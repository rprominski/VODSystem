package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Pair;
import product.Product;
import simulation.Simulator;
import user.User;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class InfoPanelController implements Initializable{
    @FXML
    public Button delete;

    @FXML
    private TextArea filmInfo = new TextArea("");

    @FXML
    private LineChart chart;
    private XYChart.Series<String,Integer> views;
    private Object object;
    @FXML
    public void deleteObject() {
        Stage stage = (Stage) delete.getScene().getWindow();
        if(Product.class.isAssignableFrom(object.getClass())) {
            Simulator.getInstance().removeProduct((Product) object);
        }
        if(User.class.isAssignableFrom(object.getClass())) {
            Simulator.getInstance().removeUser((User) object);
        }
        stage.close();
    }

    public TextArea getFilmInfo() {
        return filmInfo;
    }

    public void setInfo(Object obj) {
        object = obj;
        filmInfo.setText(obj.toString());
        if(Product.class.isAssignableFrom(obj.getClass())) {
            updateViews((Product) obj);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        views = new XYChart.Series<>();
        views.setName("Views");
        chart.getData().add(views);
    }

    private synchronized void updateViews(Product product) {
        views.getData().clear();
        for(Pair<String,Integer> p : product.getViews()) {
            views.getData().add(new XYChart.Data(p.getKey(),p.getValue()));
        }
        views.getData().sort(Comparator.comparingInt(d -> d.getYValue()));
    }
}
