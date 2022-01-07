package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Municipality extends ChanceCardParent {
    public void municipality(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 DKK.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(3000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
