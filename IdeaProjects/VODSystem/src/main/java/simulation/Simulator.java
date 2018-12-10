package simulation;

import product.Product;
import timeController.TimeController;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import user.Client;
import user.Distributor;
import user.User;

import java.util.*;

public class Simulator {
    private static Simulator ourInstance = new Simulator();
    private Map<String,User> users;
    private Map<Integer,Product> products;
    private double profitFromSystem;
    private volatile int maxProductId;
    private volatile int maxUserId;

    public void saveSimulation(String path) {

    }

    public void loadSimulation(String path) {

    }

    void run() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        addUser(podamFactory.manufacturePojo(Distributor.class));
        while(Boolean.TRUE) {
            for (int i = 0; i < products.size() / 10 + 1; i++) {
                addUser(podamFactory.manufacturePojo(Client.class));
            }
            try {
                Thread.sleep(TimeController.getInstance().getDayTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Usery %d\n",users.size());
            System.out.printf("Produkty %d\n",products.size());
        }

    }

    public void stop() {

    }

    public void addUser(User user) {
        if(users.containsKey(user.getName())) {
            return;
        }
        users.put(user.getName(),user);

        user.start();
    }

    public void addProduct(Product product) {
        product.setId(maxProductId);
        products.put(maxProductId,product);
        maxProductId++;
    }

    public void removeUser(User user) {

    }

    public void removeProduct(Product product) {

    }

    public static Simulator getInstance() {
        return ourInstance;
    }

    private Simulator() {
        maxProductId = 0;
        users = new HashMap<>();
        products = new HashMap<>();
    }

    public Map<String,User> getUsers() {
        return users;
    }

    public Map<Integer,Product> getProducts() {
        return products;
    }

    public double getProfitFromSystem() {
        return profitFromSystem;
    }

}
