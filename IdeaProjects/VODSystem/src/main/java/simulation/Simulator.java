package simulation;

import product.Product;
import user.Distributor;
import user.User;

import java.util.ArrayList;
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

    void run() {
        Distributor distributor = new Distributor();
        Thread thread = new Thread(distributor);
        thread.start();

        while(Boolean.TRUE) {
            System.out.println(products.size());
            for(Product product : getProducts()) {
                System.out.println(product);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {

    }

    public void addUser(User user) {

    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeUser(User user) {

    }

    public void removeProduct(Product product) {

    }

    public static Simulator getInstance() {
        return ourInstance;
    }

    private Simulator() {
        users = new ArrayList<>();
        products = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getProfitFromSystem() {
        return profitFromSystem;
    }
}
