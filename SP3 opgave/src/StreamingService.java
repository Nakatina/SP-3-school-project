import util.FileHandler;
import util.TextUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class StreamingService {
    //TODO: Opret liste af brugere
    //TODO: Opret liste af medier
    //TODO: Implementer registrer og login
    //TODO:Implemteter search title pg kategori + udskriv fejbesked hvis ikke findes
    //TODO: Print velkommen login / opret dig.

    private ArrayList<Media> mediaLibrary;
    private ArrayList<User> users;
    FileHandler fileHandler = new FileHandler();
    Path filePath = fileHandler.getFile("SP3 opgave/", "data/", "film.txt");
    TextUI textUI = new TextUI();

    public StreamingService() {
        this.mediaLibrary = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public void addToLibrary(Path filePath) {
        List<String> lines = fileHandler.returnFile(filePath);

        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length > 4) {
                textUI.displayMsg(parts[0] + " har for mange parametre!");
            }
            String title = (parts.length > 0) ? parts[0].trim() : "Ukendt titel";

            String yearString = (parts.length > 1) ? parts[1].trim() : "Ukendt årstal";
            int year = Integer.parseInt(yearString);

            String category = (parts.length > 2) ? parts[2].trim() : "Ukendt kategori";
            
            String ratingString = (parts.length > 3) ? parts[3].trim() : "Ukendt rating";
            double rating = Double.parseDouble(ratingString.replaceAll(",", "."));

            // Movie movie = new Movie(title, year, rating, category);
            // mediaLibrary.add(movie);
        }
    }

    public ArrayList<Media> getMediaLibrary() {
        return mediaLibrary;
    }
}
