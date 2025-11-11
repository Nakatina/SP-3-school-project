import java.util.ArrayList;

public abstract class Media {
    // Felter som alle medier har
    protected String title;
    protected int releaseYear;
    protected double rating;
    protected ArrayList<String> category;

    //konstructor
    public Media(String title, int releaseYear, double rating, ArrayList<String> category) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
    }

    // Getters (bruges til at hente data andre steder i programmet)
    public String getTitle() {
        return title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public double getRating() {
        return rating;
    }
    public String getCategory() {
        return category;
    }
// Abstrakt metode som skal implemeteres i underklasser (Movies og Series)
    public abstract void play();
}


