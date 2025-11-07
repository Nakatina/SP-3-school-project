import java.io.File;
import java.util.Scanner;

public class Movie extends Media {
    int duration; //filmens længde
    //TODO indlæser fra txt/csv Filer.
    //Konstructor!
    public Movie(String title, int releaseYear, double rating, String category, int duration){
        super(title, releaseYear, rating, category);
        this.duration = duration;
    }

    @Override
    public void play(){
        System.out.println(title + " afspilles nu...");
    }

    //eksemple
    //Movie titanic = new Movie("Titanic", 1997, 7.8, "Romance", 195);

    //Læs film fra .txt
    public static void readMoviesFromTxt(){
        try {
            File file = new File("data/film.txt"); //peger på txt filen

            Scanner input = new Scanner(file); //læser teksten i file (som peger på txt)

            while (input.hasNextLine()){ //så længe der er en ny linje bliver den læst og vist
                String line = input.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Kunne ikke læse filen");
        }
    }

    //Midlertidig test main
    public static void main(String[] args){
        readMoviesFromTxt();
    }

}
