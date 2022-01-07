package Fields;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.stream.IntStream;

import static java.awt.Color.*;

public class Ferry {
    private GUI_Controller gui = new GUI_Controller();
    private int rentPrice, buyPrice;
    private boolean isOwned;
    private Player owner;
    private GUI_Player guiOwner;
    private int[] ferryFields = {5, 15, 25, 35};
    private int ferryPrice;

    public void setOwner(Player player, GUI_Player gui_player) {
        this.owner = player;
        this.guiOwner = gui_player;

        player.getAccount().setMoney(-4000);
        gui_player.setBalance(player.getAccount().getMoney());

        player.setFerriesOwned(1);

        this.isOwned = true;
    }

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

    public void buyFerry(Player player, GUI_Player gui_player, Ferry[] ferries) {

        if(IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && !isOwned){
            String buy = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                    gui.getSpecificField(player.getSquare()).getTitle() +
                    ", vil du købe den for 4000 DKK?", "Ja", "Nej");

            if(buy.equals("Ja")) {
                gui.getSpecificField(player.getSquare()).setSubText(player.getName());
                setOwner(player, gui_player);

                switch(player.getFerriesOwned()) {
                    case 1 -> ferryPrice = 500;
                    case 2 -> ferryPrice = 1000;
                    case 3 -> ferryPrice = 2000;
                    case 4 -> ferryPrice = 4000;
                }

                for (int j = 0; j < ferries.length; j++) {
                    if(player == ferries[j].getOwner()){
                        ferries[j].setRentPrice(ferryPrice);
                    }
                }
            }
        }
    }


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
}