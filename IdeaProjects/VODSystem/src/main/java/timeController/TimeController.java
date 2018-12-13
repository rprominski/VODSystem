package timeController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class TimeController {
    private static TimeController ourInstance = new TimeController();
    private Date beginning;
    private int dayTime = 1000;
    public static TimeController getInstance() {
        return ourInstance;
    }

    private TimeController() {
        beginning = new Date();
    }

    public static TimeController getOurInstance() {
        return ourInstance;
    }

    private long getTimeFromStart() {
        return new Date().getTime() - beginning.getTime();
    }

    public static void setOurInstance(TimeController ourInstance) {
        TimeController.ourInstance = ourInstance;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public int getDayTime() {
        return dayTime;
    }

    public void setDayTime(int dayTime) {
        this.dayTime = dayTime;
    }

    public Date getSimulationDate() {
        int diffrence = (int) (new Date().getTime() - beginning.getTime());
        return addDays(new Date(),diffrence / dayTime);
    }

    public Date getRandomDateInFewDays(int days) {
        return addDays(getSimulationDate(),Math.abs(new Random().nextInt() % days));
    }

    public Date addDays(Date date, int days) {
        LocalDateTime newDate = LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusDays(days);
        return Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}
