package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import product.Product;
import simulation.Simulator;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class PanelController implements Initializable {
    @FXML
    private ListView<Product> productList;
    @FXML
    private LineChart chart;
    @FXML
    private TextArea filmInfo;
    @FXML
    private MenuBar menu;
    @FXML
    private MenuItem refresh;

    @FXML
    private void refreshAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulator.getInstance().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        productList.setItems(products);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productList.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ProductListCell();
            }
        });
        productList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showProductInfo(productList.getSelectionModel().getSelectedItem());
            }
        });


        filmInfo.setEditable(false);
        refreshAll();
    }

    public void showProductInfo(Product product) {
        filmInfo.setText(product.toString());
    }
}
