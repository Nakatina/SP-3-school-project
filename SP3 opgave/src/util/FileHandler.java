package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    //TODO: Metode til loadUsers() der læser brugere fra txt-fil LAVET METODE DER LÆSER ALT I EN FIL
    //TODO: Metode til saveUsers() der gemmer brugere i txt-fil TJEK
    //TODO: Overvej fejlhåndtering
    FileWriter writer;
    Path path;
    TextUI ui = new TextUI();
    File file;

    public void stringFileWriter(String filePath, String... writeInput) {

        try {
            writer = new FileWriter(filePath, true);
            for (String s : writeInput) {
                writer.write(s + System.lineSeparator());

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> returnFile(String filePath) {
        try {
            path = Paths.get(filePath);
            if (Files.notExists(path)) {
                return new ArrayList<>();
            } else {
                return Files.readAllLines(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkFile(String checkPlace, String filePath, int lengthOfSplitLine, int checkThisArrayPlace) {
        List<String> listOfFile;
        try {
            path = Paths.get(filePath); //Tager filstien
            //Hvis de ikke eksisterer retunerer den false og skriver fejlmeddelselse
            if (Files.notExists(path)) {
                ui.displayMsg("Fejl i checkFile(Slet dette)");
                return false;
            }
            listOfFile = Files.readAllLines(Path.of(filePath)); //Læser alle linjer i filen

            //Splitter alle linjerne i filen og tjekker om "checkForThis" indgår i filen
            for (String s : listOfFile) {
                String[] split = s.split(";"); //Laver et String array over den splittede linje, med ; som seperator
                if (split.length >= lengthOfSplitLine && split[checkThisArrayPlace].equalsIgnoreCase(checkPlace)) { //Tjek det splittede array for checkPlace på checkThisArrayPlace's plads
                    //lengthOfSplitLine er typisk længden af linjen -1 fx: Brugernavn;Password er 1
                    return true;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    public boolean checkMatchFile(String filePath,
                                  int colA, String valA,
                                  int colB, String valB,
                                  int minColumn) {
        List<String> listOfFile;
        try {
            path = Paths.get(filePath);
            if (Files.notExists(path)) {
                ui.displayMsg("Filen findes ikke(Slet dette)");
                return false;
            }
            listOfFile = Files.readAllLines(Path.of(filePath));
            for (String s : listOfFile) {
                String[] strings = s.split(";");
                if (strings.length >= minColumn) {
                    boolean a = strings[colA].equals(valA); //Tjekker kolonne A for value A
                    boolean b = strings[colB].equals(valB); //Tjekker kolonne B for value B
                    if (a && b) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void findOrCreateAndWriteFile(String inputCheck, String filePath,
                                         int lengthOfSplitLine, int checkThisArrayPlace, String... msg) {
        file = new File(filePath);
        boolean checkedfile =
                checkFile(inputCheck, filePath, lengthOfSplitLine, checkThisArrayPlace);

        try {
            //Laver ny fil hvis der ikke findes en
            path = Paths.get(filePath);
            //Hvis den givne mappe ikke findes, bliver den oprettet
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            //Hvis filen ikke findes bliver den lavet, og der bliver skrevet i den
            if (Files.notExists(path)) {
                Files.createFile(path);
                stringFileWriter(inputCheck, filePath);
                ui.displayMsg("findOrCreateAndWriteFile created new file (Slet dette)");
                //Hvis filen findes, tjekkes der for det der vil tilføjes, og gives en msg, msg kan være tom, eller flere strings
            } else if (checkedfile) {
                for (String s : msg) {
                    ui.displayMsg(s);
                }
            } else stringFileWriter(inputCheck, filePath);

            {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



