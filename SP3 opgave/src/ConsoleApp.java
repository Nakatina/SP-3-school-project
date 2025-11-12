//Initialiserer appen
//Alt der bliver kaldt fra Main skal komme herfra

import util.TextUI;
import util.FileHandler;

import java.nio.file.Path;


/**
 * Hoved-klassen for konsolapplikationen.
 * <p>
 * Ansvar:
 * <ul>
 *   <li>Start-flow (velkomst, login/registrering, hovedmenu)</li>
 *   <li>Styrer den “aktive” bruger og film-funktionalitet</li>
 *   <li>Delegerer filhåndtering til {@link FileHandler}</li>
 *   <li>Delegerer afspilning/søgning til {@link Movie} og mediebibliotek fra {@code StreamingService}</li>
 * </ul>
 */
public class ConsoleApp {
    /** Tekstbaseret brugergrænseflade til prompts og beskeder. */
    private TextUI ui = new TextUI();

    /** Fælles filhåndtering for hele app’en (én instans). */
    private FileHandler fh = new FileHandler();

    /** Den aktuelt loggede ind bruger (sættes efter succesfuldt login). */
    private User user;

    /** Film-funktionalitet bundet til den aktive bruger. */
    private Movie movie;


   // private StreamingService Fletnix = new StreamingService();
    /**
     * Filsti til den overordnede filmliste.
     * Bemærk: path bygges relativt til projektets rod via {@link FileHandler#getFile(String...)}.
     */
    Path filePathFilm = fh.getFile("SP3 opgave/" + "data/" + "film.txt");

    /**
     * Starter programmet og håndterer start-flowet (velkomst → login/registrering → hovedmenu).
     * Kalder sig selv igen, hvis brugeren ikke vælger login/registrering korrekt.
     */
    public void startProgram() {
        // Fletnix.addToLibrary();
        startMsg();
        if (checkDoLoginOrRegister() == false) {
            startProgram();
            return;
        }
        doCategoryOrMovie();
    }
    /**
     * Viser hovedmenuen efter login og sender brugeren videre til kategori- eller søgeflow.
     * Valgmuligheder:
     * <ol>
     *   <li>Vælg kategori</li>
     *   <li>Søg efter film</li>
     *   <li>Afslut</li>
     * </ol>
     */
    private void doCategoryOrMovie() {
        int choice = ui.promptNumeric("\n1. Vælg kategori", "2. Søg efter film", "3. Afslut");
        while ((choice != 1) && (choice != 2) && (choice != 3)) {
            ui.displayMsg("Indtast korrekt valg: ");
        }
        if (choice == 1) {
            doCategory();
        }
        if (choice == 2) {
            movie.doMovie();
        }
        return;

    }
    /**
     * Placeholder for kategoriflow.
     */
    private void doCategory() {
    }

    /**
     * Viser login/registrering/afslut-menu og håndterer valg-loopet.
     *
     * @return {@code true} hvis brugeren vælger login/registrering og flowet fortsætter, ellers {@code false}
     */
    private boolean checkDoLoginOrRegister() {


        int choice = ui.promptNumeric("Indtast dit valg: ");

        while (choice != 1 && choice != 2 && choice != 3) {
            choice = ui.promptNumeric("Indtast korrekt valg: ");
        }
        if (choice == 1 || choice == 2) {

            return doLoginOrRegister(choice);
        }
        ui.displayMsg("Farvel...");
        return false;
    }

    /**
     * Dispatcher for login eller registrering baseret på brugerens valg.
     *
     * @param choice 1 = login, 2 = registrer
     * @return {@code true} hvis den valgte handling lykkes, ellers {@code false}
     */
    private boolean doLoginOrRegister(int choice) {
        if (choice == 1) {
            return doLogin();
        } else if (choice == 2) {
            return doRegister();
        }
        return false;
    }

    /**
     * Login-flow.
     * <ol>
     *   <li>Prompt for brugernavn og adgangskode</li>
     *   <li>Valider via {@link User#authenticateUser(String, String, FileHandler)} (statisk)</li>
     *   <li>Ved succes: opret aktiv {@link User} og {@link Movie}, opret evt. brugerens mapper/filer</li>
     * </ol>
     *
     * @return {@code true} ved succesfuldt login, ellers {@code false}
     */
    public boolean doLogin() {
        String usernameInput = ui.promptText("Skriv brugernavn");
        String passwordInput = ui.promptText("Skriv adgangskode");

        if (User.authenticateUser(usernameInput, passwordInput, fh)) {
            ui.displayMsg("Login successful!");
            createNewUserAndMovie(usernameInput, passwordInput);
            user.createUserFiles(usernameInput);
            return true;
        }
        ui.displayMsg("Brugernavn eller password forkert!");
        return false;
    }

    /**
     * Opretter og binder den aktive {@link User} og tilhørende {@link Movie}-instans
     * til denne {@code ConsoleApp}. Der genbruges samme {@link FileHandler} til begge.
     *
     * @param username Brugernavn der skal associeres med sessionen
     * @param password Adgangskode der skal associeres med sessionen
     */
    private void createNewUserAndMovie(String username, String password) {
        this.user = new User(fh, username, password);
        this.movie = new Movie(fh, user);
    }
/*
    public void doSearch() {
        boolean found = false;

        String searchWord = ui.promptText("Hvilken film vil du se? Søg efter filmtitel:").toLowerCase();

        for (Media m : Fletnix.getMediaLibrary()) {
            if (m.getTitle().equalsIgnoreCase(searchWord)) {
                ui.displayMsg("Afspiller nu filmen " + searchWord);
                found = true;
            }
        }
        if (!found) {
            ui.displayMsg("Ingen film med titlen " + searchWord + " blev fundet. Prøv igen.");
            doSearch();
        }
    }
*/

    /**
     * Registrerings-flow.
     * <ol>
     *   <li>Prompt for brugernavn og adgangskode</li>
     *   <li>Forsøg at oprette via {@link User#createUsernameAndPassword(String, String, FileHandler)} (statisk)</li>
     *   <li>Ved succes: vis besked og hop til login-flow</li>
     * </ol>
     *
     * @return {@code true} hvis registrering + efterfølgende login lykkes, ellers {@code false}
     */
    private boolean doRegister() {
        String usernameInput = ui.promptText("Opret brugernavn");
        String passwordInput = ui.promptText("Opret adgangskode");
        boolean b = User.createUsernameAndPassword(usernameInput, passwordInput, fh);
        if (b) {
            ui.displayMsg("Register succesfuld!");
            return doLogin();
        } else ui.displayMsg("Noget gik galt med registrering.");
        return false;
    }

    /**
     * Viser startbeskeden/for-menuen.
     */
    private void startMsg() {
        ui.displayMsg("==== Velkommen ====", "\n1: Login", "2: Opret bruger", "3: Afslut");
    }


}
