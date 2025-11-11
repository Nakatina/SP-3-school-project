import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import util.TextUI;

public class Movie extends Media {
    TextUI textUI = new TextUI();
    int duration; //filmens længde

    //TODO indlæser fra txt/csv Filer.
    //Constructor!
    public Movie(String title, int releaseYear, double rating, String category) {
    public Movie(String title, int releaseYear, double rating, ArrayList<String> category){
        super(title, releaseYear, rating, category);
        this.duration = duration;
    }

    @Override
    public void play() {
        textUI.displayMsg(title + " afspilles nu...");
    }

    //Hjælpemetode: Lav en kategori
    public static ArrayList<String> parseCategory(String categoryText) {
        ArrayList<String> list = new ArrayList<>();
        if (categoryText == null) return list;
        for (String part : categoryText.split(",")){
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) list.add(trimmed);
        }
        return list;
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

}
