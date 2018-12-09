package user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import product.Film;
import product.LiveStream;
import product.Product;
import product.Series;
import simulation.Simulator;

import javax.jws.soap.SOAPBinding;
import java.util.Random;

public class Distributor extends User implements Runnable{
    private Contract contract;
    private String bankAccount;
    private double monthlyProfit;

    public Contract getContract() {
        return contract;
    }

    private void disturbe() {
        for(int j = 0; j < Simulator.getInstance().getProducts().size()/100 + 1; j++) {
            Product product;
            Random random = new Random();
            int i = Math.abs(random.nextInt());
            System.out.println(i % 3);
            if (i % 3 == 0) {
                product = new Film();
            } else
            if (i % 3 == 1) {
                product = new Series();
            } else {
                product = new LiveStream();
            }
            Simulator.getInstance().addProduct(product);
        }
    }

    private void negotiateContract() {
        Random random = new Random();

        if(random.nextInt()%100 == 0) {
            contract = new Contract();
        }
    }

    public void calculateProfit(Product product) {

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

    public double getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(double monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    public void work() {
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

    public void run() {
        work();
    }
}
