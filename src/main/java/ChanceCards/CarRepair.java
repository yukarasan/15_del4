package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

/**
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by pressing OK, then lastly the money will be set, and
    showed on the gui.
*/

public class CarRepair extends ChanceCardParent {
    @Override
    public void makePayment(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Betal 3000 DKK for reparation af deres vogn");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-3000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    @Override
    public void acceptPayment(GUI_Player gui_player, Player player) {

    }
}
