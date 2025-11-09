//Initialiserer appen
//Alt der bliver kaldt fra Main skal komme herfra

import util.TextUI;
import util.FileHandler;

public class ConsoleApp {
    TextUI ui = new TextUI();
    FileHandler fh = new FileHandler();
    User user = new User(fh);
    util.FileHandler fileHandler = new util.FileHandler();

    public void startProgram() {
        startMsg();
        if (!checkDoLoginOrRegister()) {
            return;
        }


    }

    private boolean checkDoLoginOrRegister() {


        int choice = doLoginOrRegister(ui.promptNumeric("Indtast dit valg: "));

        while (choice != 1 && choice != 2 && choice != 3) {
            choice = doLoginOrRegister(ui.promptNumeric("Indtast korrekt valg: "));
        }
        if  (choice == 1 || choice == 2) {
            return true;
        }
        ui.displayMsg("Farvel...");
        return false;
    }

    private int doLoginOrRegister(int choice) {

        switch (choice) {
            case 1:
                doLogin();
                break;
            case 2:
                doRegister();
                break;


        }
        return choice;
    }

    public boolean doLogin() {
        String usernameInput = ui.promptText("Skriv brugernavn");
        String passwordInput = ui.promptText("Skriv adgangskode");
        boolean checkNameAndPassword = user.authenticateUser(usernameInput, passwordInput, fh);
        if (checkNameAndPassword) {
            ui.displayMsg("Login successful!");
            return true;
        }
        ui.displayMsg("Brugernavn eller password forkert!");
        return false;

    }

    private void doRegister() {
        String usernameInput = ui.promptText("Opret brugernavn");
        String passwordInput = ui.promptText("Opret adgangskode");
        user.createUsernameAndPassword(usernameInput, passwordInput, fh);
    }

    private void startMsg() {
        ui.displayMsg("==== Velkommen ====");
        ui.displayMsg("\n1: Login");
        ui.displayMsg("2: Opret bruger");
        ui.displayMsg("3: Afslut");
    }

}
