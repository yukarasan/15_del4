package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class CarInsurance extends ChanceCard {

    public void carInsurance(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Betal din bilforsikring for 1000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }
}
