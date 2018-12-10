package user;

import product.Product;
import product.transactionController.TransactionController;
import simulation.Simulator;
import uk.co.jemos.podam.common.PodamCollection;

import java.util.*;

public class Client extends User implements Runnable{
    private String creditCardNumber;
    private Subscription subscription;
    private Date dateOfBirth;
    @PodamCollection(nbrElements = 0)
    private Set<Integer> boughtProductsId;

    public void buyProduct() {
        Random random = new Random();
        if(Math.abs(random.nextInt()) % 5 != 0 && boughtProductsId.size() != 0 ||
                Simulator.getInstance().getProducts().size() == 0) {
            return;
        }
        int id = Math.abs(random.nextInt()) % Simulator.getInstance().getProducts().size();

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
                    Simulator.getInstance().getProducts().get(i).addView();
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

   // public Client() {
   //     boughtProductsId = new HashSet<>();
   // }

    @Override
    public void run() {
        work();
    }
}