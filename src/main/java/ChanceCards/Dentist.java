package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Dentist extends ChanceCardParent {
    public void dentist(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Tandlægeregning, betal 2000 DKK.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-2000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
