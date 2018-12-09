package product;

import java.util.Date;

public class Promotion {
    private Date startDate;
    private Date endDate;
    private int percantageDiscount;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPercantageDiscount() {
        return percantageDiscount;
    }

    public void setPercantageDiscount(int percantageDiscount) {
        this.percantageDiscount = percantageDiscount;
    }
}
