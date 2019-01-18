package simulation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Simulation {
    private Simulator simulator;

    private static Simulation ourInstance = new Simulation();

    public static Simulation getInstance() {
        return ourInstance;
    }

    private Simulation() {
        simulator = new Simulator();
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = null;
        this.simulator = simulator;
    }

    public void loadSimulator(String path) {
        ObjectInputStream in = null;
        Simulator s = null;

        try {
            in = new ObjectInputStream(new FileInputStream(path));
            simulator = (Simulator) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
