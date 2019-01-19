package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import product.Product;
import simulation.Simulation;
import simulation.Simulator;

import java.util.Map;

/**
 * ListView which can stores products.
 */
public class ProductList extends ListView<Product> {
    public ProductList() {
        super();
        update();
        setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ProductListCell();
            }
        });
    }

    /**
     *  Updates the products in list.
     */
    public void update() {
        setItems(getProducts());
    }

    private ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulation.getInstance().getSimulator().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        return products;
    }
}
