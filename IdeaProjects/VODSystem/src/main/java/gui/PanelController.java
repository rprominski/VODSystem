package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Pair;
import product.Product;
import simulation.Simulator;
import timeController.TimeController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
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
    private XYChart.Series<String,Integer> views;

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
            public synchronized void handle(MouseEvent event) {
                showProductInfo(productList.getSelectionModel().getSelectedItem());
            }
        });

        views = new XYChart.Series<>();
        views.setName("Views");

        chart.getData().add(views);
        refreshAll();
    }

    public synchronized void showProductInfo(Product product) {
        filmInfo.setText(product.toString());
        updateViews(product);
    }

    private synchronized void updateViews(Product product) {
        views.getData().clear();
        for(Pair<String,Integer> p : product.getViews()) {
            views.getData().add(new XYChart.Data(p.getKey(),p.getValue()));
        }
        views.getData().sort(Comparator.comparingInt(d -> d.getYValue()));
    }


}
