package Main;

import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

public class Game {
    private final GUI_Controller gui = new GUI_Controller();
    private static boolean[] numberOfOption = new boolean[6];
    private static boolean chooseColorAgain;

    public void startGame() {
        gui.getInstance();
        welcomingPlayer();
        createPlayer();
        round();
    }

    private void welcomingPlayer() {
        gui.getInstance().showMessage("Velkommen til Matador!");
    }

    public boolean checkForSameName(String name, int playerNamesYet, Player[] players){

        boolean writeNameAgain = false;

        for(int i=0; i < playerNamesYet; i++) {
            if (!name.equals(players[i].getName())) {
                writeNameAgain = false;
            }
        }
        for (int i = 0; i < playerNamesYet; i++) {
            if (name.equals(players[i].getName())) {
                writeNameAgain = true;
            }
        }
        return writeNameAgain;
    }

    public void colorChooser(boolean[] colorChosen, int numberOfOption, String colorString, int playerNumber, GUI_Car car, Color color) {

        if (colorChosen[numberOfOption]) {
            gui.getInstance().showMessage("Spiller " + (playerNumber+1) + ", " + colorString + " er allerede taget");
            chooseColorAgain = true;
        } else {
            colorChosen[numberOfOption] = true;
            chooseColorAgain = false;
            car.setPrimaryColor(color);
        }
    }

    private void createPlayer() {
        String buttonPressed = gui.getInstance().getUserButtonPressed("Hvor mange spillere ønsker I at være?", "3", "4",
                "5", "6");

        int option = Integer.parseInt(buttonPressed);
        Player[] player = new Player[option];

        GUI_Player[] gui_player = new GUI_Player[option];
        GUI_Car[] car = new GUI_Car[option];

        for (int i = 0; i < option; i++) {
            chooseColorAgain = true;

            player[i] = new Player();
            car[i] = new GUI_Car();

            String name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", indtast dit navn: ");

            player[i].setName(name);

            // If the name contains any numbers between 0 - 9, they will be replaced with an empty string.
            String[] numbersInName = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            name = name.replaceAll(Arrays.toString(numbersInName),"");

            String[] otherCharacters = {"?", "´", "`", "+", "|", "~", "^", "¨", "'", "*", "_", ".", ":", ";", ","
                    + "=", ")", "(", "/", "&", "%", "¤", "#", "\"", "!", "\\", "§", "½", "<", ">"
                    + "@", "£", "$", "€", "{", "}", "[", "]"};
            name = name.replaceAll(Arrays.toString(otherCharacters),"");

            char firstChar = name.charAt(0);  // localizing the first character at index 0
            char lastChar = name.charAt(name.length() - 1);  // localizing the last character at index "name length"
            String firstLetter = (Character.toString(firstChar)).toUpperCase(Locale.ROOT);  // Converting to a string and making
            String restOfName = name.substring(1);  // Making a variable that separates the name at index 1

            // Determining if input contains " " and then trimming the string before computing the rest of the statements
            if (Character.toString(firstChar).matches(" ") || Character.toString(lastChar).matches(" ")) {
                name = name.trim();
                firstChar = name.charAt(0);
                firstLetter = (Character.toString(firstChar)).toUpperCase(Locale.ROOT);
                restOfName = name.substring(1);
            }

            // Detecting if the rest of the name contains any upper case letters, and converting them to lowercase.
            restOfName = restOfName.toLowerCase();

            name = firstLetter + restOfName;

                while (checkForSameName(name, i, player)) {
                    gui.getInstance().showMessage("Spiller " + (i + 1) + ", navnet er allerede taget, skriv et nyt");
                    name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", Indtast et navn");
                }
                player[i].setName(name);

                chooseColorAgain = true;

            while (chooseColorAgain) {
                chooseColorAgain = false;

                String color = gui.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

                switch (color) {
                    case "Blå":
                     colorChooser(numberOfOption,0,color,i,car[i], Color.blue);
                        break;
                    case "Sort":
                        colorChooser(numberOfOption,1,color,i,car[i], Color.black);
                        break;
                    case "Hvid":
                        colorChooser(numberOfOption,2,color,i,car[i], Color.white);
                        break;
                    case "Gul":
                        colorChooser(numberOfOption,3,color,i,car[i], Color.yellow);
                        break;
                    case "Rød":
                        colorChooser(numberOfOption,4,color,i,car[i], Color.red);
                        break;
                    case "Grøn":
                        colorChooser(numberOfOption,5,color,i,car[i], Color.green);
                        break;
                }
            }
            gui_player[i] = new GUI_Player(player[i].getName(), player[i].getAccount().getMoney(), car[i]);
            gui.getInstance().addPlayer(gui_player[i]);
            gui.getSpecificField(0).setCar(gui_player[i], true);
        }
    }

    private void round() {
        DiceCup diceCup = new DiceCup();
        gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());
    }
}