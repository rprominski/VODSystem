package product;

import uk.co.jemos.podam.common.PodamIntValue;

import java.util.ArrayList;

public class Film extends WatchableObject {
    private ArrayList<String> trailers;
    @PodamIntValue(minValue = 30,maxValue = 60)
    private int expiryDateInDays;
    private Promotion promotion;

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
    }

    public int getExpiryDateInDays() {
        return expiryDateInDays;
    }

    public void setExpiryDateInDays(int expiryDateInDays) {
        this.expiryDateInDays = expiryDateInDays;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + "trailers: " + trailers + '\n' +
                "expiryDateInDays: " + expiryDateInDays + '\n' +
                "promotion: " + promotion + '\n';
    }
}
