package util;
//--- Hjælpemetoder og filhåndtering ---
import java.util.ArrayList;
import java.util.Scanner;
public class TextUI {

    private static Scanner sc = new Scanner(System.in);

    public ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg){
        displayMsg(msg);
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>(); //til at lave beholder til at gemme brugerens valg
        while(choices.size() < limit){

            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    public void displayList(ArrayList<String>list, String msg){
        for (int i = 0; i <list.size(); i++){
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public void displayMsg(String msg){
        System.out.println(msg);
    }

    public int promptNumeric(String msg){
        displayMsg(msg); //stille bruger spørgsmål
        String input = sc.nextLine(); //Giver bruger et sted at svare og vente på svar
        int numInput = Integer.parseInt(input); //skulle konvertere det indput til et tal

        return numInput;
    }

    public boolean promptBinary(String msg){ //Giver brugeren mulighed for ja / Nej Spørgsmål
        displayMsg(msg);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("Y")){
            return true;
        }
        else if (input.equalsIgnoreCase("N")){
            return false;
        }
        else {
            return promptBinary(msg);
        }
    }
    public String promptText(String msg){
        displayMsg(msg);
        String input = sc.nextLine();
        return input;
    }

}
