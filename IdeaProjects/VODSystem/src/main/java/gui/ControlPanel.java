package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.BasicConfigurator;
import simulation.Simulator;

public class ControlPanel extends Application{

    public static void main(String args[]) {
        BasicConfigurator.configure();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Simulator.getInstance().start();
        Parent root = FXMLLoader.load(getClass().getResource("/controlPanel.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Simulator.getInstance().stopSimulation();
            }
        });
    }
}
