package user;

import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamIntValue;

public class Contract {
    @PodamDoubleValue(minValue = 100, maxValue = 3000)
    private double monthlyLumpSum;
    @PodamDoubleValue(minValue = 1,maxValue = 20)
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
