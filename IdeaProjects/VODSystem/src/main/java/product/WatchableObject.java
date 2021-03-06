package product;

import java.util.List;
/**
 * Stores information shared by products.
 */
public abstract class WatchableObject extends Product {
    private Genre genre;
    private List<Actor> actors;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return super.toString() + "genre: " + genre + "\n" +
                "actors: " + actors + "\n";
    }
}
