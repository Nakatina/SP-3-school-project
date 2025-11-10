import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StreamingService {
    //TODO: Opret liste af brugere
    //TODO: Opret liste af medier
    //TODO: Implementer registrer og login
    //TODO:Implemteter search title pg kategori + udskriv fejbesked hvis ikke findes
    //TODO: Print velkommen login / opret dig.

    private ArrayList<Media> mediaLibrary;
    private ArrayList<User> users;

    public StreamingService(){
        this.mediaLibrary = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addToLibrary(){
        try(BufferedReader br = new BufferedReader(new FileReader("SP3 opgave/data/film.txt"))){
            String line;
            while((line = br.readLine()) != null){
                // Gennemgå fil linje for linje og gem hver værdi i variable
                String[] parts = line.split(";");
                String title = parts[0].trim();
                String yearString = parts[1].trim();
                int year = Integer.parseInt(yearString);
                String category = parts[2].trim();
                String ratingString = parts[3].trim();
                double rating = Double.parseDouble(ratingString.replaceAll(",","."));

                // Tilføj film til arraylist
                Movie movie = new Movie(title, year, rating, category);
                mediaLibrary.add(movie);
                }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Media> getMediaLibrary() {
        return mediaLibrary;
    }
}
