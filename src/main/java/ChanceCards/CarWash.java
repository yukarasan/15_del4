package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class CarWash extends ChanceCardParent {
    public void carWash(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Betal for vognvask og smøring 300 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-300);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
