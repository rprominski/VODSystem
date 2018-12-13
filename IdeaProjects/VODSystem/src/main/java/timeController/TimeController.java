package timeController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class TimeController {
    private static TimeController ourInstance = new TimeController();
    private Date beginning;
    private int dayTime = 1000;
    private SimpleDateFormat format;
    public static TimeController getInstance() {
        return ourInstance;
    }

    private TimeController() {
        beginning = new Date();
        format = new SimpleDateFormat("yyyy-mm-dd");
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

    public Date getRawSimulationDate() {
        int diffrence = (int) (new Date().getTime() - beginning.getTime());
        return addDays(new Date(),diffrence / dayTime);
    }

    public Date getRandomDateInFewDays(int days) {
        return addDays(getRawSimulationDate(),Math.abs(new Random().nextInt() % days));
    }

    public Date addDays(Date date, int days) {
        LocalDateTime newDate = LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusDays(days);
        return Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String formatDate(Date date) {
        System.out.println(date);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String formatedDate =
                Integer.toString(localDate.getYear()) + "-" +
                Integer.toString(localDate.getMonthValue()) + "-" +
                Integer.toString(localDate.getDayOfMonth());
        return formatedDate;
    }
}
