package product;

import timeController.TimeController;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

import java.sql.Time;
import java.util.Date;

public class Promotion {
    @PodamExclude
    private Date startDate;
    @PodamExclude
    private Date endDate;
    @PodamIntValue(minValue = 5,maxValue = 50)
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

    public Promotion() {
        startDate = TimeController.getInstance().getRawSimulationDate();
        endDate = TimeController.getInstance().getRandomDateInFewDays(30);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", percantageDiscount=" + percantageDiscount +
                '}';
    }
}
