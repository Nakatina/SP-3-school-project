public abstract class Media {
    //TODO: Opret Felter
    //TODO: Lav konstruktør
    //TODO: Tilføj getters
    //TODO: Lav metode play()
    String title;
    int releaseYear;
    double rating;
    String category;

    public Media(String title, int releaseYear, double rating, String category) {
        this.title = title.trim();
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
    }

    // Getters
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }
    public category getCategory() { return category; }

    public abstract void play(); // Play method to be overridden
}


