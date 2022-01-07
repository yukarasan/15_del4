package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class RunRedLight extends ChanceCardParent {

    public void runRedLight(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

}
