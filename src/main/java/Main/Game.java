package Main;

import Fields.*;
import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.awt.*;
import java.util.Arrays;
import java.util.Locale;


public class Game {
    private static final boolean[] numberOfOptions = new boolean[6];
    private static boolean chooseColorAgain;
    private final DiceCup diceCup = new DiceCup();
    private static int numberOfPlayers;
    private Player[] players = new Player[numberOfPlayers];
    private GUI_Player[] gui_players = new GUI_Player[numberOfPlayers];
    private final MoveWithADelay moveWithADelay = new MoveWithADelay();
    private Jail jail = new Jail();
    private int intHelper;
    boolean wait, notEnoughMoney, gameOver;

    public void startGame() {
        GUI_Controller.getInstance();
        GUI_Controller.getGameBoard().instantiatingFerries();
        GUI_Controller.getGameBoard().initializeBrewers();
        GUI_Controller.getGameBoard().createPropertiesPrices();
        welcomingPlayer();
        createPlayer();
        round();
    }

    private void welcomingPlayer() {
        GUI_Controller.getInstance().showMessage("Velkommen til Matador!");
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
            GUI_Controller.getInstance().showMessage("Spiller " + (playerNumber + 1) + ", " + colorString + " er allerede taget");
            chooseColorAgain = true;
        } else {
            colorChosen[numberOfOption] = true;
            chooseColorAgain = false;
            car.setPrimaryColor(color);
        }
    }

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

    public void playerLastTurn(Player player, GUI_Player gui_player, DiceCup diceCup) {

        if (!player.getInJail() && !player.getPlayerOutOfGame()) {

            //If player owns a set of colors, player gets asked what to do with their turn
            optionsIfOwningSetOfColors(player);

            //Player throws dice
            GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

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

    private void playerTurn(Player player, GUI_Player gui_player) {

        if (!player.getInJail() && !player.getPlayerOutOfGame()) {

            //If player owns a set of colors, player gets asked what to do with their turn
            optionsIfOwningSetOfColors(player);

            //Player throws dice
            GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

            //Dice get shown on board
            GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

            //This is when the piece moves one square by one square up until thrown value
            moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

            playerLandsAnywhere(player, gui_player);
        }

        if (player.getInJail() && !player.getWaitATurn()) {
            outOfJail(player, gui_player, diceCup);
            notEnoughMoney = false;

        } else if (player.getInJail() && player.getWaitATurn()) {
            player.setWaitATurn(false);
        }
    }

    public void playerLandsAnywhere(Player player, GUI_Player gui_player) {
        passStartField(player, gui_player);

        //If player lands on chancecard
        GUI_Controller.getGameBoard().getChanceCard().playerLandsOnChanceField(player, gui_player);

        //If player lands on ferries
        GUI_Controller.getGameBoard().getFerry(player).buyFerry(player, GUI_Controller.getGameBoard().getFerries(), players, gui_players);

        if (!GUI_Controller.getGameBoard().getFerry(player).isJustBought()) {

            GUI_Controller.getGameBoard().getFerry(player).payOwnerOfFerry(player, gui_player, GUI_Controller.getGameBoard().getFerries());

        } else {
            GUI_Controller.getGameBoard().getFerry(player).setJustBought(false);
        }

        //If player lands on brewers
        GUI_Controller.getGameBoard().getBrewer(player).buyBrewer(player, gui_player, GUI_Controller.getGameBoard().getBrewers(), players, gui_players);

        if (!GUI_Controller.getGameBoard().getBrewer(player).getIsJustBought()) {
            GUI_Controller.getGameBoard().getBrewer(player).payOwnerOfBrewer(player, gui_player, diceCup, GUI_Controller.getGameBoard().getBrewers());
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


    public void optionsIfOwningSetOfColors(Player player) {

        if (player.getOwnsAPropertySet()) {

            String option = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", vælg hvad du ønsker med din tur",
                    "kast", "Køb bygninger");

            if (option.equals("Køb bygninger")) {
                GUI_Controller.getGameBoard().getProperty(player).optionsWhenOwningAllFields(GUI_Controller.getGameBoard().getProperties(), player);
            }
        }
    }

    private void passStartField(Player player, GUI_Player gui_players) {

        if (player.getPassedStartField()) {
            GUI_Controller.getInstance().showMessage(player.getName() + ", du har passeret start og modtager 4000 DKK");
            player.getAccount().setMoney(4000);
            gui_players.setBalance(player.getAccount().getMoney());
            player.resetPassedStartField();
        }
    }

    public void setPlayerInJail(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
        GUI_Controller.getSpecificField(10).setCar(gui_player, true);
        player.moveToHere(10);
        player.setInJail(true);
        player.setWaitATurn(true);
    }


    public void outOfJail(Player player, GUI_Player gui_player, DiceCup diceCup) {

        String chosenElement = null;

        if (player.getAccount().getMoney() < 1000) {
            notEnoughMoney = true;
        }

        if (player.getTurnNumberInJail() < 2 || notEnoughMoney) {

            if (player.getAccount().getMoney() > 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du har to valgmuligheder for at komme ud af fængslet",
                        "Slå to ens terninger", "Betal 1000 DKK");
            }

            if (player.getAccount().getMoney() <= 1000) {
                chosenElement = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du har en valgmulighed for at komme ud af fængslet",
                        "Slå to ens terninger");
                notEnoughMoney = true;
            }

            switch (chosenElement) {
                case "Slå to ens terninger" -> {
                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue()) {

                        player.setInJail(false);
                        player.setWaitATurn(false);

                        GUI_Controller.getInstance().showMessage("Tillykke! Du slog to ens. Du må nu rykke ud af fængslet og slå igen.");

                        moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                        GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);

                        playerLandsAnywhere(player, gui_player);

                        GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                        GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                        moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                        playerLandsAnywhere(player, gui_player);
                        player.resetNumberInJail();

                    } else {
                        GUI_Controller.getInstance().showMessage("Desværre, bedre held næste gang. Du må vente en runde.");
                        player.setTurnNumberInJail();
                        wait = true;
                    }
                }
                case "Betal 1000 DKK" -> {
                    player.getAccount().setMoney(-1000);
                    gui_player.setBalance(player.getAccount().getMoney());
                    GUI_Controller.getInstance().showMessage("Tak for pengene. Du må nu slå igen for at komme ud.");
                    GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");

                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup);

                    player.setInJail(false);
                    player.setWaitATurn(false);

                    playerLandsAnywhere(player, gui_player);
                    wait = false;
                    player.resetNumberInJail();
                }
            }
        }

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

                    GUI_Controller.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
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
     * Checks whether a player wins or loses. This is done with an if-statement. If a player has less than 0 or
     * equal to 0, then a message gets printed out saying that they have gone bankrupt.
     * Then the player will be set out of the game, using the setter in the player class.
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

    public static void main(String[] args) {

        GUI_Controller.getGameBoard().createPropertiesPrices();


        Player[] players = new Player[2];
        players[0] = new Player();
        players[0].setName("hej111");
        players[1] = new Player();
        players[1].setName("hej222");
        GUI_Car car = new GUI_Car();
        GUI_Car car1 = new GUI_Car();

        GUI_Player[] gui_players = new GUI_Player[2];

        gui_players[0] = new GUI_Player("hej111", players[0].getAccount().getMoney(), car);
        gui_players[1] = new GUI_Player("hej222", players[1].getAccount().getMoney(), car1);

        GUI_Controller.getInstance().addPlayer(gui_players[0]);
        GUI_Controller.getInstance().addPlayer(gui_players[1]);

        players[0].moveToHere(1);
        GUI_Controller.getSpecificField(1).setCar(gui_players[0], true);


        GUI_Controller.getGameBoard().getProperty(players[0]).landOnProperty(players[0], gui_players[0], GUI_Controller.getGameBoard().getProperties(), players, gui_players);

        players[1].moveToHere(1);
        GUI_Controller.getSpecificField(1).setCar(gui_players[1], true);
        //gui.getGameBoard().getProperty(players[1]).landOnProperty(players[1], gui_players[1], gui.getGameBoard().getProperties(), players, gui_players);
        GUI_Controller.getGameBoard().getProperty(players[1]).payOwner(players[1], gui_players[1]);


    }


}