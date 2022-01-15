package Main;

import Fields.*;
import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;

/**
 * This class is where we have implemented most of the game rules and methods.
 */

public class Game {
    private static final boolean[] numberOfOptions = new boolean[6];
    private static boolean chooseColorAgain;
    private final DiceCup diceCup = new DiceCup();
    private static int numberOfPlayers;
    private Player[] players = new Player[numberOfPlayers];
    private GUI_Player[] gui_players = new GUI_Player[numberOfPlayers];
    private final MoveWithADelay moveWithADelay = new MoveWithADelay();
    private int intHelper;
    boolean wait, notEnoughMoney, gameOver;

    /**
     * The method startGame, is simply a method that runs other methods.
     */

    public void startGame() {
        GUI_Controller.getInstance();
        GUI_Controller.getGameBoard().instantiatingFerries();
        GUI_Controller.getGameBoard().initializeBrewers();
        GUI_Controller.getGameBoard().createPropertiesPrices();
        welcomingPlayer();
        createPlayer();
        round();
    }

    /**
     * The method welcomingPlayer is a simple method that welcomes the player by showing the message;
     * "Welcome to Matador!"
     */

    private void welcomingPlayer() {
        GUI_Controller.getInstance().showMessage("Velkommen til Matador!");
    }

    /**
     * The method checkForSameName is a method that makes sure that players can't choose the same name.
     * We know that in real life, different people can have the same name, and therefore it might seem weird that
     * we've gone with this implementation. But in games, there have been a tradition of not being able to have the
     * same name as other players. This is something that we want for our game as well.
     *
     * The method sets a boolean value to false by default. In its parameters, it takes in a name, an int value
     * and an array of players.
     *
     * We use this method in the createPlayer method, where the name is just a string variable called name, and
     * the int value is i from the for-loop in the createPlayer method. The value i can have different values depending
     * on how many players are in the game. The last parameter players, as an array that gets created after the user
     * enters how many players are in the game.
     */

    private boolean checkForSameName(String name, int playerNamesYet, Player[] players) {
        boolean writeNameAgain = false;

        for (int i = 0; i < playerNamesYet; i++) {
            if (name.equals(players[i].getName())) {
                writeNameAgain = true;
                break;
            }
        }
        return writeNameAgain;
    }

    /**
     * The method colorChooser is a method that we'll use when creating a player in the createPlayer() method.
     * In the parameter a boolean array[6] called colorChosen we will choose different colors depending on which
     * button the user presses. The array only contains false booleans.
     *
     * If a player chooses an option in the createPlayer method, a number for numberOfOption will be chosen.
     * For that specific number, we will go through the first if statement, which in that case will be true,
     * because our colorChosen[numberOfOption] still hasn't been assigned as being true yet.
     * If the colorChosen[numberOfOption] is already defined true, it won't go through the first if-statement.
     * Insted it will just tell the user that the color has already been taken.
     */

    private void colorChooser(boolean[] colorChosen, int numberOfOption, String colorString, int playerNumber, GUI_Car car, Color color) {
        if (!colorChosen[numberOfOption]) {
            colorChosen[numberOfOption] = true;
            chooseColorAgain = false;
            car.setPrimaryColor(color);
        } else if (colorChosen[numberOfOption]) {
            GUI_Controller.getInstance().showMessage("Spiller " + (playerNumber + 1) + ", " + colorString + " er allerede taget");
            chooseColorAgain = true;
        }
    }

    /**
     * This is the method that creates each player. Depending on how what button gets pressed, an integer of that
     * number will be made, called numberOfPlayers. And with that number of player, we will create an array of
     * players[numberOfPlayers]. We do the same for GUI_player and GUI_Car
     * We don't allow the players name to contain any numbers, nor any characters besides the alphabet and '-'.
     * And if a player chooses the same name, they will be asked to write another instead.
     * It's also in this method that the player gets to choose their car.
     */

