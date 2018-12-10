package gui;

import org.apache.log4j.BasicConfigurator;
import product.LiveStream;
import simulation.Simulator;

public class ControlPanel {

    public static void main(String args[]) {
        BasicConfigurator.configure();
        Simulator.getInstance().run();
        LiveStream liveStream = new LiveStream();
    }
}
