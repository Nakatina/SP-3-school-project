public abstract class Media {
    String title;
    int releaseYear;
    double rating;
    String category;

//konstructor
    public Media(String title, int releaseYear, double rating, String category) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
    }

    public Media() {

    }

    // Getters
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }
    public String getCategory() { return category; }

    public abstract void play(); // Play method to be overridden
}


