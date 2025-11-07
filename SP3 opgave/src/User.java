import java.util.ArrayList;
import java.util.Scanner;

//TODO: Opret username og password i en fil
//TODO: Opret watchedList og watchLaterList
//TODO: Implementer gettes for listerne
//TODO: Slette fra watchLaterList

public class User {
    private String name;
    private String password;
    private ArrayList<Movie> watchedList = new ArrayList<>();
    private ArrayList<Movie> watchLaterList = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //Vælg mellem at logge ind eller lave ny bruger
    private boolean loginOrRegister(Scanner input) {
        while (true) {
            int chosenInput = input.nextInt();
            input.nextLine();
            //Login
            if (chosenInput == 1) {
                //TODO: få metoden til at skrive "Skriv dit brugernavn for at logge ind"
                //TextUI
                name = input.nextLine();
                //TODO: få metoden til at skrive "skriv adgangskode"
                //TextUI
                password = input.nextLine();
                return false;
            }
            //Opret bruger
            else if (chosenInput == 2) {
                //TODO: Opret navn
                //TextUI
                name = input.nextLine();
                //TODO: Opret password
                //TextUI
                password = input.nextLine();
                String msgToUser = addUser(name, password);
                //TODO: udskriv msgToUser
                //TextUI
                return false;

            } else {
                //TODO: Giv fejlmeddelselse
                //TextUI
            }

        }
    }

    private String addUser(String name, String password) {
        this.name = name;
        this.password = password;
        //TODO: Return en success eller failure
        //return TextUI
        //Placeholder
        return name;
    }

    public void addWatchedMovies(Movie movie) {
        if (movie == null) return;
        watchedList.add(movie);


    }

}
