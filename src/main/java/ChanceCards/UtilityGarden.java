package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class UtilityGarden extends ChanceCardParent {
    public void utilityGarden(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Værdien af egen avl fra nyttehaven udgør 200 DKK som de modtager af banken");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(200);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
