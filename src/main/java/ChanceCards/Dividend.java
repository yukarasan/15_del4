package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class Dividend extends ChanceCardParent {
    public void dividend(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Du modtager dit aktieudbytte. Modtag 1000 DKK af banken");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
