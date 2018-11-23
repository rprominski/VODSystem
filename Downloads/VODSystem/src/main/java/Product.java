import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

abstract class Product {
    private BufferedImage photo;
    private String name;
    private String description;
    private Date productionDate;
    private int durationInMinutes;
    private String distributorName;
    private ArrayList<String> CountriesOfProduction;
    private int rate;
    private double price;
    private int views;
}
