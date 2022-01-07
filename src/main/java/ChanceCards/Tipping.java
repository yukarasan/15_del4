package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Tipping extends ChanceCardParent {
    public void tipping(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De have en række med elleve rigtige i tipning. Modtag 1000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
