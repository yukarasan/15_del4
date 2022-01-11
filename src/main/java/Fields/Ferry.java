package Fields;

import Fields.Properties.Property;
import GUI_Controllor.GUI_Controller;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

import static java.awt.Color.blue;
import static java.awt.Color.red;

/**
 * This is the ferry field. In this class we've created setters and getters that will keep track of what the
 * rent price is, how many ferries is owned, and who owns them.
 *
 * Furthermore, we've created methods that allow for at player to buy a ferry field and
 */

public class Ferry extends OwnableField {
    private int rentPrice;
    private boolean isOwned, bidAgain;
    private Player owner, theOneWhoAuctioned;
    private GUI_Player guiOwner;
    private final int[] ferryFields = {5, 15, 25, 35};
    private static int ferryPrice, intHelper, ferryNumber, guiField, buyPrice = 4000, currentBid;
    boolean justBought;

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public boolean getIsFerryOwned() {
        return isOwned;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public GUI_Player getGuiOwner() {
        return guiOwner;
    }

    /**
     * This method sets an owner to a ferry if a player decides to buy one. The if-statement can be seen in the
     * method buyFerry.
     */

    public void setOwner(Player player, GUI_Player gui_player) {
        this.owner = player;
        this.guiOwner = gui_player;

        player.getAccount().setMoney(-4000);
        gui_player.setBalance(player.getAccount().getMoney());

        player.setFerriesOwned();

        this.isOwned = true;
    }

    /**
     * The method buyFerry is a method that checks if a player has landed on any of the 4 ferry fields.
     * If they have, and it is not owned, then the player will have the option to buy a field or pass.
     *
     * If they buy the field, an owner will be set. And depending on how many fields they already own,
     * a specific rent price will be chosen in the switch-statement.
     */

    public void buyFerry(Player player, GUI_Player gui_player, Ferry[] ferries, Player[] players, GUI_Player[] gui_players) {

        guiField = player.getSquare();

        if(IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && !isOwned){

            String buy = null;
            if(player.getAccount().getMoney() < 4000){
                buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                        gui.getSpecificField(player.getSquare()).getTitle() +
                        ", vil du købe den for 4000 DKK?", "Ja", "Sæt færge på auktion");
            }

            if(player.getAccount().getMoney() > 4000) {
                buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                        gui.getSpecificField(player.getSquare()).getTitle() +
                        ", vil du købe den for 4000 DKK?", "Ja", "Nej, sæt færge på auktion");
            }

            if(buy.equals("Ja")) {
                gui.getSpecificField(player.getSquare()).setSubText(player.getName());
                setOwner(player, gui_player);

                switch(player.getFerriesOwned()) {
                    case 1 -> ferryPrice = 500;  // Rent price
                    case 2 -> ferryPrice = 1000;
                    case 3 -> ferryPrice = 2000;
                    case 4 -> ferryPrice = 4000;
                }

                /**
                 * In the for-each loop below, we've taken a collection of the Ferry array and iterate through
                 * the amount of ferries.
                 *
                 * If a player equals an owner (that is if the if-statement is true), then a rent price will be
                 * set by passing a ferry price as an argument.
                 */
                for (Ferry ferry : ferries) {
                    if (player == ferry.getOwner()) {
                        ferry.setRentPrice(ferryPrice);
                    }
                }
            }if(buy.equals("Nej, sæt færge på auktion") || buy.equals("Sæt færge på auktion")){
                setFerryOnAuction(player, players, ferries, gui_players);
            }
        }
    }

    public void checkIfLandedFerryField(int guiField){

        switch (guiField) {
            case 5 -> ferryNumber = 0;
            case 15 -> ferryNumber = 1;
            case 25 -> ferryNumber = 2;
            case 35 -> ferryNumber = 3;
        }
    }

    public int getFerryPrice() {
        return ferryPrice;
    }

    public void setFerryOnAuction(Player player, Player[] players, Ferry[] ferries, GUI_Player[] gui_players){

        theOneWhoAuctioned = player;
        checkIfLandedFerryField(player.getSquare());

        gui.getInstance().showMessage("Færgen " + gui.getSpecificField(player.getSquare()).getTitle() + " er sat på auktion" +
                ", andre spillere kan nu byde på denne");

        Player isOutOfAuction[] = new Player[players.length];
        Player highestBidder = null;

        boolean bought = false;
        int isOut = 0;

        int richestAmount = 0;
        for (int i = 0; i < players.length; i++) {

            if(players[i].getAccount().getMoney() > richestAmount){
                richestAmount = players[i].getAccount().getMoney();
            }
        }

        while(!bought) {

            for (int i = 0; i < players.length; i++) {

                bidAgain = true;
                if (players[i] != isOutOfAuction[i] && !bought) {
                    while (bidAgain && players[i].getAccount().getMoney() > (currentBid + 50) && players[i] != isOutOfAuction[i]
                            && currentBid < richestAmount && !players[i].getPlayerOutOfGame()) {
                        bidAgain = false;
                        String bid = gui.getInstance().getUserButtonPressed(players[i].getName() + ", du kan byde på feltet "
                                        + gui.getSpecificField(player.getSquare()).getTitle() + ". (Oprindelig pris: " + 4000
                                        + "). Det nuværende bud er " + currentBid + ". Hvad vil du byde med mere",
                                "50", "100", "500", "1000", "2000", "5000", "Ønsker ikke at byde");

                        switch (bid) {
                            case "50", "100", "500", "1000", "2000", "5000" -> placeBit(player, Integer.parseInt(bid) + currentBid, highestBidder);
                            case "Ønsker ikke at byde" -> {
                                isOutOfAuction[i] = players[i];
                                isOut++;
                            }
                        }
                    }
                }
                if (isOut == (isOutOfAuction.length - 1) || currentBid >= richestAmount - 50) {
                    bought = true;
                }
            }
        }

        if(bought){

            for (int i = 0; i < players.length; i++) {

                if(players[i] != isOutOfAuction[i]) {
                    gui.getInstance().showMessage("Tillykke " + players[i].getName() + ", du har købt feltet " +
                            gui.getSpecificField(player.getSquare()).getTitle() + " for " + currentBid);
                    ferries[intHelper].boughtFerryFromAuction(gui_players[i], players[i], ferries);


                    switch(players[i].getFerriesOwned()) {
                        case 1 -> ferryPrice = 500;  // Rent price
                        case 2 -> ferryPrice = 1000;
                        case 3 -> ferryPrice = 2000;
                        case 4 -> ferryPrice = 4000;
                    }
                }
            }
            ferries[intHelper].setRentPrice(ferryPrice);
        }
    }

    public void setGuiOwner(GUI_Player guiOwner) {
        this.guiOwner = guiOwner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void trueIsOwned() {
        isOwned = true;
    }

    public void setJustBought(boolean trueOrNot){
        justBought = trueOrNot;
    }

    public void boughtFerryFromAuction(GUI_Player gui_player, Player player, Ferry[] ferries){
        checkForFieldNumber(ferryNumber);
        checkIfLandedFerryField(guiField);

        player.setFerriesOwned();

        this.owner = player;
        ferries[intHelper].setGuiOwner(gui_player);
        this.guiOwner = gui_player;
        ferries[intHelper].setOwner(player);

        player.getAccount().setMoney(-currentBid);
        gui_player.setBalance(player.getAccount().getMoney());


        gui.getSpecificField(guiField).setSubText(player.getName());

        ferries[intHelper].setJustBought(true);

        ferries[intHelper].trueIsOwned();
        this.isOwned = true;

    }

    private void placeBit(Player player, int bid, Player highestBidder){

        if(bid >= player.getAccount().getMoney()){
            gui.getInstance().showMessage(player.getName() + ", du kan ikke byde højere end det du har, byd en anden værdi");
        }else{
            currentBid = bid;
            highestBidder = player;
        }
    }

    public boolean isJustBought() {
        return justBought;
    }

    /**
     * This method is for when a player lands on a ferry field. The method will go through an if statement
     * which will only be true if a player has landed on the field, and the ferry field is already owned,
     * and the player who lands on the field is not an owner as well.
     */



    public void payOwnerOfFerry(Player player, GUI_Player gui_player, Ferry[] ferries) {

        checkIfLandedFerryField(player.getSquare());

        if (IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && ferries[ferryNumber].getIsFerryOwned()
                && player != ferries[ferryNumber].getOwner() && player != theOneWhoAuctioned) {
            gui.getInstance().getUserButtonPressed("Oh oh.. " + player.getName() + ", du har landet på " +
                    getOwner().getName() + "'s færge: " + gui.getSpecificField(player.getSquare()).getTitle() +
                    ". Du skal betale " + getRentPrice() + " DKK", "Betal");

            //Setting the payer's money
            player.getAccount().setMoney(-getRentPrice());
            gui_player.setBalance(player.getAccount().getMoney());

            //Setting the owner's money
            getOwner().getAccount().setMoney(getRentPrice());
            getGuiOwner().setBalance(getOwner().getAccount().getMoney());
        }
        if(player == theOneWhoAuctioned){
            theOneWhoAuctioned = null;
        }
    }

    public void checkForFieldNumber(int ferryNumber){
        switch (ferryNumber) {
            case 0 -> guiField = 5;
            case 1 -> guiField = 15;
            case 2 -> guiField = 25;
            case 3 -> guiField = 35;
        }
    }

    public void resetFerry(int ferryNumber){

        checkForFieldNumber(ferryNumber);

        int price = Integer.parseInt(gui.getGameBoard().getGuiStreet(intHelper).getRent());
        gui.getSpecificField(intHelper).setSubText("Pris: " + price);

        this.owner = null;
        this.guiOwner = null;
        this.isOwned = false;
    }
}