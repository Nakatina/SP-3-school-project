import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Movie extends Media {
    int duration; //filmens længde
    //TODO indlæser fra txt/csv Filer.
    //Constructor!
    public Movie(String title, int releaseYear, double rating, String category){
        super(title, releaseYear, rating, category);
    }

    @Override
    public void play(){
        System.out.println(title + " afspilles nu...");
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

}
