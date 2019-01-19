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

/**
 * Main class which start main window.
 */
public class Main extends Application{
    /**
     * Creates new simulation or load previously saved.
     * @param args If number of arguments is 1 then simulation is loaded from the path in first argument.
     */
    public static void main(String args[]) {
        if(args.length == 1) {
            Simulation.getInstance().loadSimulator(args[0]);
        }
        BasicConfigurator.configure();
        launch(args);
    }

    /**
     * Opens control panel
     * @param primaryStage
     * @throws Exception
     */
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
