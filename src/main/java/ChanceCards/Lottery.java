package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Lottery extends ChanceCardParent {
    public void lottery(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har vundet i klasselotteriet. Modtag 500 DKK.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(500);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
