package Fields;

import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

/**
 *
 */

public class Brewer extends OwnableField {
    private Player owner;
    private GUI_Player guiOwner;
    private int fieldPrice, intHelper;
    private final int[] brewerFields = {12, 28};
    private boolean isOwned;
    private boolean justBought, bidAgain;
    private int brewerPrice, currentBid;
    private static int brewerNumber, guiFieldNumber;


    public void setIsOwned(boolean trueOrFalse){
        isOwned = trueOrFalse;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setGuiOwner(GUI_Player guiOwner) {
        this.guiOwner = guiOwner;
    }


    public void buyBrewerField(Player player, GUI_Player gui_player) {

        String chosenOption = null;
        if (IntStream.of(brewerFields).anyMatch(x -> x == player.getSquare()) && !isOwned) {
            chosenOption = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                    gui.getSpecificField(player.getSquare()).getTitle() + ", vil du købe feltet for 3000 DKK?", "Ja", "Nej");

            if (chosenOption.equals("Ja")) {
                player.getAccount().setMoney(-3000);
                gui_player.setBalance(player.getAccount().getMoney());
                gui.getSpecificField(player.getSquare()).setSubText(player.getName());
                player.setBrewersOwned();
                this.owner = player;
                this.guiOwner = gui_player;
                isOwned = true;
            }
        }
    }


    private void boughtBrewerFromAuction(Player player, GUI_Player gui_player, Brewer[] brewers){
        player.getAccount().setMoney(-currentBid);
        gui_player.setBalance(player.getAccount().getMoney());
        gui.getSpecificField(guiFieldNumber).setSubText(player.getName());
        player.setBrewersOwned();

        brewers[brewerNumber].setOwner(player);
        brewers[brewerNumber].setGuiOwner(gui_player);
        brewers[brewerNumber].setIsOwned(true);
    }

    public void buyBrewer(Player player, Brewer[] brewers, Player[] players, GUI_Player[] gui_players) {

        checkIfLandedBrewerField(player);
        guiFieldNumber = player.getSquare();

        if (IntStream.of(brewerFields).anyMatch(x -> x == player.getSquare()) && !isOwned) {

            String buy = null;
            if (player.getAccount().getMoney() < 3000) {
                buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                        gui.getSpecificField(player.getSquare()).getTitle() +
                        ", vil du købe den for 3000 DKK?", "Ja", "Sæt brygger på auktion");
            }

            if (player.getAccount().getMoney() > 3000) {
                buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                        gui.getSpecificField(player.getSquare()).getTitle() +
                        ", vil du købe den for 3000 DKK?", "Ja", "Nej, sæt brygger på auktion");
            }

            if (buy.equals("Ja")) {
                gui.getSpecificField(player.getSquare()).setSubText(player.getName());
            }

            if(buy.equals("Sæt brygger på auktion") || buy.equals("Nej, sæt brygger på auktion")){
                setBrewerOnAuction(player, players, brewers, gui_players);
            }

        }
    }

    public void checkIfLandedBrewerField(Player player){

        switch (player.getSquare()) {
            case 12 -> brewerNumber = 0;
            case 28 -> brewerNumber = 1;
        }
    }

    public void setBrewerOnAuction(Player player, Player[] players, Brewer[] brewers, GUI_Player[] gui_players) {

        checkIfLandedBrewerField(player);

        gui.getInstance().showMessage("bryggeren " + gui.getSpecificField(player.getSquare()).getTitle() + " er sat på auktion" +
                ", andre spillere kan nu byde på denne");

        Player isOutOfAuction[] = new Player[players.length];
        Player highestBidder = null;

        boolean bought = false;
        int isOut = 0;

        int richestAmount = 0;
        for (int i = 0; i < players.length; i++) {

            if (players[i].getAccount().getMoney() > richestAmount) {
                richestAmount = players[i].getAccount().getMoney();
            }
        }

        while (!bought) {

            for (int i = 0; i < players.length; i++) {

                bidAgain = true;
                if (players[i] != isOutOfAuction[i] && !bought) {
                    while (bidAgain && players[i].getAccount().getMoney() > (currentBid + 50) && players[i] != isOutOfAuction[i]
                            && currentBid < richestAmount) {
                        bidAgain = false;
                        String bid = gui.getInstance().getUserButtonPressed(players[i].getName() + ", du kan byde på feltet "
                                        + gui.getSpecificField(player.getSquare()).getTitle() + ". (Oprindelig pris: " + 4000
                                        + "). Det nuværende bud er " + currentBid + ". Hvad vil du byde med mere",
                                "50", "100", "500", "1000", "2000", "5000", "Ønsker ikke at byde");

                        switch (bid) {
                            case "50", "100", "500", "1000", "2000", "5000" -> placeBid(player, Integer.parseInt(bid) + currentBid, highestBidder);
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

        if (bought) {

            for (int i = 0; i < players.length; i++) {

                if (players[i] != isOutOfAuction[i]) {
                    gui.getInstance().showMessage("Tillykke " + players[i].getName() + ", du har købt feltet " +
                            gui.getSpecificField(player.getSquare()).getTitle() + " for " + currentBid);
                    brewers[brewerNumber].boughtBrewerFromAuction(players[i], gui_players[i], brewers);
                }
            }
        }
    }

    private void placeBid(Player player, int bid, Player highestBidder){

        if(bid >= player.getAccount().getMoney()){
            gui.getInstance().showMessage(player.getName() + ", du kan ikke byde højere end det du har, byd en anden værdi");
        }else{
            currentBid = bid;
            highestBidder = player;
        }
    }

    public void payOwnerOfBrewer(Player player, GUI_Player gui_player, DiceCup diceCup, Brewer[] brewers) {
        if (IntStream.of(brewerFields).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner
                && !justBought) {
            switch (owner.getBrewersOwned()) {
                case 1 -> fieldPrice = (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()) * 100;
                case 2 -> fieldPrice = (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()) * 200;
            }

            gui.getInstance().getUserButtonPressed("Oh oh.. " + player.getName() + ", du er landet på " +
            owner.getName() + "'s sodavandsfabrik: " + gui.getSpecificField(player.getSquare()).getTitle() +
            ". Du skal betale " + fieldPrice + " DKK", "Betal");

            player.getAccount().setMoney(-fieldPrice);
            gui_player.setBalance(player.getAccount().getMoney());

            owner.getAccount().setMoney(fieldPrice);
            guiOwner.setBalance(owner.getAccount().getMoney());
        }

        if(brewers[brewerNumber].getUsJustBought()){
            brewers[brewerNumber].setJustBought(false);
        }
    }

    public void setJustBought(boolean trueOrFalse){
        justBought = trueOrFalse;
    }

    public boolean getUsJustBought(){
        return justBought;
    }

    public void checkForFieldNumber(int brewerNumber){
        switch (brewerNumber) {
            case 0 -> intHelper = 12;
            case 1 -> intHelper = 28;
        }
    }

    public void resetBrewer(int brewerNumber){

        checkForFieldNumber(brewerNumber);

        int price = Integer.parseInt(gui.getGameBoard().getGuiStreet(intHelper).getRent());
        gui.getSpecificField(intHelper).setSubText("Pris: " + price);

        this.owner = null;
        this.guiOwner = null;
        this.isOwned = false;
        this.fieldPrice = 0;
    }

    public Player getOwner() {
        return owner;
    }
}
