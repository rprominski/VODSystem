package product;

import user.Distributor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

public abstract class Product {
    private BufferedImage photo;
    private String name;
    private String description;
    private Date productionDate;
    private int durationInMinutes;
    private Distributor distributor;
    private ArrayList<String> CountriesOfProduction;
    private int rate;
    private double price;
    private int views;
    private int id;

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

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributorName) {
        this.distributor = distributorName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addView() {
        views++;
    }

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "description: " + description + '\n' +
                "productionDate: " + productionDate + '\n' +
                "durationInMinutes: " + durationInMinutes + '\n' +
                "distributorName: " + distributor + '\n' +
                "CountriesOfProduction: " + CountriesOfProduction + '\n' +
                "rate: " + rate + '\n' +
                "price: " + price + '\n' +
                "views: " + views + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
