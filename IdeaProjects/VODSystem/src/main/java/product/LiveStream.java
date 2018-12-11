package product;

import java.util.Date;

public class LiveStream extends Product{
    private Date startDate;
    private Promotion promotion;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + "startDate: " + startDate + '\n' +
                "promotion=" + promotion + '\n';

    }
}
