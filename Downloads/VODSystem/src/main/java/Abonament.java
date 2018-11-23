public class Abonament {
    private double price;
    private int numberOfDevices;
    private int rozdzielczosc;

    public static final Abonament BASIC = new Abonament(1,2,3);
    public static final Abonament FAMILY= new Abonament(4,5,6);
    public static final Abonament PREMIUM = new Abonament(7,8,9);

    private Abonament(double price, int numberOfDevices, int rozdzielczosc) {
        this.price = price;
        this.numberOfDevices = numberOfDevices;
        this.rozdzielczosc = rozdzielczosc;
    }
}
