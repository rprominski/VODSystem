package timeController;

import uk.co.jemos.podam.common.PodamExclude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

/**
 * Class converting real time into time in simulation
 *
 */
public class TimeController {
    private static TimeController ourInstance = new TimeController();
    @PodamExclude
    /**
     * Date of start of simulation using in calculating simulation date.
     */
    private Date beginning;
    @PodamExclude
    /**
     * The length of simulated day in miliseconds
     */
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

    /**
     *
     * @return Real time from start of simulation
     */
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

    public int getMonth() {
        return getRawSimulationDate().getMonth();
    }

    public void setDayTime(int dayTime) {
        this.dayTime = dayTime;
    }

    /**
     *
     * @return Calculated date of simulation
     */
    public Date getRawSimulationDate() {
        int diffrence = (int) (new Date().getTime() - beginning.getTime());
        return addDays(new Date(),diffrence / dayTime);
    }

    public Date getRandomDateInFewDays(int days) {
        return addDays(getRawSimulationDate(),Math.abs(new Random().nextInt() % days));
    }

    /**
     * Used to set date of production of product.
     * @return Random date in past.
     */
    public Date getRandomPastDay() {
        return addDays(new Date(),-(Math.abs(new Random().nextInt() % 10000)));
    }

    /**
     * Method to get the date in the future.
     * @param date starting date
     * @param days how many days you want to add.
     * @return date after the number of given days
     */
    public Date addDays(Date date, int days) {
        LocalDateTime newDate = LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusDays(days);
        return Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Format date to pretty string.
     * @param date date to format
     * @return date in format "YYYY-MM-DD".
     */
    public String formatDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String formatedDate =
                Integer.toString(localDate.getYear()) + "-" +
                Integer.toString(localDate.getMonthValue()) + "-" +
                Integer.toString(localDate.getDayOfMonth());
        return formatedDate;
    }
}
