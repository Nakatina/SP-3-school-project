import util.TextUI;
import util.FileHandler;

import java.util.ArrayList;


//TODO: Opret username og password i en fil
//TODO: Opret watchedList og watchLaterList
//TODO: Implementer gettes for listerne
//TODO: Slette fra watchLaterList

public class User {
    private ArrayList<Movie> watchedList = new ArrayList<>();
    private ArrayList<Movie> watchLaterList = new ArrayList<>();
    TextUI ui = new TextUI();
    private String username;
    private String password;
    String filePath = "data/users.txt";

    public User(FileHandler fh) {
    }

    public void createUsernameAndPassword(String username, String password, FileHandler fh) {
        fh.twoStringFileWriter(username, password, filePath);
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
