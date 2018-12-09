package product;

import java.util.ArrayList;
import java.util.Date;

public class Film extends WatchableObject {
    private ArrayList<String> trailers;
    private Date expiryDate;
    private Promotion promotion;

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + "Film{" +
                "trailers=" + trailers +
                ", expiryDate=" + expiryDate +
                ", promotion=" + promotion +
                '}';
    }
}
