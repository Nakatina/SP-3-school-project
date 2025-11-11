import java.nio.file.Path;

import util.*;

public class Movie extends Media {
    private final TextUI textUI = new TextUI();
    private final FileHandler fh;
    private final User user;
    Path filePathFilm = fh.getFile("SP3 opgave/" + "data/" + "film.txt");
    int duration; //filmens længde
    String username;


    //TODO indlæser fra txt/csv Filer.
    //Constructor!
    public Movie(FileHandler fh, User user, String title, int releaseYear, double rating, String category) {
        super(title, releaseYear, rating, category);
        this.fh = fh;
        this.user = user;
    }
    public Movie(FileHandler fh, User user){
        this.fh = fh;
        this.user = user;

    }
    public void doMovie() {
        String chosenMovie = textUI.promptText("Hvilken film vil du se?");

        if (fh.checkFile(filePathFilm, chosenMovie, 4, 0)) {
            var choice = chooseWatchOrAdd();
            if (choice == 1) {
                textUI.displayMsg(chosenMovie + " afspilles nu...");
            } else if (choice == 2) {
                textUI.displayMsg("Film tilføjet til se senere!");
                addMovieToFiles(fh.getFile(), chosenMovie);
                //Tilføj film til Watch Later fil
            }
        } else {
            textUI.displayMsg("Filmen findes ikke");
        }

        }
    private void addMovieToFiles(Path filePathFilm, String title) {
        Path path = fh.getFile("SP3 opgave", "data", "userdata",user.getUsername(), "watch later movies.txt");
    fileHandler.stringFileWriter(path, title);
    }

    @Override
    public void play() {
        textUI.displayMsg(title + " afspilles nu...");
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

}
