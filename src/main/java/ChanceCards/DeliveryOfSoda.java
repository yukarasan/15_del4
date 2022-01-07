package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class DeliveryOfSoda extends ChanceCardParent{

    public void deliveryOfSoda(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Betal 200 DKK for levering af 2 kasser sodavand");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
