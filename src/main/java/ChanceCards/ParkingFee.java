package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class ParkingFee extends ChanceCardParent {
    public void parkingFee(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("De har fået en parkeringsbøde, betal kr 200 i bøde.");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
