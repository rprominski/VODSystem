package product;

import product.podamStrategies.DateStrategy;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.io.Serializable;
import java.util.Date;

class Episode implements Serializable {
    @PodamStrategyValue(DateStrategy.class)
    private Date startDate;
    @PodamIntValue(minValue = 30, maxValue = 60)
    private int durationInMinutes;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "startDate=" + startDate +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
