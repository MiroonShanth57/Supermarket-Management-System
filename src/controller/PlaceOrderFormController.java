package controller;

import bo.BoFactory;
import bo.Customer.CustomerBO;
import bo.Customer.ItemBO;
import bo.Customer.OrderBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.scenario.effect.Color4f;
import com.sun.scenario.effect.DropShadow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import tdm.CartTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderFormController {
    public FontAwesomeIcon btnBack;
    public Label lblBack;
    public JFXTextField txtCusID;
    public JFXTextField txtCusTitle;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtCusProvince;
    public JFXTextField txtCusPCode;
    public JFXComboBox <String> cmbSearchCus;
    public JFXComboBox <String> cmbSearchItem;
    public JFXTextField txtForSaleQty;
    public Label lblPackSize;
    public Label lblUPrice;
    public Label lblQtyOnHand;
    public Label lblDescription;
    public TableView tblCart;
    public TableColumn colICode;
    public TableColumn colDescription;
    public TableColumn colPSize;
    public TableColumn colUPrice;
    public TableColumn colQuantity;
    public TableColumn colTotal;
    public Label lblOID;
    public Label lblBillAmount;
    public Label lblBillFinalAmount;
    public JFXTextField txtDiscount;
    public Label lblDate;
    public BorderPane PlaceOrderContext;
    public TableView<CartTM> addToCartTable;


    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ORDER);
    private final ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ITEM);

    int selectedRowForRemove = -1;

    public void initialize() throws SQLException, ClassNotFoundException {


        setOrderId();
        loadCustomersToComboBox();
        loadItemsToComboBox();
        setDate();
        setTime();
        //setManageOrderOrderIds();


        cmbSearchItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemDetails((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRowForRemove = (int) newValue;
        });
    }
    public static String superItemId;


    public void setItemDetails(String itemId) throws SQLException, ClassNotFoundException {
        superItemId = itemId;
        ItemDTO item = itemBO.search(itemId);

        if (item == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Set ...").show();
        } else {
            lblDescription.setText(item.getDescription());
            lblPackSize.setText(item.getPackSize());
            lblUPrice.setText(String.valueOf(item.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        }
    }

    private void loadItemsToComboBox() throws SQLException, ClassNotFoundException {
        cmbSearchItem.setItems(itemBO.getItemIds());
    }

    public void Add_To_Cart(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        double unitPriceAfterDiscount = 0;
        if (txtDiscount.getText() != null) {
            if (itemBO.addDiscountToItem(txtDiscount.getText()) != -1) {
                unitPriceAfterDiscount =
                        Double.parseDouble(lblUPrice.getText()) - itemBO.addDiscountToItem(txtDiscount.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Discount Added").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Discount Not Added.. Invalid Discount Code").show();
                unitPriceAfterDiscount =
                        Double.parseDouble(lblUPrice.getText());
            }
        } else {
            unitPriceAfterDiscount =
                    Double.parseDouble(lblUPrice.getText());
        }


        int ordredQty = Integer.parseInt(txtForSaleQty.getText());

        if (ordredQty <= Integer.parseInt(txtForSaleQty.getText())) {

            double ttl = unitPriceAfterDiscount * ordredQty;

            CartTM tm = new CartTM(
                    cmbSearchItem.getValue(),
                    lblDescription.getText(),
                    cmbSearchCus.getValue(),
                    Double.parseDouble(lblUPrice.getText()),
                    ordredQty,
                    ttl
            );

            int rowNumber = isExists(tm);

            if (rowNumber == -1) {
                obList.add(tm);
            } else {
                CartTM temp = obList.get(rowNumber);
                obList.add(new CartTM(
                        temp.getItemCode(),
                        temp.getItemDescription(),
                        temp.getCustomerId(),
                        temp.getItemUnitPrice(),
                        (temp.getOrderedQty() + ordredQty),
                        (temp.getTtl() + ttl)
                ));
                obList.remove(rowNumber);
            }
        } else new Alert(Alert.AlertType.WARNING, "Invalid Order Quantity ...").show();


        addToCartTable.setItems(obList);

//        if (obList != null) {
//            placeOrder.setVisible(true);
//        }

        setSubTtl();
    }

    private void setSubTtl() {
        double ttl = 0;
        for (CartTM temp :
                obList) {
            ttl += temp.getTtl();
        }
        lblBillFinalAmount.setText("-/" + ttl);
    }

    public ObservableList<CartTM> obList = FXCollections.observableArrayList();


    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }

    public void removeFromCart(ActionEvent actionEvent) {
    }

    public void finalAmount(ActionEvent actionEvent) {
    }

    public void confiemOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println(lblOID.getText());
        ArrayList<OrderDetailsDTO> item = new ArrayList<>();

        for (CartTM temp :
                obList) {
            item.add(new OrderDetailsDTO(
                    lblOID.getText(),
                    temp.getItemCode(),
                    temp.getOrderedQty(),
                    temp.getItemUnitPrice() - (temp.getTtl() / temp.getOrderedQty())
            ));
        }

        OrderDTO order = new OrderDTO(
                lblOID.getText(),
                new Date(),
                cmbSearchCus.getValue(),
                item
        );

        if (orderBO.saveOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done ...").show();
        }

        setOrderId();

    }

    private void setOrderId() throws SQLException, ClassNotFoundException {
        //System.out.println(new OrderBOImpl().getOrderId());
        lblOID.setText(orderBO.getOrderId());
    }

    public void Back_To_Menu(MouseEvent mouseEvent) throws IOException {
        Stage window=(Stage)PlaceOrderContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBordCashierForm.fxml"))));
        window.setResizable(false);
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof FontAwesomeIcon){
            FontAwesomeIcon icon=(FontAwesomeIcon) mouseEvent.getSource();

            switch (icon.getId()){
                case "btnBack":
                    lblBack.setText("Back to Menu");
                    break;

            }

            ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(100),icon);
            scaleTransition.setToX(1.2);
            scaleTransition.play();

            DropShadow glow=new DropShadow();
            glow.setColor(Color4f.BLACK);
            glow.setRadius(20);
            icon.setEffect(icon.getEffect());

        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof FontAwesomeIcon) {
            FontAwesomeIcon icon = (FontAwesomeIcon) mouseEvent.getSource();

            ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(100),icon);
            scaleTransition.setToX(1);
            scaleTransition.play();

            lblBack.setText("");
            icon.setEffect(null);
        }
    }

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.CUSTOMER);



    public void save_Customer_Databases(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {CustomerDTO c = new CustomerDTO(
            txtCusID.getText(),
            txtCusTitle.getText(),
            txtCusName.getText(),
            txtCusAddress.getText(),
            txtCusCity.getText(),
            txtCusProvince.getText(),
            txtCusPCode.getText()
    );
        if (txtCusID.getText().isEmpty() || txtCusTitle.getText().isEmpty() || txtCusName.getText().isEmpty() || txtCusAddress.getText().isEmpty() || txtCusCity.getText().isEmpty() || txtCusProvince.getText().isEmpty() || txtCusPCode.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please Fill All Fields ...").show();
        } else {
            if (customerBO.customerSaved(c)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved ...").show();
            } else new Alert(Alert.AlertType.WARNING, "Try Again ...").show();
        }
        loadCustomersToComboBox();
    }

    private void loadCustomersToComboBox() throws SQLException, ClassNotFoundException {
        if (customerBO.getCustomerIds() != null) {
            cmbSearchCus.setItems(customerBO.getCustomerIds());
        }
    }

    public void Remove_Customer_Databases(MouseEvent mouseEvent) {

    }

    private void setTime() {
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                try {
                    Thread.sleep(1000); //1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblDate.setText(time);
                });
            }
        });
        timerThread.start();

    }

    private void setDate() {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dateShow = new Date();
        lblDate.setText(formatter1.format(dateShow));
    }



}
