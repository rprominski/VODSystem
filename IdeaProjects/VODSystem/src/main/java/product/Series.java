package product;

import java.util.ArrayList;

/**
 * Stores information about series.
 */
public class Series extends Product {
    private Season season;
    private ArrayList<Episode> episodes;


    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return super.toString() + "season: " +  season + '\n' +
                ", episodes=" + episodes + '\n';

    }
}
