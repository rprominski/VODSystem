package gui;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import simulation.Simulator;
import user.Subscription;

import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionPricePanelController{
    @FXML
    private Slider basic;
    @FXML
    private Slider family;
    @FXML
    private Slider premium;
    @FXML
    private Button save;

    @FXML
    private void updateSubscriptions() {

        Simulator.getInstance().getBasic().setPrice(basic.getValue());
        Simulator.getInstance().getFamily().setPrice(family.getValue());
        Simulator.getInstance().getPremium().setPrice(premium.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Saved");
        alert.show();
    }
}
