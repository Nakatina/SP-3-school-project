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
    TextUI ui = new TextUI();
    FileHandler fileHandler = new FileHandler();
    private String username;
    private String password;
    Path filePath = fileHandler.getFile("SP3 opgave/", "data/", "users.txt");

    public User(FileHandler fh) {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createUserFiles(String nameOfUser) {

        Path watchedFilePath = fileHandler.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watched movies.txt");
        Path watchLaterFilePath = fileHandler.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watch later movies.txt");
        fileHandler.createFileAndPath(watchedFilePath);
        fileHandler.createFileAndPath(watchLaterFilePath);
    }

    public boolean createUsernameAndPassword(String username, String password, FileHandler fh) {
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

    public boolean authenticateUser(String username, String password, FileHandler fh) {

        return fh.checkMatchFile(filePath, 0, username, 1, password, 2);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
