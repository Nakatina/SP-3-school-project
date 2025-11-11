import java.nio.file.Path;

import util.*;

public class Movie extends Media {
    TextUI textUI = new TextUI();
    FileHandler fh = new FileHandler();
    Path filePathFilm = fh.getFile("SP3 opgave/" + "data/" + "film.txt");
    int duration; //filmens længde

    //TODO indlæser fra txt/csv Filer.
    //Constructor!
    public Movie(String title, int releaseYear, double rating, String category) {
        super(title, releaseYear, rating, category);
    }
    public Movie(){
        super();

    }
    public void doMovie() {
        String chosenMovie = textUI.promptText("Hvilken film vil du se?");

        if (fh.checkFile(filePathFilm, chosenMovie, 4, 0)) {
            var choice = chooseWatchOrAdd();
            if (choice == 1) {
                textUI.displayMsg(chosenMovie + " afspilles nu...");
            } else if (choice == 2) {
                textUI.displayMsg("Lav metode der tilføjer filmen til en liste");
            }
        } else {
            textUI.displayMsg("Filmen findes ikke");
        }
    }
    @Override
    public void play() {
        textUI.displayMsg(title + " afspilles nu...");
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

}
