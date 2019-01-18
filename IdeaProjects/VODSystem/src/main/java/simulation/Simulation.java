package simulation;

import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

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
        System.out.println(simulator);
        Simulator s = null;
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            simulator = (Simulator) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
      //  System.out.println(s);
       // simulator.run();
     /*   System.out.println(this.simulator);
            for (Map.Entry<String,User> u  : simulator.getUsers().entrySet()) {
            u.getValue().start();
        }*/
    }
}
