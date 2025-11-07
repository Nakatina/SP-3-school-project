public class Movie extends Media {
    int duration; //filmens længde

    //Konstructor!
    public Movie(String title, int releaseYear, double rating, String category, int duration){
        super(title, releaseYear, rating, category);
        this.duration = duration;
    }

    @Override
    public void play(){
        System.out.println(title + " afspilles nu...");
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

    //TODO indlæser fra txt/csv Filer.
}
