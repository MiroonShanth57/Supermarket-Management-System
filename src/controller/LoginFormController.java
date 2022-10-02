package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.IOException;

public class LoginFormController {
    public JFXComboBox cmbRole;
    public AnchorPane AnchorPane1st_Context;

    String Role;

    public void initialize(){
        cmbRole.getItems().addAll("Administrator","Cashier");
        cmbRole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            Role= (String) newValue;
            //System.out.println(newValue);
        } );
    }

    public void on_Action_Open_DashBord(MouseEvent mouseEvent) throws IOException {
        if(Role=="Administrator"){
            System.out.println(Role);
            Stage window=(Stage)AnchorPane1st_Context.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBordAdminForm.fxml"))));
            window.setResizable(false);
            window.setTitle("Dash Bord");


        }else if(Role=="Cashier"){
            System.out.println(Role);
            Stage window=(Stage)AnchorPane1st_Context.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBordCashierForm.fxml"))));
            window.setResizable(false);
            window.setTitle("Dash Bord");

        }else{
            new Alert(Alert.AlertType.WARNING,"You Must Select a User Role First").show();
        }
    }
}
