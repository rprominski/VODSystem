package product;

public class Season {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Season{" +
                "number=" + number +
                '}';
    }
}
