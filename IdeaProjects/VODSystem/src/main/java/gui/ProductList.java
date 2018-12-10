package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import product.Product;
import simulation.Simulator;

import java.util.Map;

public class ProductList extends ListView<Product> {
    public ProductList() {
        super();
        update();
    }

    public void update() {
        setItems(getProducts());
    }

    private ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulator.getInstance().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        return products;
    }
}
