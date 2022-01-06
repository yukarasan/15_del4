package Main;

import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

public class Game {
    private final GUI_Controller gui = new GUI_Controller();

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

        for(int i=0; i<playerNamesYet; i++) {
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

    private void createPlayer() {
        String buttonPressed = gui.getInstance().getUserButtonPressed("Hvor mange spillere ønsker I at være?", "3", "4",
                "5", "6");

        int option = Integer.parseInt(buttonPressed);
        Player[] player = new Player[option];

        GUI_Player[] gui_player = new GUI_Player[option];
        GUI_Car[] car = new GUI_Car[option];

        boolean blueTaken = false, blackTaken = false, whiteTaken = false, redTaken = false, yellowTaken = false, greenTaken = false;
        boolean chooseColorAgain;

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

            // Detecting if the rest of the name contains any upper case letters, and converting them to lowercase
            restOfName = restOfName.toLowerCase();

            name = firstLetter + restOfName;

                while (checkForSameName(name, i, player)) {
                    gui.getInstance().showMessage("Spiller " + (i + 1) + ", navnet er allerede taget, skriv et nyt");
                    name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", Indtast et navn");
                }
                player[i].setName(name);

            while (chooseColorAgain) {

                chooseColorAgain = false;
                String color = gui.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

                switch (color) {

                    case "Blå":
                        if (blueTaken) {
                            gui.getInstance().showMessage("Blå er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i].setPrimaryColor(Color.blue);
                            blueTaken = true;
                        }
                        break;
                    case "Sort":
                        if (blackTaken) {
                            gui.getInstance().showMessage("Sort er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i].setPrimaryColor(Color.black);
                            blackTaken = true;
                        }
                        break;
                    case "Hvid":
                        if (whiteTaken) {
                            gui.getInstance().showMessage("Hvid er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i].setPrimaryColor(Color.white);
                            whiteTaken = true;
                        }
                        break;
                    case "Gul":
                        if (yellowTaken) {
                            gui.getInstance().showMessage("Gul er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i].setPrimaryColor(Color.yellow);
                            yellowTaken = true;
                        }
                        break;
                    case "Rød":
                        if (redTaken) {
                            gui.getInstance().showMessage("Rød er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i].setPrimaryColor(Color.red);
                            redTaken = true;
                        }
                        break;
                    case "Grøn":
                        colorChooser(Color.green,car[i],greenTaken,chooseColorAgain, "Grøn");
                        break;
                }
            }
            gui_player[i] = new GUI_Player(player[i].getName(), player[i].getAccount().getMoney(), car[i]);
            gui.getInstance().addPlayer(gui_player[i]);
            gui.getSpecificField(0).setCar(gui_player[i], true);
        }
    }

    public void colorChooser(Color color, GUI_Car car, boolean colorChosen, boolean chooseColorAgain, String colorString){

        if (colorChosen) {
            gui.getInstance().showMessage(colorString + " er allerede taget");
            chooseColorAgain = true;
        } else {
            car.setPrimaryColor(color);
            colorChosen = true;
        }
    }

    private void round() {
        DiceCup diceCup = new DiceCup();
        gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());
    }
}