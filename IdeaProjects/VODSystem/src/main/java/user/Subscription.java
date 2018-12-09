package user;

public class Subscription {
    private double price;
    private int numberOfDevices;
    private int rozdzielczosc;

    private Subscription(double price, int numberOfDevices, int rozdzielczosc) {
        this.price = price;
        this.numberOfDevices = numberOfDevices;
        this.rozdzielczosc = rozdzielczosc;
    }

    public static final Subscription BASIC = new Subscription(1,2,3);
    public static final Subscription FAMILY= new Subscription(4,5,6);
    public static final Subscription PREMIUM = new Subscription(7,8,9);
}
