package user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import product.Film;
import product.LiveStream;
import product.Product;
import product.Series;
import simulation.Simulation;
import simulation.Simulator;
import timeController.TimeController;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.sql.Time;
import java.util.Random;

/**
 *
 */
public class Distributor extends User implements Runnable{
    private Contract contract;
    private String bankAccount;
    /**
     * Number of month of when get last income, using to know when give him monthly lump sum.
     */
    private int lastIncomeMonth;

    public Contract getContract() {
        return contract;
    }

    /**
     * Describe how distributor creates products
     */
    private void disturbe() {
        for(int j = 0; j < Simulation.getInstance().getSimulator().getProducts().size()/100 + 1; j++) {
            Product product;
            Random random = new Random();
            int i = Math.abs(random.nextInt());
            PodamFactory factory = new PodamFactoryImpl();

            if (i % 3 == 0) {
                product = factory.manufacturePojo(Film.class);
            } else
            if (i % 3 == 1) {
                product = factory.manufacturePojo(Series.class);
            } else {
                product = factory.manufacturePojo(LiveStream.class);
            }
            product.setDistributor(this);
            Simulation.getInstance().getSimulator().addProduct(product);
        }
    }

    /**
     * Define when distributor changes contract.
     */
    private void negotiateContract() {
        if(new Random().nextInt()%100 == 0) {
            contract = new Contract();
        }
    }

    /**
     * Calculate profit from product.
     * @param product product which was bought.
     * @param subscription Subscription of client who bought the prodcut.
     * @return
     */
    public double calculateProfit(Product product, Subscription subscription) {
        double sum = contract.getProfitForProduct();
        int month = TimeController.getInstance().getMonth();
        if(month != lastIncomeMonth) {
            sum += contract.getMonthlyLumpSum();
            lastIncomeMonth = month;
        }
        Simulation.getInstance().getSimulator().calculateIncomeFromProduct(product.getPrice() - sum,month);
        return sum;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        work();
    }

    @Override
    public void work() {
        lastIncomeMonth = -1;
        while(Boolean.TRUE) {
            disturbe();
            negotiateContract();
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "contract: " + contract + "\n" +
                "bankAccount: " + bankAccount + "\n";
    }
}
