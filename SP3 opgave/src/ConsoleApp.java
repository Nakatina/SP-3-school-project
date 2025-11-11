//Initialiserer appen
//Alt der bliver kaldt fra Main skal komme herfra

import util.TextUI;
import util.FileHandler;

import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleApp {
    TextUI ui = new TextUI();
    FileHandler fh = new FileHandler();
    User user = new User(fh);
    StreamingService Fletnix = new StreamingService();
    Movie movie = new Movie();

    Path filePathFilm = fh.getFile("SP3 opgave/" + "data/" + "film.txt");

    public void startProgram() {
        // Fletnix.addToLibrary();
        startMsg();
        if (!checkDoLoginOrRegister()) {
            startProgram();
            return;
        }
        doCategoryOrMovie();
    }

    private void doCategoryOrMovie() {
        int choice = ui.promptNumeric("1. Vælg kategori", "2. Søg efter film", "3. Afslut");
        while ((choice != 1) && (choice != 2) && (choice != 3)) {
            ui.displayMsg("Indtast korrekt valg: ");
        }
        if (choice == 1) {
            doCategory();
        }
        if (choice == 2) {
            movie.doMovie();
        } return;

    }

    private void doCategory() {

    }

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

    private boolean doLoginOrRegister(int choice) {
        if (choice == 1) {
            return doLogin();
        } else if (choice == 2) {
            return doRegister();
        }
        return false;
    }

    public boolean doLogin() {
        String usernameInput = ui.promptText("Skriv brugernavn");
        String passwordInput = ui.promptText("Skriv adgangskode");

        if (user.authenticateUser(usernameInput, passwordInput, fh)) {
            ui.displayMsg("Login successful!");
            return true;
        }
        ui.displayMsg("Brugernavn eller password forkert!");
        return false;
    }

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

    private boolean doRegister() {
        String usernameInput = ui.promptText("Opret brugernavn");
        String passwordInput = ui.promptText("Opret adgangskode");
        boolean b = user.createUsernameAndPassword(usernameInput, passwordInput, fh);
        if (b) {
            ui.displayMsg("Register succesfuld!");
            return doLogin();
        } else ui.displayMsg("Noget gik galt med registrering.");
        return false;
    }

    private void startMsg() {
        ui.displayMsg("==== Velkommen ====", "\n1: Login", "2: Opret bruger", "3: Afslut");
    }


}
