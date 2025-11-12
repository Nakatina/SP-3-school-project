import util.TextUI;
import util.FileHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


//TODO: Opret username og password i en fil TJEK
//TODO: Opret watchedList og watchLaterList
//TODO: Implementer gettes for listerne
//TODO: Slette fra watchLaterList

/**
 * Repræsenterer en bruger i streamingtjenesten.
 * <p>
 * Klassen indeholder brugerens oplysninger, personlige film­lister
 * og metoder til at oprette brugerens mapper og autentificere login.
 * <p>
 * {@link User}-objekter bindes normalt til en {@link FileHandler} og
 * håndteres gennem {@link ConsoleApp}.
 */
public class User {
    /**
     * Liste over film som brugeren har set.
     *
     * @deprecated Denne liste anvendes ikke længere direkte.
     * Brug i stedet en filbaseret eller database-baseret løsning
     * via {@link FileHandler#createFileAndPath(Path)}.
     */
    @Deprecated
    private ArrayList<Movie> watchedList = new ArrayList<>();

    /**
     * Liste over film som brugeren har markeret til senere visning.
     *
     * @deprecated Denne liste er forældet.
     * Brug i stedet en ekstern lagringsmetode (fx i filsystemet).
     */
    @Deprecated
    private ArrayList<Movie> watchLaterList = new ArrayList<>();

    /** Fælles tekst-UI til udskrivning af beskeder. */
    private static final TextUI ui = new TextUI();

    /** Filhåndteringsobjekt der anvendes til at gemme og læse filer. */
    private final FileHandler fh;

    /** Brugerens unikke brugernavn. */
    private final String username;

    /** Brugerens adgangskode. */
    private String password;

    /**
     * Opretter et nyt {@link User}-objekt med tilknyttet {@link FileHandler}.
     *
     * @param fh        den {@link FileHandler} der skal bruges til filadgang
     * @param username  brugerens brugernavn
     * @param password  brugerens adgangskode
     */
    public User(FileHandler fh, String username, String password) {
        this.username = username;
        this.password = password;
        this.fh = fh;
    }

    /**
     * Opretter brugerens personlige mapper og tekstfiler
     * til henholdsvis sete film og film markeret til senere.
     * <p>
     * Filerne bliver placeret i:
     * <pre>
     * SP3 opgave/data/userdata/&lt;brugernavn&gt;/watched movies.txt
     * SP3 opgave/data/userdata/&lt;brugernavn&gt;/watch later movies.txt
     * </pre>
     *
     * @param nameOfUser navnet på brugeren (bruges til filstien)
     */
    public void createUserFiles(String nameOfUser) {

        Path watchedFilePath = fh.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watched movies.txt");
        Path watchLaterFilePath = fh.getFile("SP3 opgave/", "data/", "userdata/", nameOfUser, "watch later movies.txt");
        fh.createFileAndPath(watchedFilePath);
        fh.createFileAndPath(watchLaterFilePath);
    }

    /**
     * Opretter en ny bruger i brugerfilen.
     * <p>
     * Tilføjer linjen {@code "brugernavn;password"} i
     * <pre>SP3 opgave/data/users.txt</pre>.
     *
     * @param username  brugernavn der skal oprettes
     * @param password  adgangskode
     * @param fh        filhåndtering der bruges til at skrive
     * @return {@code true} hvis brugeren blev gemt korrekt, ellers {@code false}
     */
    public static boolean createUsernameAndPassword(String username, String password, FileHandler fh) {

        Path filePath = fh.getFile("SP3 opgave/", "data/", "users.txt");

        String usernameAndPassword = username + ";" + password;
        if (!fh.stringFileWriter(filePath, usernameAndPassword)) {
            ui.displayMsg("Noget gik galt ved oprettelse af bruger");
            return false;
        }
        return true;
    }

    /** Tilføjer film brugeren har set
     * @deprecated Filmene gemmes i en fil via metoder i {@link Movie}.
     *  *
     *  * @see Movie#doMovie()
     *  * @see Movie#addMovieToFiles(Path, String, String)
     *
     * @param movie
     */
    @Deprecated
    public void addWatchedMovies(Movie movie) {
        if (movie == null) return;
        watchedList.add(movie);


    }

    /**
     * Validerer et loginforsøg ud fra brugernavn og adgangskode.
     * <p>
     * Matcher på linjer i {@code users.txt}, hvor kolonne 0 = brugernavn
     * og kolonne 1 = password.
     *
     * @param username  brugernavnet der skal tjekkes
     * @param password  adgangskoden der skal tjekkes
     * @param fh        {@link FileHandler} der anvendes til filopslag
     * @return {@code true} hvis kombinationen findes i filen, ellers {@code false}
     */
    public static boolean authenticateUser(String username, String password, FileHandler fh) {

        Path filePath = fh.getFile("SP3 opgave/", "data/", "users.txt");

        return fh.checkMatchFile(filePath, 0, username, 1, password, 2);
    }

    /**
     * Henter brugerens brugernavn.
     *
     * @return brugernavn
     */
    public String getUsername() {
        return username;
    }
    /**
     * Setter for brugeres username.
     * Brugerens username skal ikke kunne sættes direkte med en metode, men ændres i fil <code>SP3 opgave/data/users.txt</code> og mappe <code> SP3 opgave/data/userdata</code>.
     * @param username
     */
    /*
    public void setUsername(String username) {
        this.username = username;
    }
*/
    /**
     * Henter brugeren password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for brugerens password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
