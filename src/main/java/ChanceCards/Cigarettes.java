package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Cigarettes extends ChanceCardParent {
    public void cigarettes(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har været udenlands og købt for mange smøger, betal kr 200 i told.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
