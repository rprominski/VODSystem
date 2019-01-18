package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.BasicConfigurator;
import simulation.Simulation;
import simulation.Simulator;

public class Main extends Application{

    static String loadPath = null;

    public static void main(String args[]) {
        if(args.length == 1) {
            loadPath = args[0];
            Simulation.getInstance().loadSimulator(args[0]);
        }
        BasicConfigurator.configure();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Simulation.getInstance().getSimulator().start();
        Parent root = FXMLLoader.load(getClass().getResource("/controlPanel.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> Simulation.getInstance().getSimulator().stopSimulation());
    }
}
