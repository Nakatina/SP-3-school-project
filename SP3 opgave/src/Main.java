import java.io.Console;
import java.io.File;
import util.FileHandler;

import static util.FileHandler.readMoviesFromTxt;

public class Main {

    public static void main(String[] args){
        readMoviesFromTxt("SP3 opgave/data/film.txt");

        ConsoleApp console = new ConsoleApp();
        //TODO: Udskriv velkomsttest
        //TODO: Indlæs brugere og medier
        //TODO: Vis startmenu og håndter brugerindput
        //TODO: Gennemgå håndtering af fejl
        //TODO: SIDTE DEL AF KODNINGEN! Login gemmes i extern fil
        //TODO: VALGTFRIT EXTRA: Grafisk brugerflade
        console.startProgram();




    }

    private static void readMoviesFromTxt(String s) {
    }

}
