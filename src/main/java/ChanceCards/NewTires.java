package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class NewTires extends ChanceCardParent {
    public void newTires(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har købt 4 nye dæk til Deres vogn, betal kr 1000");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
