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

public class DashBordAdminFormController {
    public ImageView imgStock;
    public ImageView imgReports;
    public Label lblStock;
    public Label lblReports;
    public FontAwesomeIcon btnSignOut;
    public Label lblSignOut;
    public AnchorPane Admin_Context;

    public void Open_Stock_Form(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../View/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Admin_Context.getChildren().clear();
        Admin_Context.getChildren().add(load);
    }

    public void playMouseEnterAnimation2(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView img = (ImageView) mouseEvent.getSource();

            switch (img.getId()) {
                case "imgStock":
                    lblStock.setText("Stock Details");
                    break;

                case "imgReports":
                    lblReports.setText("Company Reports");
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

    public void Open_Repots(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../View/ReportsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Admin_Context.getChildren().clear();
        Admin_Context.getChildren().add(load);
    }

    public void Sign_Out_OnAction(MouseEvent mouseEvent) throws IOException {
        Stage window=(Stage)Admin_Context.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
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

    public void playMouseExitAnimation2(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView img = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(100),img);
            scaleTransition.setToX(1);
            scaleTransition.play();

            lblReports.setText("");
            lblStock.setText("");
            img.setEffect(null);
        }
    }
}
