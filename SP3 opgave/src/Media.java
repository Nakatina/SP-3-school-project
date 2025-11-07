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
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
    }

    public abstract void play(); // Play method to be overridden
}


