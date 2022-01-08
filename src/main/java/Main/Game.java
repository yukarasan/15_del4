package Main;

import Fields.MoveWithADelay;
import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

public class Game {
    private final GUI_Controller gui = new GUI_Controller();
    private static final boolean[] numberOfOption = new boolean[6];
    private static boolean chooseColorAgain;
    private final DiceCup diceCup = new DiceCup();
    private static int numberOfPlayers;
    private Player[] players = new Player[numberOfPlayers];
    private GUI_Player[] gui_players = new GUI_Player[numberOfPlayers];
    private final MoveWithADelay moveWithADelay = new MoveWithADelay();

    public void startGame() {
        gui.getInstance();
        gui.getGameBoard().instantiatingFerries();
        gui.getGameBoard().initializeBrewers();
        welcomingPlayer();
        createPlayer();
        round();
    }

    private void welcomingPlayer() {
        gui.getInstance().showMessage("Velkommen til Matador!");
    }

    private boolean checkForSameName(String name, int playerNamesYet, Player[] players) {
        boolean writeNameAgain = false;

        for (int i = 0; i < playerNamesYet; i++) {
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

    private void colorChooser(boolean[] colorChosen, int numberOfOption, String colorString, int playerNumber, GUI_Car car, Color color) {

        if (colorChosen[numberOfOption]) {
            gui.getInstance().showMessage("Spiller " + (playerNumber + 1) + ", " + colorString + " er allerede taget");
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

        numberOfPlayers = Integer.parseInt(buttonPressed);
        players = new Player[numberOfPlayers];

        gui_players = new GUI_Player[numberOfPlayers];
        GUI_Car[] car = new GUI_Car[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            chooseColorAgain = true;

            players[i] = new Player();
            car[i] = new GUI_Car();

            String name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", indtast dit navn: ");

            players[i].setName(name);

            // If the name contains any numbers between 0 - 9, they will be replaced with an empty string.
            String[] numbersInName = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            name = name.replaceAll(Arrays.toString(numbersInName), "");

            String[] otherCharacters = {"?", "´", "`", "+", "|", "~", "^", "¨", "'", "*", "_", ".", ":", ";", ","
                    + "=", ")", "(", "/", "&", "%", "¤", "#", "\"", "!", "\\", "§", "½", "<", ">"
                    + "@", "£", "$", "€", "{", "}", "[", "]"};
            name = name.replaceAll(Arrays.toString(otherCharacters), "");

            char firstChar = name.charAt(0);  // localizing the first character at index 0
            char lastChar = name.charAt(name.length() - 1);  // localizing the last character at index "name length"
            String firstLetter = (Character.toString(firstChar)).toUpperCase(Locale.ROOT);  // Converting to a string and making it upper case
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

            while (checkForSameName(name, i, players)) {
                gui.getInstance().showMessage("Spiller " + (i + 1) + ", navnet er allerede taget, skriv et nyt");
                name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", Indtast et navn");
            }
            players[i].setName(name);

            chooseColorAgain = true;

            while (chooseColorAgain) {
                chooseColorAgain = false;

                String color = gui.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

                switch (color) {
                    case "Blå" -> colorChooser(numberOfOption, 0, color, i, car[i], Color.blue);
                    case "Sort" -> colorChooser(numberOfOption, 1, color, i, car[i], Color.black);
                    case "Hvid" -> colorChooser(numberOfOption, 2, color, i, car[i], Color.white);
                    case "Gul" -> colorChooser(numberOfOption, 3, color, i, car[i], Color.yellow);
                    case "Rød" -> colorChooser(numberOfOption, 4, color, i, car[i], Color.red);
                    case "Grøn" -> colorChooser(numberOfOption, 5, color, i, car[i], Color.green);
                }
            }
            gui_players[i] = new GUI_Player(players[i].getName(), players[i].getAccount().getMoney(), car[i]);
            gui.getInstance().addPlayer(gui_players[i]);
            gui.getSpecificField(0).setCar(gui_players[i], true);
        }
    }

    private void round() {

        while (true) {
            for (int i = 0; i < numberOfPlayers; i++) {
                playerTurn(players[i], gui_players[i]);

                if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()) {
                    gui.getInstance().showMessage(players[i].getName() + ", du har slået to ens terninger, slå igen");
                    playerTurn(players[i], gui_players[i]);
                }
            }
        }
    }

    private void playerTurn(Player player, GUI_Player gui_player) {
        if (!player.getInJail()) {
            //Player throws dice
            gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

            //This is when the piece moves one square by one square up until thrown value
            moveWithADelay.movePlayerWithADelay(gui_player, player,diceCup,gui);
        }

        passStartField(player, gui_player);

        gui.getGameBoard().getChanceCard().playerLandsOnChanceField(player, gui_player);

        gui.getGameBoard().getFerry(player).buyFerry(player, gui_player, gui.getGameBoard().getFerries());
        gui.getGameBoard().getFerry(player).payOwnerOfFerry(player, gui_player);

        gui.getGameBoard().getBrewer(player).buyBrewerField(player, gui_player);
        gui.getGameBoard().getBrewer(player).payOwnerOfBrewer(player, gui_player, diceCup);

        gui.getGameBoard().getJackpot().payToJackpot(player, gui_player);
        gui.getGameBoard().getJackpot().receiveJackpot(player, gui_player);


        if (player.getSquare() == 30) {
            gui.getGameBoard().getJail().setPlayerInJail(gui_player, player);
        }

        if (player.getInJail() && !player.getWaitATurn()) {
            gui.getGameBoard().getJail().outOfJail(gui_player, player, diceCup);

        } else if (player.getInJail() && player.getWaitATurn()) {
            player.setWaitATurn(false);
        }
    }

    private void passStartField(Player player, GUI_Player gui_players){

        if(player.getPassedStartField()){
            gui.getInstance().showMessage(player.getName() + ", du har passeret start og modtager 4000 DKK");
            player.getAccount().setMoney(4000);
            gui_players.setBalance(player.getAccount().getMoney());
            player.resetPassedStartField();
        }
    }

    /*public void chooseWhichMortgage(Player player, GUI_Player gui_player){
        String chooseMortgage = gui.getInstance().getUserButtonPressed("Vælg hvilken type du vil sælge: " +
                "Brewer", "Ferry");
        switch (chooseMortgage){
            case "Brewer" -> gui.getGameBoard()
        }
    }*/


    public void optionToBuyProperty(Player player, GUI_Player gui_players){

    }

}