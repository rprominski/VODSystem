package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import product.Actor;
import product.Product;
import product.WatchableObject;
import simulation.Simulator;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ControlPanelController implements Initializable {
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
    private RadioButton actor;
    @FXML
    private RadioButton name;
    @FXML
    private Button search;
    @FXML
    private ToggleGroup searchBy;
    @FXML
    private TextField pattern;
    @FXML
    private ListView<User> usersList;
    @FXML
    private MenuItem subscriptions;
    private XYChart.Series<String,Integer> views;

    @FXML
    private void refreshAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulator.getInstance().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        ObservableList<User> users = FXCollections.observableArrayList();
        for (Map.Entry<String,User> e : Simulator.getInstance().getUsers().entrySet()) {
            users.add(e.getValue());
        }
        productList.setItems(products);
        usersList.setItems(users);
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
                Product p = productList.getSelectionModel().getSelectedItem();
                showProductInfo(p);
                updateViews(p);

            }
        });
        usersList.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new UserListCell();
            }
        });
        usersList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public synchronized void handle(MouseEvent event) {
                showProductInfo(usersList.getSelectionModel().getSelectedItem());
            }
        });
        views = new XYChart.Series<>();
        views.setName("Views");
        chart.getData().add(views);
        refreshAll();
    }

    public synchronized void showProductInfo(Object obj) {
        if(obj == null) {
            return;
        }
        filmInfo.setText(obj.toString());
    }

    public synchronized void showUserInfo(User user) {
        if(user == null) {
            return;
        }
        filmInfo.setText(user.toString());
    }

    private synchronized void updateViews(Product product) {
        views.getData().clear();
        for(Pair<String,Integer> p : product.getViews()) {
            views.getData().add(new XYChart.Data(p.getKey(),p.getValue()));
        }
        views.getData().sort(Comparator.comparingInt(d -> d.getYValue()));
    }

    @FXML
    private void searchProducts() {
        if(searchBy.getSelectedToggle() == null ) {
            return;
        }
        ObservableList<Product> products;
        if(searchBy.getSelectedToggle() == name) {
            products = FXCollections.observableList(Simulator.getInstance().getProducts().entrySet().stream().filter(
                    p -> p.getValue().getName().contains(pattern.getText()))
                    .map(p -> p.getValue())
                    .collect(Collectors.toList()));
        } else {
            products = FXCollections.observableArrayList();
            for(Map.Entry e : Simulator.getInstance().getProducts().entrySet()) {
                if(WatchableObject.class.isAssignableFrom(e.getValue().getClass())) {
                    WatchableObject w = (WatchableObject) e.getValue();
                    for(Actor a : w.getActors()) {
                        if(a.getFirstName().contains(pattern.getText()) || a.getSurname().contains(pattern.getText())) {
                            products.add(w);
                            break;
                        }
                    }
                }
            }
        }
        productList.setItems(products);
    }
    @FXML
    private void showSubscriptionsWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/subscriptionsPricePanel.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void showProductPriceWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ProductPricePanel.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
