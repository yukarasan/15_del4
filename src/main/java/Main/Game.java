package Main;

import Fields.Jail;
import Fields.MoveWithADelay;
import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

public class Game {
    private final GUI_Controller gui = new GUI_Controller();
    private static final boolean[] numberOfOptions = new boolean[6];
    private static boolean chooseColorAgain;
    private final DiceCup diceCup = new DiceCup();
    private static int numberOfPlayers;
    private Player[] players = new Player[numberOfPlayers];
    private GUI_Player[] gui_players = new GUI_Player[numberOfPlayers];
    private final MoveWithADelay moveWithADelay = new MoveWithADelay();
    private Jail jail = new Jail();
    private int intHelper;

    public void startGame() {
        gui.getInstance();
        gui.getGameBoard().instantiatingFerries();
        gui.getGameBoard().initializeBrewers();
        gui.getGameBoard().createPropertiesPrices();
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
                    case "Blå" -> colorChooser(numberOfOptions, 0, color, i, car[i], Color.blue);
                    case "Sort" -> colorChooser(numberOfOptions, 1, color, i, car[i], Color.black);
                    case "Hvid" -> colorChooser(numberOfOptions, 2, color, i, car[i], Color.white);
                    case "Gul" -> colorChooser(numberOfOptions, 3, color, i, car[i], Color.yellow);
                    case "Rød" -> colorChooser(numberOfOptions, 4, color, i, car[i], Color.red);
                    case "Grøn" -> colorChooser(numberOfOptions, 5, color, i, car[i], Color.green);
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
                if(diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()){
                   gui.getInstance().showMessage(players[i].getName() + ", du har slået to terninger igen " +
                           "og skal slå en sidste gang");
                }
                if(diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()){
                    gui.getInstance().showMessage(players[i].getName() + ", du har slået to ens terninger for " +
                            "tredje gang, og derfor skal du i fængsel");
                    setPlayerInJail(gui_players[i],players[i]);
                }
            }
        }
   }

    private void playerTurn(Player player, GUI_Player gui_player) {
        if (!player.getInJail()) {

            //If player owns a set of colors, player gets asked what to do with their turn
            optionsIfOwningSetOfColors(player);

            //Player throws dice
            gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

            //This is when the piece moves one square by one square up until thrown value
            moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup, gui);
        }

        playerLandsAnywhere(player, gui_player);
    }

    public void playerLandsAnywhere(Player player, GUI_Player gui_player){
        passStartField(player, gui_player);

        gui.getGameBoard().getChanceCard().playerLandsOnChanceField(player, gui_player);

        gui.getGameBoard().getFerry(player).buyFerry(player, gui_player, gui.getGameBoard().getFerries());
        gui.getGameBoard().getFerry(player).payOwnerOfFerry(player, gui_player);

        gui.getGameBoard().getBrewer(player).buyBrewerField(player, gui_player);
        gui.getGameBoard().getBrewer(player).payOwnerOfBrewer(player, gui_player, diceCup);

        gui.getGameBoard().getJackpot().payToJackpot(player, gui_player);
        gui.getGameBoard().getJackpot().receiveJackpot(player, gui_player);

        gui.getGameBoard().getProperty(player).landOnProperty(player, gui_player, gui.getGameBoard().getProperties());

        if (player.getSquare() == 30) {
            setPlayerInJail(gui_player, player);
        }

        if (player.getInJail() && !player.getWaitATurn()) {
            outOfJail(gui_player, player, diceCup);

        } else if (player.getInJail() && player.getWaitATurn()) {
            player.setWaitATurn(false);
        }
    }


    public void optionsIfOwningSetOfColors(Player player){

        if(player.getOwnsAPropertySet()){

            String option = gui.getInstance().getUserButtonPressed(player.getName() + ", vælg hvad du ønsker med din tur",
                    "kast", "Køb bygninger");

            if(option.equals("Køb bygninger")){
                gui.getGameBoard().getProperty(player).optionsWhenOwningAllFields(gui.getGameBoard().getProperties(), player);
            }
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

    public void setPlayerInJail(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
        gui.getSpecificField(10).setCar(gui_player, true);
        player.moveToHere(10);
        player.setInJail(true);
        player.setWaitATurn(true);
    }

    public void outOfJail (GUI_Player gui_player, Player player, DiceCup diceCup) {

        String chosenElement = gui.getInstance().getUserButtonPressed(player.getName() + ", du har to valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");

        switch (chosenElement) {
            case "Slå to ens terninger" -> {
                gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue()) {
                    gui.getInstance().showMessage("Tillykke! Du slog to ens. Du må nu rykke ud af fængslet og slå igen.");
                    player.moveToSquare(diceCup.getDie1().getFaceValue(), diceCup.getDie2().getFaceValue());
                    gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

                    playerLandsAnywhere(player, gui_player);

                    gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                    gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup, gui);

                    player.setInJail(false);
                    player.setWaitATurn(false);

                    playerLandsAnywhere(player, gui_player);

                } else {
                    gui.getInstance().showMessage("Desværre, bedre held næste gang. Du må vente en runde.");
                }
            }
            case "Betal 1000 DKK" -> {
                player.getAccount().setMoney(-1000);
                gui_player.setBalance(player.getAccount().getMoney());
                gui.getInstance().showMessage("Tak for pengene. Du må nu slå igen for at komme ud.");
                gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

                gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup, gui);

                player.setInJail(false);
                player.setWaitATurn(false);

                playerLandsAnywhere(player, gui_player);
            }
        }
    }



    public void optionToBuyProperty(Player player, GUI_Player gui_players){

    }

    /**
     * Checks whether a player wins or loses. This is done with an if-statement. If a player has less than 0 or
     * equal to 0, then a message gets printed out saying that they have gone bankrupt.
     * Then the player will be set out of the game, using the setter in the player class.
     *
     */

    public void makeIntHelper(Player player) {

        switch (player.getSquare()) {
            case 1 -> intHelper = 0;
            case 3 -> intHelper = 1;
            case 6 -> intHelper = 2;
            case 8 -> intHelper = 3;
            case 9 -> intHelper = 4;
            case 11 -> intHelper = 5;
            case 13 -> intHelper = 6;
            case 14 -> intHelper = 7;
            case 16 -> intHelper = 8;
            case 18 -> intHelper = 9;
            case 19 -> intHelper = 10;
            case 21 -> intHelper = 11;
            case 23 -> intHelper = 12;
            case 24 -> intHelper = 13;
            case 26 -> intHelper = 14;
            case 27 -> intHelper = 15;
            case 29 -> intHelper = 16;
            case 31 -> intHelper = 17;
            case 32 -> intHelper = 18;
            case 34 -> intHelper = 19;
            case 37 -> intHelper = 20;
            case 39 -> intHelper = 21;
        }
    }

    public void checkIfPlayerLooses(Player player) {
        if (player.getAccount().getMoney() <= 0) {
            gui.getInstance().showMessage(player.getName() + " er gået bankerot");
            player.setPlayerOutOfGame(true);

            for (int i = 0; i < gui.getGameBoard().getProperties().length; i++) {
                if(player == gui.getGameBoard().getProperty(i).getOwner()){
                    gui.getGameBoard().getProperties()[i].resetProperty(i);
                }
            }

            for (int i = 0; i < gui.getGameBoard().getFerries().length; i++) {
                if(player == gui.getGameBoard().getFerries()[i].getOwner()){
                    gui.getGameBoard().getFerries()[i].resetFerry(i);
                }
            }

            gui.getInstance().showMessage("Alle " + player.getName() + "'s felter er nu tilgængelig for køb.");
        }
    }

    /*public void gameOver(Player player) {
        if (endGameForPlayer && endGameForPlayer) {
            gui.getInstance().showMessage(player.getName() + " har vundet spillet!");
        }
    }
     */
}