package controller;

import com.sun.scenario.effect.Color4f;
import com.sun.scenario.effect.DropShadow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ManageOrderFormController {
    public FontAwesomeIcon btnBack;
    public Label lblBack;
    public AnchorPane ManageFFormContext;
    public TableView tblMangeOrder;


//    public void initialize() throws SQLException, ClassNotFoundException {
//
//        tblMangeOrder.setEditable(true);
//
//        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
//        colOdrQty.setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
//        colTtl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
//
//        mngOdrCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
//
//        mngOdrDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
//        mngOdrDescription.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        mngOdrQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
//        mngOdrQty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//
//        mngOdrUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//
//        setOrderId();
//        loadCustomersToComboBox();
//        loadItemsToComboBox();
//        setDate();
//        setTime();
//        setManageOrderCustomerIds();
//        //setManageOrderOrderIds();
//
//
//        itemComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                setItemDetails(newValue);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//        addToCartTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
//            selectedRowForRemove = (int) newValue;
//        });
//
//        mngOdrCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                setManageOrderOrderIds(newValue);
//                setManageOrderCustomerName(newValue);
//            } catch (SQLException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//        mngOrderOIdsListener();
//
//        tblManageOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
//            removeItemFromDB = tblManageOrder.getSelectionModel().getSelectedItem();
//        });
//
//        manageOrderTableEditOnAction();
//
//    }



    public void Back_To_Menu(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBordCashierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ManageFFormContext.getChildren().clear();
        ManageFFormContext.getChildren().add(load);
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
}
