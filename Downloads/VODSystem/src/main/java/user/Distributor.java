package user;

import product.Product;
import simulation.Simulator;

import javax.jws.soap.SOAPBinding;

public class Distributor extends User {
    private Contract contract;
    private String bankAccount;
    private double monthlyProfit;

    public Contract getContract() {
        return contract;
    }

    private void disturbe() {

    }

    private void negotiateContract() {

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

    }
}
