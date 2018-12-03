package product;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

public abstract class Product {
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

    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public ArrayList<String> getCountriesOfProduction() {
        return CountriesOfProduction;
    }

    public void setCountriesOfProduction(ArrayList<String> countriesOfProduction) {
        CountriesOfProduction = countriesOfProduction;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
