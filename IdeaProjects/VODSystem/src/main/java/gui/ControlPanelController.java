package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import product.*;
import simulation.Simulation;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import user.Client;
import user.Distributor;
import user.User;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ControlPanelController implements Initializable {
    @FXML
    private ListView<Product> productList;
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
    @FXML
    private AnchorPane ap;
    @FXML
    private void refreshAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Map.Entry<Integer,Product> e : Simulation.getInstance().getSimulator().getProducts().entrySet()) {
            products.add(e.getValue());
        }
        ObservableList<User> users = FXCollections.observableArrayList();
        for (Map.Entry<String,User> e : Simulation.getInstance().getSimulator().getUsers().entrySet()) {
            users.add(e.getValue());
        }
        productList.getItems().clear();
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
                showInfo(p);
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
                showInfo(usersList.getSelectionModel().getSelectedItem());
            }
        });
        refreshAll();
    }

    public synchronized void showInfo(Object obj) {
        if(obj == null) {
            return;
        }
        showInfoPanel(obj);
    }

    @FXML
    private void searchProducts() {
        if(searchBy.getSelectedToggle() == null ) {
            return;
        }
        ObservableList<Product> products;
        if(searchBy.getSelectedToggle() == name) {
            products = FXCollections.observableList(Simulation.getInstance().getSimulator().getProducts().entrySet().stream().filter(
                    p -> p.getValue().getName().contains(pattern.getText()))
                    .map(p -> p.getValue())
                    .collect(Collectors.toList()));
        } else {
            products = FXCollections.observableArrayList();
            for(Map.Entry e : Simulation.getInstance().getSimulator().getProducts().entrySet()) {
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
    private void addDistributor() {
        PodamFactory factory = new PodamFactoryImpl();
        Simulation.getInstance().getSimulator().addUser(factory.manufacturePojo(Distributor.class));
    }

    @FXML
    private void addClient() {
        PodamFactory factory = new PodamFactoryImpl();
        Simulation.getInstance().getSimulator().addUser(factory.manufacturePojo(Client.class));
    }

    @FXML
    private void addFilm() {
        PodamFactory factory = new PodamFactoryImpl();
        Product p = factory.manufacturePojo(Film.class);
        p.setDistributor(factory.manufacturePojo(Distributor.class));
        Simulation.getInstance().getSimulator().addProduct(p);
    }

    @FXML
    private void addSeries() {
        PodamFactory factory = new PodamFactoryImpl();
        Product p = factory.manufacturePojo(Series.class);
        p.setDistributor(factory.manufacturePojo(Distributor.class));
        Simulation.getInstance().getSimulator().addProduct(p);
    }

    @FXML
    private void addLiveStream() {
        PodamFactory factory = new PodamFactoryImpl();
        Product p = factory.manufacturePojo(LiveStream.class);
        p.setDistributor(factory.manufacturePojo(Distributor.class));
        Simulation.getInstance().getSimulator().addProduct(p);
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

    private void showInfoPanel(Object obj) {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InfoPanel.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InfoPanelController infoPanelController = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        infoPanelController.setInfo(obj);
        stage.show();
    }

    @FXML
    public void save() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selected = new File(directoryChooser.showDialog(ap.getScene().getWindow()),"Simulation.ser");
        if(selected != null){
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(selected.getAbsolutePath()));
                out.writeObject(Simulation.getInstance().getSimulator());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void load() {
        FileChooser fileChooser = new FileChooser();
        File selected = new File(fileChooser.showOpenDialog(ap.getScene().getWindow()),"");
        ObjectInputStream in = null;
        Simulation.getInstance().loadSimulator(selected.getAbsolutePath());
    }
}
