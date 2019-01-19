package simulation;

import javafx.util.Pair;
import product.CircularVector;
import product.Product;
import timeController.TimeController;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import user.Client;
import user.Distributor;
import user.Subscription;
import user.User;

import java.io.*;
import java.sql.Time;
import java.util.*;

/**
 * Class created to enable serialization of simulation.
 */
public class Simulator extends Thread implements Serializable{
    private Map<String,User> users;
    private Map<Integer,Product> products;
    /**
     * Every product has own id. When new is created it gets id equal to this field and this field is incremented.
     */
    private volatile int maxProductId;
    /**
     * Flag used to check if simulation have to end
     */
    private boolean end = false;
    private Subscription basic;
    private Subscription family ;
    private Subscription premium;
    private Pair<Integer,Double> currentMonthIncome;
    /**
     * Incomes from last 3 months, using to check if simulation have to end.
     */
    private CircularVector<Double> incomes;

    /**
     * Adds income from one product to monthly income if its the same month. Otherwise new month with the income from
     * prduct is created. Income from month is added to structure contains income from last three months.
     * @param income
     * @param month
     */
    public synchronized void calculateIncomeFromProduct(double income,int month) {
        if(month == currentMonthIncome.getKey()) {
            currentMonthIncome = new Pair<>(month,currentMonthIncome.getValue() + income);
        } else {
            incomes.add(currentMonthIncome.getValue());
            currentMonthIncome = new Pair<>(month,income);
        }
    }

    /**
     * First create distributor. Then create some number of users depending on number of products in simulation.
     * Then check if there was loss in 3 last months. If yes end the simulation.
     */
    @Override
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
            if(lossIn3Months()) {
                stopSimulation();
            }
        }
        System.exit(0);
    }

    /**
     * Check if user with give name exists. If not add him to simulation.
     * @param user User to add.
     */

    public void addUser(User user) {
        if(users.containsKey(user.getName())) {
            return;
        }
        users.put(user.getName(),user);

        user.start();
    }

    /**
     * Add Product to simulation.
     * @param product Product to add.
     */
    public void addProduct(Product product) {
        product.setId(maxProductId);
        products.put(maxProductId,product);
        maxProductId++;
    }

    /**
     * Remove user from simulation.
     * @param user User to remove.
     */
    public void removeUser(User user) {
        users.get(user.getName());
    }
    /**
     * Remove product from simulation.
     * @param product User to remove.
     */
    public void removeProduct(Product product) {
        products.remove(product.getId());
    }

    /**
     * Set flag to ends imulation
     */
    public void stopSimulation() {
        end = true;
    }

    public Simulator() {
        maxProductId = 0;
        users = new HashMap<>();
        products = new HashMap<>();
        basic = new Subscription(1.0,2,3);
        family = new Subscription(2.0, 3, 4);
        premium = new Subscription(3.0, 4,5);
        incomes = new CircularVector<Double>(3);
        currentMonthIncome = new Pair<Integer, Double>(TimeController.getInstance().getMonth(),0.0);
        ObjectInputStream in = null;
    }

    public Map<String,User> getUsers() {
        return users;
    }

    public Map<Integer,Product> getProducts() {
        return products;
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

    /**
     * Check if income in last 3 month was negative.
     * @return True if was.
     */
    public boolean lossIn3Months() {
        int l = 0;
        for(double i : incomes) {
            if(i < 0) {
                l++;
            }
        }
        return l == 3;
    }
}
