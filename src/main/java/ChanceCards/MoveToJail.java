package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

/**
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by pressing OK, then lastly the player will be moved to
    the jail - which has the field number 10 and the player and their respective car will be shown on the gui.
*/

public class MoveToJail extends ChanceCardParent {
    public void moveToJail(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("Gå i fængsel. Du modtager ikke 4000 DKK for at passere start");
        GUI_Controller.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.moveToHere(10);

        GUI_Controller.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
        GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);
        player.moveToHere(10);
        player.setInJail(true);
        player.setWaitATurn(true);

        GUI_Controller.getSpecificField(10).setCar(gui_player, true);
    }
}
