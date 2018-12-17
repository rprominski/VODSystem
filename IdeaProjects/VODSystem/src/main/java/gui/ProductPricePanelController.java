package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import product.Product;
import simulation.Simulator;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ProductPricePanelController implements Initializable{
    @FXML
    private Button save;
    @FXML
    private TextArea oldPrice;
    @FXML
    private TextArea newPrice;
    @FXML
    private ListView<Product> products;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ProductListCell();
            }
        });
        products.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public synchronized void handle(MouseEvent event) {
                Product p = products.getSelectionModel().getSelectedItem();
                oldPrice.setText(Double.toString(p.getPrice()));
            }
        });
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulator.getInstance().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        this.products.setItems(products);
    }
    @FXML
    void updatePrice() {
        Product p = products.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Saved");

        Simulator.getInstance().getProducts().get(p.getId()).setPrice(Double.parseDouble(newPrice.getText()));
        alert.show();
    }
}
