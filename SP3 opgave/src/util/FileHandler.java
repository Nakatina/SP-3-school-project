package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandler {
    //TODO: Metode til loadUsers() der læser brugere fra txt-fil LAVET METODE DER LÆSER ALT I EN FIL
    //TODO: Metode til saveUsers() der gemmer brugere i txt-fil TJEK
    //TODO: Overvej fejlhåndtering
    FileWriter writer;
    Path path;
    TextUI ui = new TextUI();
    File file;


    public Path getFile(String... fileName) {
        String baseDirectory = System.getProperty("user.dir");
        return Paths.get(baseDirectory, fileName);
    }

    public boolean stringFileWriter(Path filePath, String... writeInput) {

        try {
            writer = new FileWriter(filePath.toFile(), true);
            for (String s : writeInput) {
                writer.write(s + System.lineSeparator());
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public void createFileAndPath(Path filePath){
        try  {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> returnFile(Path filePath) {
        try {
            if (Files.notExists(filePath)) {
                return new ArrayList<>();
            } else {
                return Files.readAllLines(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkFile(Path filePath, String checkPlace, int lengthOfSplitLine, int checkThisArrayPlace) {
        List<String> listOfFile;
        try {
            //Hvis de ikke eksisterer retunerer den false og skriver fejlmeddelselse
            if (!Files.exists(filePath)) {
                ui.displayMsg(filePath.toString() + " eksisterer ikke");
                ui.displayMsg("Fejl i checkFile(Slet dette)");
                return false;
            }
            listOfFile = Files.readAllLines(Path.of(filePath.toString())); //Læser alle linjer i filen

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

    public boolean checkMatchFile(Path filePath,
                                  int colA, String valA,
                                  int colB, String valB,
                                  int minColumn) {
        List<String> listOfFile;
        try {
            if (Files.notExists(filePath)) {
                ui.displayMsg("Filen findes ikke(Slet dette)");
                return false;
            }
            listOfFile = Files.readAllLines(filePath); //Path.of(filePath.toString())
            for (String s : listOfFile) {
                String[] strings = s.split(";");
                if (strings.length >= minColumn) { // [0] = Mikkel [1] = 123
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

    public static void readFile(Path path) {
        try {
            Scanner input = new Scanner(path); //læser teksten i file (som peger på txt)

            while (input.hasNextLine()) { //så længe der er en ny linje bliver den læst
                String line = input.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findOrCreateAndWriteFile(Path filePath, String inputCheck,
                                         int lengthOfSplitLine, int checkThisArrayPlace,
                                         String... msg) {
        file = new File(String.valueOf(filePath));
        boolean checkedfile =
                checkFile(filePath, inputCheck, lengthOfSplitLine, checkThisArrayPlace);

        try {
            //Hvis den givne mappe ikke findes, bliver den oprettet
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            //Hvis filen ikke findes bliver den lavet, og der bliver skrevet i den
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                stringFileWriter(filePath, inputCheck);
                ui.displayMsg("findOrCreateAndWriteFile created new file (Slet dette)");
                //Hvis filen findes, tjekkes der for det der vil tilføjes, og gives en msg, msg kan være tom, eller flere strings
            } else if (checkedfile) {
                for (String s : msg) {
                    ui.displayMsg(s);
                }
            } else stringFileWriter(filePath, inputCheck);

            {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}