    private void createPlayer() {
        String buttonPressed = GUI_Controller.getInstance().getUserButtonPressed("Hvor mange spillere ønsker I at være?", "3", "4",
                "5", "6");

        numberOfPlayers = Integer.parseInt(buttonPressed);
        players = new Player[numberOfPlayers];

        gui_players = new GUI_Player[numberOfPlayers];
        GUI_Car[] car = new GUI_Car[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            chooseColorAgain = true;

            players[i] = new Player();
            car[i] = new GUI_Car();

            String name = GUI_Controller.getInstance().getUserString("Spiller " + (i + 1) + ", indtast dit navn: ");

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

            // Checking if the player enters the same name:
            while (checkForSameName(name, i, players)) {
                GUI_Controller.getInstance().showMessage("Spiller " + (i + 1) + ", navnet er allerede taget, skriv et nyt");
                name = GUI_Controller.getInstance().getUserString("Spiller " + (i + 1) + ", Indtast et navn");
            }

            players[i].setName(name);

            chooseColorAgain = true;

            while (chooseColorAgain) {
                chooseColorAgain = false;

                String color = GUI_Controller.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

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
            GUI_Controller.getInstance().addPlayer(gui_players[i]);
            GUI_Controller.getSpecificField(0).setCar(gui_players[i], true);
        }
    }

    /**
     * This method takes care of the rounds in the game, and contunies to play, while the gameOver is not true.
     */

    private void round() {

        while (!gameOver) {

            for (int i = 0; i < numberOfPlayers; i++) {

                if (!players[i].getPlayerOutOfGame() && !gameOver) {

                    playerTurn(players[i], gui_players[i]);

                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()
                    && !players[i].getPlayerOutOfGame()) {
                        GUI_Controller.getInstance().showMessage(players[i].getName() + ", du har slået to ens terninger, slå igen");
                        playerTurn(players[i], gui_players[i]);
                    }
                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()
                    && !players[i].getPlayerOutOfGame()) {
                        GUI_Controller.getInstance().showMessage(players[i].getName() + ", du har slået to ens terninger igen " +
                                "og kan slå en sidste gang");
                        playerLastTurn(players[i], gui_players[i], diceCup);
                    }
                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue() && !players[i].getInJail()
                    && !players[i].getPlayerOutOfGame()) {
                        GUI_Controller.getInstance().showMessage(players[i].getName() + ", du har slået to ens terninger for " +
                                "tredje gang, og derfor skal du i fængsel");
                        setPlayerInJail(gui_players[i], players[i]);
                    }
                }
            }
        }
    }

    /**
     * This method is where we've put all the methods that can happen for a players turn.
     */

    private void playerTurn(Player player, GUI_Player gui_player) {
        // If the player is not in jail, and the player is still in the game:
        if (!player.getInJail() && !player.getPlayerOutOfGame()) {

            //If player owns a set of colors, player gets asked what to do with their turn
            optionsIfOwningSetOfColors(player);

            //Player throws dice
            GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

            // The player moves to the square one by one with the moveWithDelay method:
            moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

            // This method checks what can happen to the player once they land on a square
            playerLandsAnywhere(player, gui_player);
        }

        /*
        If the player is in jail, we don't want to use the outOfJail method yet, so in the else if - statement
        we make setWaitAturn equal to false, so that in the next round, when they are still in jail, the
        if - statement below will be run, and the player can either pay or throw two dice to get out.
         */
        if (player.getInJail() && !player.getWaitATurn()) {
            outOfJail(player, gui_player, diceCup);
            notEnoughMoney = false;

        } else if (player.getInJail() && player.getWaitATurn()) {
            player.setWaitATurn(false);
        }
    }

    /**
     * This method is where we've put all the methods that can happen for a players turn, when they have only one
     * throw left. If they throw two identical die, then they'll be placed in prison.
     */

    public void playerLastTurn(Player player, GUI_Player gui_player, DiceCup diceCup) {

        if (!player.getInJail() && !player.getPlayerOutOfGame()) {

            //If player owns a set of colors, player gets asked what to do with their turn
            optionsIfOwningSetOfColors(player);

            //Player throws dice
            GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

            // If the player does not throw two identical dice, then the player will simply move as usual:
            if (diceCup.getDie1().getFaceValue() != diceCup.getDie2().getFaceValue()) {

                //This is when the piece moves one square by one square up until thrown value
                moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                playerLandsAnywhere(player, gui_player);

                if (player.getInJail() && !player.getWaitATurn()) {
                    outOfJail(player, gui_player, diceCup);
                    notEnoughMoney = false;

                } else if (player.getInJail() && player.getWaitATurn()) {
                    player.setWaitATurn(false);
                }
            }
        }
    }

    /**
     * This method checks for where the player lands. This includes chance card fields, ferry fields, brewer fields,
     * jackpot fields and the jail field at number 31 (board number).
     */

    public void playerLandsAnywhere(Player player, GUI_Player gui_player) {
        passStartField(player, gui_player);

        //If player lands on chance card
        GUI_Controller.getGameBoard().getChanceCard().playerLandsOnChanceField(player, gui_player);

        //If player lands on ferries
        GUI_Controller.getGameBoard().getFerry(player).buyFerry(player, gui_player, GUI_Controller.getGameBoard().getFerries(), players, gui_players);

        if (!GUI_Controller.getGameBoard().getFerry(player).isJustBought()) {

            GUI_Controller.getGameBoard().getFerry(player).payOwnerOfFerry(player, gui_player, GUI_Controller.getGameBoard().getFerries());

        } else {
            GUI_Controller.getGameBoard().getFerry(player).setJustBought(false);
        }

        //If player lands on brewers
        GUI_Controller.getGameBoard().getBrewer(player).buyBrewer(player, gui_player, GUI_Controller.getGameBoard().getBrewers(), players, gui_players);

        if (!GUI_Controller.getGameBoard().getBrewer(player).getIsJustBought()) {
            GUI_Controller.getGameBoard().getBrewer(player).payOwnerOfBrewer(player, gui_player, diceCup);
        } else {
            GUI_Controller.getGameBoard().getBrewer(player).setJustBought(false);
        }

        //If player lands on jackpot
        GUI_Controller.getGameBoard().getJackpot().payToJackpot(player, gui_player);
        GUI_Controller.getGameBoard().getJackpot().receiveJackpot(player, gui_player);

        //If player lands on properties
        GUI_Controller.getGameBoard().getProperty(player).payOwner(player, gui_player);

        GUI_Controller.getGameBoard().getProperty(player).landOnProperty(player, gui_player, GUI_Controller.getGameBoard().getProperties(), players, gui_players);


        //if player lands on
        if (player.getSquare() == 30) {
            setPlayerInJail(gui_player, player);
        }
        //Checking if player has any money left
        checkIfPlayerLooses(player, gui_player);
    }

    /**
     * This methods checks for whether a player owns every property of the same color. If they do, then they have
     * the option to either throw their dice, or choose to buy houses or hotels (buildings).
     */

    public void optionsIfOwningSetOfColors(Player player) {

        if (player.getOwnsAPropertySet()) {

            String option = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", vælg hvad du ønsker med din tur",
                    "kast", "Køb bygninger");

            if (option.equals("Køb bygninger")) {
                GUI_Controller.getGameBoard().getProperty(player).optionsWhenOwningAllFields(GUI_Controller.getGameBoard().getProperties(), player);
            }
        }
    }

    /**
     * This method is used for checking whether a player has passed the start field. If they have, then they'll
     * receive 4000 DKK.
     */

    private void passStartField(Player player, GUI_Player gui_players) {

        if (player.getPassedStartField()) {
            GUI_Controller.getInstance().showMessage(player.getName() + ", du har passeret start og modtager 4000 DKK");
            player.getAccount().setMoney(4000);
            gui_players.setBalance(player.getAccount().getMoney());
            player.resetPassedStartField();
        }
    }

    /**
     * This is a method that takes care of moving a player to jail, if they've landed on the other jail field.
     */

    public void setPlayerInJail(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
        GUI_Controller.getSpecificField(10).setCar(gui_player, true);
        player.moveToHere(10);
        player.setInJail(true);
        player.setWaitATurn(true);
    }

    /**
     * The method outOfJail is a method that deals with all the things related to when a player interacts with
     * the jail.
     *
     * When a player is in prison, they have some options.
     *
     * Let's say that the player has an account balance of 2000, and they have not been in prison for more than 2
     * rounds, then they have the option (first option) to attempt to throw two of the same die or pay 1000 DKK.
     * Depending on which button the player chooses, different scenarios will occur.
     * If they had an account of less than or equal to 1000 DKK, then they'll have the option to throw their dice.
     * If the user goes through the first option, then we make a boolean which we set to true, so that in the next
     * if statement won't go through.
     *
     * (Second option): When the player has been in jail for more than one round, then we'll give them the message
     * saying that they have one last try, to attempt at throwing to equal dice.
     * This if statement will only be true, if !wait = true. So wait has to be false. And the number of times
     * that the player has been in jail has to be greater than 1.
     * And depending on how much money they have, different options for pressing on the button will occur.
     */

    public void outOfJail(Player player, GUI_Player gui_player, DiceCup diceCup) {
        String chosenElement = null;

        if (player.getAccount().getMoney() < 1000) {
            notEnoughMoney = true;
        }

        // first option: the player has not been in jail for more than two rounds, or they don't have enough money:
        if (player.getTurnNumberInJail() < 2 || notEnoughMoney) { // getTurnNumberInJail how many rounds they have waited.

            // if they have the sufficient funds, they have two options.
            if (player.getAccount().getMoney() > 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du har to valgmuligheder for at komme ud af fængslet",
                        "Slå to ens terninger", "Betal 1000 DKK");
            }

            // If their balance is less than or equal to 1000 DKK, then they have only one option, which is to throw the dice.
            if (player.getAccount().getMoney() <= 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du har en valgmulighed for at komme ud af fængslet",
                        "Slå to ens terninger");
                notEnoughMoney = true;
            }

            // Depending on which button the player chooses, different scenarios will happen.
            switch (chosenElement) {
                case "Slå to ens terninger" -> {
                    // Each die will be thrown:
                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue()) {

                        // The player will no longer be in jail, and they will no longer have to wait a turn:
                        player.setInJail(false);
                        player.setWaitATurn(false);

                        // Congratulating the player:
                        GUI_Controller.getInstance().showMessage("Tillykke! Du slog to ens. Du må nu rykke ud af fængslet og slå igen.");

                        // Making sure that once the player moves out of jail, they'll move with a delay:
                        moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                        GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);

                        // Now once the player has moved out of the jail we check for were they have landed.
                        playerLandsAnywhere(player, gui_player);

                        // The player now gets the option to throw again:
                        GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                        GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                        moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                        // We check where they have landed, and we reset the number of turns that the player has been in prison
                        playerLandsAnywhere(player, gui_player);
                        player.resetNumberInJail();

                        wait = true; // For later usage.

                    } else {
                        // if they don't throw two identical dice, a message will be showed saying that they have to wait another round:
                        GUI_Controller.getInstance().showMessage("Desværre, bedre held næste gang. Du må vente en runde.");
                        // We then increment the number of turns that the player has been in prison by 1:
                        player.setTurnNumberInJail();
                        wait = true;  // For later usage.
                    }
                }

                case "Betal 1000 DKK" -> {
                    // 1000 DKK will be paid and shown on the GUI:
                    player.getAccount().setMoney(-1000);
                    gui_player.setBalance(player.getAccount().getMoney());
                    GUI_Controller.getInstance().showMessage("Tak for pengene. Du må nu slå igen for at komme ud.");
                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                    // The player will no longer be in jail, and they will no longer have to wait a turn:
                    player.setInJail(false);
                    player.setWaitATurn(false);

                    // Checking for where the player lands, and setting wait false.
                    playerLandsAnywhere(player, gui_player);
                    wait = false;

                    // We reset the number of turns that the player has been in prison:
                    player.resetNumberInJail();
                }
            }
        }

        // Second option:
        // This if statement will only be true, if !wait = true. So wait has to be false. And the number of times
        // that the player has been in jail has to be greater than 1.
        if (!wait && player.getTurnNumberInJail() > 1 && player.getInJail()) {

            if (player.getAccount().getMoney() > 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", sidste chance, " +
                        "du har to valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");
            }

            if (player.getAccount().getMoney() < 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", sidste chance, " +
                        "du har to valgmuligheder", "Slå to ens terninger");
            }

            if (chosenElement.equals("Slå to ens terninger")) {

                GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue()) {
                    GUI_Controller.getInstance().showMessage("Tillykke! Du slog to ens. Du må nu rykke ud af fængslet og slå igen.");

                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                    GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);

                    playerLandsAnywhere(player, gui_player);

                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                    player.setInJail(false);
                    player.setWaitATurn(false);
                    player.resetNumberInJail();

                    playerLandsAnywhere(player, gui_player);
                }

                if (chosenElement.equals("Betal 1000 DKK")) {
                    player.getAccount().setMoney(-1000);
                    gui_player.setBalance(player.getAccount().getMoney());

                    GUI_Controller.getInstance().showMessage("Tak for pengene. Du må nu slå igen for at komme ud.");
                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

                    // Rolling the dice:
                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                    // Moving one field at a time:
                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                    player.setInJail(false);
                    player.setWaitATurn(false);
                    player.resetNumberInJail();
                    playerLandsAnywhere(player, gui_player);
                }

                if (diceCup.getDie1().getFaceValue() != diceCup.getDie2().getFaceValue() && player.getAccount().getMoney() > 1000) {

                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", nu skal du betale 1000 DKK og kaste terningerne",
                            "Betal");
                    player.getAccount().setMoney(-1000);
                    gui_player.setBalance(player.getAccount().getMoney());
                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);
                    player.setInJail(false);
                    player.setWaitATurn(false);
                    playerLandsAnywhere(player, gui_player);
                    player.resetNumberInJail();
                }
            }
        }
        wait = false;
    }

    /**
     * The method checkIfPlayerLooses checks whether a player wins or loses. This is done with an if-statement.
     * If a player has less than 0 or equal to 0, then a message gets printed out saying that they have gone
     * bankrupt. Then the player will be set out of the game, using the setter in the player class.
     *
     * We also make sure that for each property the player has, we check whether they are the owner for these
     * properties. If they are, then we will reset the ownership of the property, and thereby making it
     * available for other players to buy. We do this with ferries and brewers as well.
     *
     * We want to reset the ownership of properties only if the game hasn't ended. If the game is not over,
     * then a message will pop up saying that the players properties are available for purchase.
     */

    public void checkIfPlayerLooses(Player player, GUI_Player gui_player) {
        if (player.getAccount().getMoney() <= 0) {
            GUI_Controller.getInstance().showMessage(player.getName() + " er gået bankerot");
            player.setPlayerOutOfGame(true);
            GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, false);

            for (int i = 0; i < GUI_Controller.getGameBoard().getProperties().length; i++) {
                if (player == GUI_Controller.getGameBoard().getProperty(i).getOwner()) {
                    GUI_Controller.getGameBoard().getProperties()[i].resetProperty(i);
                }
            }

            for (int i = 0; i < GUI_Controller.getGameBoard().getFerries().length; i++) {
                if (player == GUI_Controller.getGameBoard().getFerries()[i].getOwner()) {
                    GUI_Controller.getGameBoard().getFerries()[i].resetFerry(i);
                }
            }

            for (int i = 0; i < GUI_Controller.getGameBoard().getBrewers().length; i++) {
                if (player == GUI_Controller.getGameBoard().getBrewers()[i].getOwner()) {
                    GUI_Controller.getGameBoard().getBrewers()[i].resetBrewer(i);
                }
            }

            gameOver();

            if(!gameOver) {
                GUI_Controller.getInstance().showMessage("Alle " + player.getName() + "'s felter er nu tilgængelige for køb.");
            }
        }
    }

    /**
     * In the method gameOver we keep track of every player that have gotten out of the game.
     * This way we can determine ourselves when the game should end.
     *
     * Since the rules of the game declare that the game should end once a single player is standing, we've
     * created a for-loop that checks for whether they have gotten out of the game. If they have, then isOut
     * will be incremented by one.
     *
     * Then if isOut equals the number of players in our game minus 1, we make gameOver equal true.
     * And for the player that has not gotten out of the game, we'll show a message displaying their name.
     */

    public void gameOver() {
        int isOut = 0;

        for (int i = 0; i < numberOfPlayers; i++) {

            if (players[i].getPlayerOutOfGame()) {
                isOut++;
            }
        }

        if (isOut == numberOfPlayers - 1) {
            gameOver = true;

            for (int i = 0; i < numberOfPlayers; i++) {
                if (!players[i].getPlayerOutOfGame()) {
                    GUI_Controller.getInstance().showMessage(players[i].getName() + " har vundet spillet!");
                }
            }
        }
    }
}