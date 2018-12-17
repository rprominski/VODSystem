package simulation;

import product.Product;
import sun.awt.windows.ThemeReader;
import timeController.TimeController;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import user.Client;
import user.Distributor;
import user.Subscription;
import user.User;

import java.util.*;

public class Simulator extends Thread{
    private static Simulator ourInstance = new Simulator();
    private Map<String,User> users;
    private Map<Integer,Product> products;
    private double profitFromSystem;
    private volatile int maxProductId;
    private volatile int maxUserId;
    private boolean end = false;
    private Subscription basic;
    private Subscription family ;
    private Subscription premium;
    public void saveSimulation(String path) {

    }

    public void loadSimulation(String path) {

    }

    public void run() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        addUser(podamFactory.manufacturePojo(Distributor.class));
        while(!end) {
            for (int i = 0; i < products.size() / 10 + 1; i++) {
                addUser(podamFactory.manufacturePojo(Client.class));
            }
            try {
                Thread.sleep(TimeController.getInstance().getDayTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public void stopSimulation() {
        end = true;
        System.out.println(end);
    }

    public static Simulator getInstance() {
        return ourInstance;
    }

    private Simulator() {
        maxProductId = 0;
        users = new HashMap<>();
        products = new HashMap<>();
        basic = new Subscription(1.0,2,3);
        family = new Subscription(2.0, 3, 4);
        premium = new Subscription(3.0, 4,5);
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

    public Subscription getBasic() {
        return basic;
    }

    public void setBasic(Subscription basic) {
        this.basic = basic;
    }

    public Subscription getFamily() {
        return family;
    }

    public void setFamily(Subscription family) {
        this.family = family;
    }

    public Subscription getPremium() {
        return premium;
    }

    public void setPremium(Subscription premium) {
        this.premium = premium;
    }
}
