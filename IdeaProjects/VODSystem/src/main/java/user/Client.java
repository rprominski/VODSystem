package user;

import product.Product;
import simulation.Simulator;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Client extends User {
    private String creditCardNumber;
    private Subscription subscription;
    private Date dateOfBirth;
    private List<Product> boughtProducts;

    public void buyProduct() {
        Product product = Simulator.getInstance().getProducts().get
                (new Random().nextInt() % Simulator.getInstance().getProducts().size());

        if(!boughtProducts.contains(product)) {
            boughtProducts.add(product);
        }
       // Simulator.getInstance().getUsers().get(Simulator.getInstance().getUsers().indexOf())
    }

    public void watch() {

    }

    public void work() {

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

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }
}
