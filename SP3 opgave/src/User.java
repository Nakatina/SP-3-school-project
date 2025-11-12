import util.TextUI;
import util.FileHandler;

import java.nio.file.Path;
import java.util.ArrayList;


//TODO: Opret username og password i en fil TJEK
//TODO: Opret watchedList og watchLaterList
//TODO: Implementer gettes for listerne
//TODO: Slette fra watchLaterList

public class User {
    private ArrayList<Movie> watchedList = new ArrayList<>();
    private ArrayList<Movie> watchLaterList = new ArrayList<>();

    private static final TextUI ui = new TextUI();
    private final FileHandler fh;
    private final String username;
    private String password;


    public User(FileHandler fh, String username, String password) {
        this.username = username;
        this.password = password;
        this.fh = fh;
    }

    public void createUserFiles(String nameOfUser) {

        Path watchedFilePath = fh.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watched movies.txt");
        Path watchLaterFilePath = fh.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watch later movies.txt");
        fh.createFileAndPath(watchedFilePath);
        fh.createFileAndPath(watchLaterFilePath);
    }

    public static boolean createUsernameAndPassword(String username, String password, FileHandler fh) {

        Path filePath = fh.getFile("SP3 opgave/", "data/", "users.txt");

        String usernameAndPassword = username + ";" + password;
        if (!fh.stringFileWriter(filePath, usernameAndPassword)) {
            ui.displayMsg("Noget gik galt ved oprettelse af bruger");
            return false;
        }
        return true;
    }

    public void addWatchedMovies(Movie movie) {
        if (movie == null) return;
        watchedList.add(movie);


    }

    public static boolean authenticateUser(String username, String password, FileHandler fh) {

        Path filePath = fh.getFile("SP3 opgave/", "data/", "users.txt");

        return fh.checkMatchFile(filePath, 0, username, 1, password, 2);
    }

    public String getUsername() {
        return username;
    }
    /*
    public void setUsername(String username) {
        this.username = username;
    }
*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
