package util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    //TODO: Metode til loadUsers() der læser brugere fra txt-fil
    //TODO: Metode til saveUsers() der gemmer brugere i txt-fil
    //TODO: Overvej fejlhåndtering
    FileWriter writer;
    Path path;
    TextUI ui = new TextUI();

    public void oneStringFileWriter(String writeInput, String filePath) {

        try {
            writer = new FileWriter(filePath, true);
            writer.write(writeInput + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void twoStringFileWriter(String writeInputOne, String writeInputTwo, String filePath) {

        try {
            writer = new FileWriter(filePath, true);
            writer.write(writeInputOne + ";" + writeInputTwo + "\n");
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

    public boolean checkFile(String valueToCheck, String filePath, int lengthOfSplitLine, int checkThisArrayPlace) {
        List<String> listOfFile;
        try {
            path = Paths.get(filePath); //Tager filstien
            //Hvis de ikke eksisterer retunerer den false og skriver fejlmeddelselse
            if (Files.notExists(path)) {
                ui.displayMsg("Fejl i checkFile!");
                return false;
            }
            listOfFile = Files.readAllLines(Path.of(filePath)); //Læser alle linjer i filen
            //Splitter alle linjerne i filen og tjekker om "checkForThis" indgår i filen
            for (String s : listOfFile) {
                String[] split = s.split(";"); //Laver et String array over den splittede linje, med ; som seperator
                if (split.length >= lengthOfSplitLine && split[checkThisArrayPlace].equalsIgnoreCase(valueToCheck)) { //Tjek det splittede array for valueToCheck på checkThisArrayPlace's plads
                    //lengthOfSplitLine er typisk længden af linjen -1 fx: Brugernavn;Password er 1
                    return true;
                }
                }
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
    public boolean checkMatchFile (String filePath,
                                   int colA, String valA,
                                   int colB, String valB,
                                   int minColumn){
        List<String> listOfFile;
        try {
            path = Paths.get(filePath);
            if (Files.notExists(path)) {
                ui.displayMsg("checkMatchFile!");
                return false;
            }
            listOfFile = Files.readAllLines(Path.of(filePath));
            for  (String s : listOfFile) {
                String[] split = s.split(";");
                if (split.length >= minColumn) {
                    boolean a = split[colA].equals(valA); //Tjekker kolonne A for value A
                    boolean b = split[colB].equals(valB); //Tjekker kolonne B for value B
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

    }



