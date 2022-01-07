package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class WageIncrease extends ChanceCardParent {
    public void wageIncrease(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Grundet dyrtiden har De fået lønforhøjelse. Modtag 1000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
