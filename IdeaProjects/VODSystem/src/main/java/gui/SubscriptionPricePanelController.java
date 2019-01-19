package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import simulation.Simulation;
/**
 * Control behaviour of subscriptions Panel.
 */
public class SubscriptionPricePanelController{
    @FXML
    private Slider basic;
    @FXML
    private Slider family;
    @FXML
    private Slider premium;
    @FXML
    private Button save;

    /**
     * Updates prices of subscription set in pnael.
     */
    @FXML
    private void updateSubscriptions() {

        Simulation.getInstance().getSimulator().getBasic().setPrice(basic.getValue());
        Simulation.getInstance().getSimulator().getFamily().setPrice(family.getValue());
        Simulation.getInstance().getSimulator().getPremium().setPrice(premium.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Saved");
        alert.show();
    }
}
