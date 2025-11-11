import util.*;

public abstract class Media {
    String title;
    int releaseYear;
    double rating;
    String category;
    TextUI textUI = new TextUI();
    FileHandler fileHandler = new FileHandler();
//konstructor
    public Media(String title, int releaseYear, double rating, String category) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
    }

    public Media() {

    }
public int chooseWatchOrAdd(){
        return textUI.promptNumeric("Vil du se filmen nu, eller tilføje den til se senere?", "\n1. Se film", "2. Tilføj film til se senere");
}
    // Getters
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }
    public String getCategory() { return category; }

    public abstract void play(); // Play method to be overridden
}


