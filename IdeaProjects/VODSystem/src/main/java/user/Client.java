package user;

import product.Product;
import product.transactionController.TransactionController;
import simulation.Simulation;
import simulation.Simulator;
import timeController.TimeController;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.*;

/**
 * Stores information of client and his behaviour.
 */
public class Client extends User implements Runnable{
    private String creditCardNumber;
    private Subscription subscription;
    @PodamExclude
    private Date dateOfBirth;
    @PodamCollection(nbrElements = 0)
    /**
     * Ids of bought products
     */
    private Set<Integer> boughtProductsId;

    /**
     * Describe how simulated process of buying should work.
     */
    public void buyProduct() {
        Random random = new Random();
        if(Math.abs(random.nextInt()) % 5 != 0 && boughtProductsId.size() != 0 ||
                Simulation.getInstance().getSimulator().getProducts().size() == 0) {
            return;
        }
        int id = Math.abs(random.nextInt()) % Simulation.getInstance().getSimulator().getProducts().size();

        if(boughtProductsId.contains(id)) {
            return;
        }
        TransactionController transactionController = new TransactionController();
        transactionController.performTranscation(this, id);
    }

    public void watch() {
        Random random = new Random();

        if(Math.abs(random.nextInt()) % 2 == 0 && boughtProductsId.size() > 0) {
            int which = Math.abs(random.nextInt()) % boughtProductsId.size();
            for(Integer i : boughtProductsId) {
                if(which == 0) {
                    Product p = Simulation.getInstance().getSimulator().getProducts().get(i);
                    if(p != null) {
                        p.addView();
                    }
                    return;
                }
                else {
                    which--;
                }
            }
        }
    }

    public void work() {
        while(Boolean.TRUE) {
            buyProduct();
            watch();
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Integer> getBoughtProductsId() {
        return boughtProductsId;
    }

    public void setBoughtProductsId(Set<Integer> boughtProductsId) {
        this.boughtProductsId = boughtProductsId;
    }

    public Client(String creditCardNumber, Subscription subscription, Date dateOfBirth, Set<Integer> boughtProductsId) {
        this.creditCardNumber = creditCardNumber;
        this.subscription = subscription;
        this.dateOfBirth = dateOfBirth;
        this.boughtProductsId = boughtProductsId;
    }

    public Client() {
        dateOfBirth = TimeController.getInstance().getRandomPastDay();
    }

    @Override
    public void run() {
        work();
    }

    @Override
    public String toString() {
        return super.toString() + "creditCardNumber:" + creditCardNumber + "\n" +
                "subscription:" + subscription + "\n" +
                "dateOfBirth: " + dateOfBirth + "\n" +
                "boughtProductsId: " + boughtProductsId + "\n";
    }
}
