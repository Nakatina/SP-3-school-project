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
        ui.displayMsg("==== Velkommen ====");
        ui.displayMsg("\n1: Login");
        ui.displayMsg("2: Opret bruger");
        ui.displayMsg("3: Afslut");

        int choice = doLoginOrRegister(ui.promptNumeric("Indtast dit valg: "));

        while (choice != 1 && choice != 2 && choice != 3) {
            choice = doLoginOrRegister(ui.promptNumeric("Indtast korrekt valg: "));
        }

        if (choice == 3) {
            ui.displayMsg("Farvel...");
        }
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

    public void doLogin() {
        String usernameInput = ui.promptText("Skriv brugernavn");
        String passwordInput = ui.promptText("Skriv adgangskode");
        if (user.authenticateUser(usernameInput, passwordInput, fh) == true) ui.displayMsg("Login succesfult!");
        else  {
            ui.displayMsg("Brugernavn eller password forkert!");
        }
    }

    private void doRegister() {
        String usernameInput = ui.promptText("Opret brugernavn");
        String passwordInput = ui.promptText("Opret adgangskode");
        user.createUsernameAndPassword(usernameInput, passwordInput, fh);
    }
}
