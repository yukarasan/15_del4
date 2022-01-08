package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

/*
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by pressing OK, then lastly the money will be set, and
    showed on the gui.
*/

public class Cigarettes extends ChanceCardParent {
    public void cigarettes(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har været udenlands og købt for mange smøger, betal 200 DKK i told.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
