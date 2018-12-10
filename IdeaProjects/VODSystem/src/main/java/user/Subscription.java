package user;

public class Subscription {
    private double price;
    private int numberOfDevices;
    private int resolution;

    private Subscription(double price, int numberOfDevices, int resolution) {
        this.price = price;
        this.numberOfDevices = numberOfDevices;
        this.resolution = resolution;
    }

    public static final Subscription BASIC = new Subscription(1,2,3);
    public static final Subscription FAMILY= new Subscription(4,5,6);
    public static final Subscription PREMIUM = new Subscription(7,8,9);
}
