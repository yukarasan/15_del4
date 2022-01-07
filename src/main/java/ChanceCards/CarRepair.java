package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class CarRepair extends ChanceCardParent {
    public void carRepair(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Betal 3000 for reparation af deres vogn");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-3000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
