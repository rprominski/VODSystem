package product;

import product.Product;

import java.util.ArrayList;

public class Series extends Product {
    private Season season;
    private ArrayList<Episode> Episodes;


    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public ArrayList<Episode> getEpisodes() {
        return Episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        Episodes = episodes;
    }

    @Override
    public String toString() {
        return super.toString() + "season: " +  season + '\n' +
                ", Episodes=" + Episodes + '\n';

    }
}
