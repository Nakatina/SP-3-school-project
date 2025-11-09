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
     Path filePath = fileHandler.getFile("SP3 opgave/", "data/","users.txt");

    public User(FileHandler fh) {
    }

    public void createUsernameAndPassword(String username, String password, FileHandler fh) {
       String usernameAndPassword = username + ";" + password;
        fh.stringFileWriter(filePath, usernameAndPassword);
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
