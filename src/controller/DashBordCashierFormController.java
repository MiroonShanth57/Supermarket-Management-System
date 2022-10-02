package controller;

import com.sun.scenario.effect.Color4f;
import com.sun.scenario.effect.DropShadow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class DashBordCashierFormController {
    public ImageView imgPOrder;
    public ImageView imgMOrder;
    public Label lblPOrder;
    public Label lblMangeOrder;
    public FontAwesomeIcon btnSignOut;
    public Label lblSignOut;
    public AnchorPane Cashier_Context;

    public void Open_POrder_Form(MouseEvent mouseEvent) throws IOException {
        Stage window=(Stage)Cashier_Context.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/PlaceOrderForm.fxml"))));
        window.setResizable(false);
        window.setTitle("Order Form");
    }

    public void playMouseEnterAnimation3(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView img = (ImageView) mouseEvent.getSource();

            switch (img.getId()) {
                case "imgPOrder":
                    lblPOrder.setText("Place Order");
                    break;

                case "imgMOrder":
                    lblMangeOrder.setText("Manage Order");
                    break;

            }

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), img);
            scaleTransition.setToX(1.2);
            scaleTransition.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color4f.BLACK);
            glow.setRadius(20);
            img.setEffect(img.getEffect());
        }
    }

    public void Open_MOrder(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../View/ManageOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Cashier_Context.getChildren().clear();
        Cashier_Context.getChildren().add(load);
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof FontAwesomeIcon) {
            FontAwesomeIcon icon = (FontAwesomeIcon) mouseEvent.getSource();

            ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(100),icon);
            scaleTransition.setToX(1);
            scaleTransition.play();

            lblSignOut.setText("");
            icon.setEffect(null);
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof FontAwesomeIcon){
            FontAwesomeIcon icon=(FontAwesomeIcon) mouseEvent.getSource();

            switch (icon.getId()){
                case "btnSignOut":
                    lblSignOut.setText("Sign Out");
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

    public void Sign_Out_OnAction(MouseEvent mouseEvent) throws IOException {
        Stage window=(Stage)Cashier_Context.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
    }

    public void playMouseExitAnimation3(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView img = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(100),img);
            scaleTransition.setToX(1);
            scaleTransition.play();

            lblMangeOrder.setText("");
            lblPOrder.setText("");
            img.setEffect(null);
        }
    }
}
