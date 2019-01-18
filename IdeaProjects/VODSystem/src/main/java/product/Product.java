package product;

import javafx.util.Pair;
import product.podamStrategies.BufferedImageStrategy;
import timeController.TimeController;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStrategyValue;
import user.Distributor;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Product implements Serializable {
    @PodamStrategyValue(BufferedImageStrategy.class)
    private transient BufferedImage photo;
    private String name;
    private String description;
    @PodamExclude
    private Date productionDate;
    @PodamIntValue(minValue = 60, maxValue = 180)
    private int durationInMinutes;
    @PodamExclude
    private Distributor distributor;
    private List<String> countriesOfProduction;
    @PodamIntValue(minValue = 0, maxValue = 100)
    private int rate;
    @PodamDoubleValue(minValue = 0, maxValue = 100)
    private double price;
    @PodamIntValue(numValue = "0")
    private int viewsInDay;
    @PodamExclude
    private CircularVector<Pair<String, Integer>> views;
    @PodamExclude
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

    public List<String> getCountriesOfProduction() {
        return countriesOfProduction;
    }

    public void setCountriesOfProduction(List<String> countriesOfProduction) {
        this.countriesOfProduction = countriesOfProduction;
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

    public int getViewsInDay() {
        return viewsInDay;
    }

    public void setViewsInDay(int viewsInDay) {
        this.viewsInDay = viewsInDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void addView() {
        viewsInDay++;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        updateViews(new Pair<String,Integer>(TimeController.getInstance().formatDate(
                TimeController.getInstance().getRawSimulationDate()),viewsInDay));
    }

    public List<Pair<String, Integer>> getViews() {
        return views;
    }

    public void setViews(CircularVector<Pair<String, Integer>> views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "description: " + description + '\n' +
                "productionDate: " + productionDate + '\n' +
                "durationInMinutes: " + durationInMinutes + '\n' +
                "distributorName: " + distributor.getName() + '\n' +
                "countriesOfProduction: " + countriesOfProduction + '\n' +
                "rate: " + rate + '\n' +
                "price: " + price + '\n';
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

    public Product() {
        views = new CircularVector<>(30);
        views.add(new Pair<String, Integer>(
                TimeController.getInstance().formatDate(TimeController.getInstance().getRawSimulationDate()),0));
        productionDate = TimeController.getInstance().getRandomPastDay();

    }

    public synchronized void updateViews(Pair<String,Integer> view) {
        if (views.get(views.size() - 1).getKey().equals(view.getKey())) {
            Pair<String, Integer> p = new Pair<String, Integer>(views.get(views.size() - 1).getKey(), views.get(views.size() - 1).getValue() + 1);
            views.set(views.size() - 1, p);
        } else {
            Pair<String, Integer> p = views.get(views.size() - 1);
            view = new Pair<>(view.getKey(),view.getValue() + p.getValue());
            views.add(view);
        }
        viewsInDay = 0;
    }
}
