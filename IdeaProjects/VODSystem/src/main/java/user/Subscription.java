package user;

import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamIntValue;

import java.io.Serializable;

public class Subscription implements Serializable {
    @PodamDoubleValue(minValue = 1.0, maxValue = 100.0)
    private double price;
    @PodamIntValue(minValue = 1, maxValue = 20)
    private int numberOfDevices;
    @PodamIntValue
    private int resolution;

    public Subscription(double price, int numberOfDevices, int resolution) {
        this.price = price;
        this.numberOfDevices = numberOfDevices;
        this.resolution = resolution;
    }

    static enum Type {
        BASIC, FAMILY,PREMIUM
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(int numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}
