import java.nio.file.Path;

import util.*;

/**
 * Repræsenterer en film i streamingtjenesten.
 * Klassen håndterer afspilning af film, brugerinteraktioner
 * (som "se nu" eller "se senere"), og gemmer disse valg
 * i brugerens personlige datafiler.
 *
 * <p>Fildata indlæses fra {@code film.txt}, og brugerens valgte film
 * gemmes i individuelle filer under:
 * <code>SP3 opgave/data/userdata/&lt;brugernavn&gt;/</code>.
 *
 * @see Media
 * @see FileHandler
 * @see User
 */
public class Movie extends Media {
    /** Brugergrænseflade til input og output i konsollen. */
    private final TextUI textUI = new TextUI();

    /** Filhåndtering til læsning og skrivning af data. */
    private final FileHandler fh;

    /** Den aktuelle bruger, som er logget ind. */
    private final User user;

    /** Filmens varighed i minutter. */
    int duration;

    /** Brugernavn associeret med denne film. */
    String username;

    /**
     * Opretter en ny film med alle dens metadata.
     * @deprecated Denne konstruktør er ikke brugt endnu
     * @param fh          den {@link FileHandler} der bruges til filoperationer
     * @param user        den {@link User} som har valgt filmen
     * @param title       filmens titel
     * @param releaseYear udgivelsesåret for filmen
     * @param rating      filmens bedømmelse (0.0–10.0)
     * @param category    filmens kategori (f.eks. "Drama" eller "Action")
     */
    @Deprecated
    public Movie(FileHandler fh, User user, String title, int releaseYear, double rating, String category) {
        super(title, releaseYear, rating, category);
        this.fh = fh;
        this.user = user;
    }

    /**
     * Opretter en film knyttet til en {@link User} og {@link FileHandler},
     * uden at specificere metadata. Bruges typisk, når der arbejdes
     * med brugerens fil-lister.
     *
     * @param fh   den {@link FileHandler} der bruges til filoperationer
     * @param user den {@link User} som filmen er tilknyttet
     */
    public Movie(FileHandler fh, User user) {
        this.fh = fh;
        this.user = user;

    }

    /**
     * Lader brugeren vælge en film at afspille eller tilføje til "se senere"-listen.
     * <p>
     * Metoden tjekker om filmen eksisterer i {@code film.txt} via
     * {@link FileHandler#checkFile(Path, String, int, int)} og beder derefter brugeren
     * vælge en handling.
     * <p>
     * Resultatet gemmes i brugerens personlige mappe under
     * <code>watched movies.txt</code> eller <code>watch later movies.txt</code>.
     *
     * @see #addMovieToFiles(Path, String, String)
     */
    public void doMovie() {
        String chosenMovie = textUI.promptText("Hvilken film vil du se?");
        Path filePathFilm = fh.getFile("SP3 opgave/" + "data/" + "film.txt");
        if (fh.checkFile(filePathFilm, chosenMovie, 4, 0)) {
            var choice = chooseWatchOrAdd();
            if (choice == 1) {
                play(chosenMovie);
                addMovieToFiles(fh.getFile(), chosenMovie, "watched movies.txt");
            } else if (choice == 2) {
                textUI.displayMsg("Film tilføjet til se senere!");
                addMovieToFiles(fh.getFile(), chosenMovie, "watch later movies.txt");
                //Tilføj film til Watch Later fil
            }
        } else {
            textUI.displayMsg("Filmen findes ikke");
        }

    }

    /**
     * Tilføjer en film til brugerens filhistorik, enten som "set"
     * eller "se senere".
     * <p>
     * Denne metode skriver filmens titel til en specifik tekstfil i
     * brugerens mappe, oprettet via {@link FileHandler#getFile(String...)}.
     *
     * @param filePathFilm stien til filmdatafilen
     * @param title        filmens titel
     * @param file         navnet på den fil der skal skrives til (fx {@code watched movies.txt})
     * @see FileHandler#stringFileWriter(Path, String...)
     */
    private void addMovieToFiles(Path filePathFilm, String title, String file) {
        Path path = fh.getFile("SP3 opgave", "data", "userdata", user.getUsername(), file);
        fileHandler.stringFileWriter(path, title);
    }

    /**
     * Afspiller film
     * @param movieName Navnet på filmen
     */
    @Override
    public void play(String movieName) {
        textUI.displayMsg(movieName + " afspilles nu...");
    }
}
