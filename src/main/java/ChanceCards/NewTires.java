package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

/*
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by clicking OK, then lastly the money will be set, and
    showed on the gui.
*/

public class NewTires extends ChanceCardParent {
    public void newTires(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har købt 4 nye dæk til Deres vogn, betal kr 1000");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
