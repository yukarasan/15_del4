package Fields;

import Main.Player;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

/**
 * This is the ferry field. In this class we've created setters and getters that will keep track of what the
 * rent price is, how many ferries is owned, and who owns them.
 *
 * Furthermore, we've created methods that allow for at player to buy a ferry field and
 */

public class Ferry extends OwnableField {
    private int rentPrice;
    private boolean isOwned;
    private Player owner;
    private GUI_Player guiOwner;
    private final int[] ferryFields = {5, 15, 25, 35};
    private int ferryPrice, intHelper;

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

    public void buyFerry(Player player, GUI_Player gui_player, Ferry[] ferries) {
        if(IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && !isOwned){
            String buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                    gui.getSpecificField(player.getSquare()).getTitle() +
                    ", vil du købe den for 4000 DKK?", "Ja", "Nej");

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
            }
        }
    }

    /**
     * This method is for when a player lands on a ferry field. The method will go through an if statement
     * which will only be true if a player has landed on the field, and the ferry field is already owned,
     * and the player who lands on the field is not an owner as well.
     */

    public void payOwnerOfFerry(Player player, GUI_Player gui_player) {
        if (IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner) {
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
    }

    public void checkForFieldNumber(int ferryNumber){
        switch (ferryNumber) {
            case 0 -> intHelper = 5;
            case 1 -> intHelper = 15;
            case 2 -> intHelper = 25;
            case 3 -> intHelper = 35;
        }
    }

    public void resetFerry(int ferryNumber){

        checkForFieldNumber(ferryNumber);

        int price = Integer.parseInt(gui.getGameBoard().getGuiStreet(intHelper).getRent());
        gui.getSpecificField(intHelper).setSubText("Pris: " + price);

        this.owner = null;
        this.guiOwner = null;
        this.isOwned = false;
        this.ferryPrice = 0;
    }
}