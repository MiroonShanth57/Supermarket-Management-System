package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.scenario.effect.Color4f;
import com.sun.scenario.effect.DropShadow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
    public JFXComboBox cmbSearchCus;
    public JFXComboBox cmbSearchItem;
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

    public void Add_To_Cart(MouseEvent mouseEvent) {
    }

    public void removeFromCart(ActionEvent actionEvent) {
    }

    public void finalAmount(ActionEvent actionEvent) {
    }

    public void confiemOrder(ActionEvent actionEvent) {
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

    public void save_Customer_Databases(MouseEvent mouseEvent) {
//        Customer customer1= new Customer(
//                txtCusID.getText(),txtCusTitle.getText(),txtCusName.getText(),
//                txtCusAddress.getText(),txtCusCity.getText(),txtCusProvince.getText(),txtCusPCode.getText());
//
//        if(saveCustomerInDatabase(customer1)){
//            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
//
//        }else{
//            new Alert(Alert.AlertType.ERROR,"Try Again").show();
//        }
    }

    public void Remove_Customer_Databases(MouseEvent mouseEvent) {
    }
}
