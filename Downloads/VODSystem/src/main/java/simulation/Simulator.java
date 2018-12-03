package simulation;

import product.Product;
import user.User;

import java.util.List;

public class Simulator {
    private static Simulator ourInstance = new Simulator();
    private List<User> users;
    private List<Product> products;
    private double profitFromSystem;

    public void saveSimulation(String path) {

    }

    public void loadSimulation(String path) {

    }

    public void run() {

    }

    public void stop() {

    }

    public void addUser(User user) {

    }

    public void addProduct(Product product) {

    }

    public void removeUser(User user) {

    }

    public void removeProduct(Product product) {

    }

    public static Simulator getInstance() {
        return ourInstance;
    }

    private Simulator() {
    }
}
