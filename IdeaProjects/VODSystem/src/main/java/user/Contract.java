package user;

import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamIntValue;

import java.io.Serializable;

/**
 * Stores information about distributor's contract.
 */
public class Contract implements Serializable {
    @PodamDoubleValue(minValue = 100, maxValue = 3000)
    /**
     * How mouch money distributor get each month
     */
    private double monthlyLumpSum;
    @PodamDoubleValue(minValue = 1,maxValue = 20)
    /**
     * How much percent of product price distributor get
     */
    private double profitForProduct;

    public double getMonthlyLumpSum() {
        return monthlyLumpSum;
    }

    public void setMonthlyLumpSum(double monthlyLumpSum) {
        this.monthlyLumpSum = monthlyLumpSum;
    }

    public double getProfitForProduct() {
        return profitForProduct;
    }

    public void setProfitForProduct(double profitForProduct) {
        this.profitForProduct = profitForProduct;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "monthlyLumpSum=" + monthlyLumpSum +
                ", profitForProduct=" + profitForProduct +
                '}';
    }
}
