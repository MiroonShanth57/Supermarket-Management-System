package controller;

import bo.BoFactory;
import bo.Customer.ItemBO;
import bo.Customer.OrderBO;
import com.jfoenix.controls.JFXTextField;
import com.sun.scenario.effect.Color4f;
import com.sun.scenario.effect.DropShadow;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import dto.ItemDTO;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AddItemFormController {
    public FontAwesomeIcon btnBack;
    public Label lblBack;
    public JFXTextField txtUPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtPSize;
    public JFXTextField txtIDescription;
    public JFXTextField txtICode;
    public JFXTextField txtItemCodeSearch;
    public TableView tblItem;
    public TableColumn colICode;
    public TableColumn colIDescription;
    public TableColumn colISize;
    public TableColumn colIQtyHand;
    public TableColumn colIUnitP;
    public AnchorPane Item_Context;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ITEM);
    private final OrderBO orderBO = (OrderBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.ORDER);

    public void Back_To_Menu(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBordAdminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Item_Context.getChildren().clear();
        Item_Context.getChildren().add(load);
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

    public void Add_Item_to_Database(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(
                txtICode.getText(), txtIDescription.getText(),
                txtPSize.getText(), Double.parseDouble(txtUPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );
        if (itemBO.ItemSaved(itemDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Saved").show();
        }
    }

    public void Search_Item(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO= itemBO.search(txtItemCodeSearch.getText());
        if (itemDTO != null) {
            txtIDescription.setText(itemDTO.getDescription());
            txtPSize.setText(itemDTO.getPackSize());
            txtUPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        } else new Alert(Alert.AlertType.WARNING, "Empty Set").show();
    }
}
