package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveToGroenningen extends ChanceCardParent {
    public void moveToGroenningen(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000");

        if (player.getSquare() > 24) {
                GUI_Controller.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
                player.setMoney(4000);
                gui_player.setBalance(player.getAccount().getMoney());
        }
        player.moveToHere(24);
        GUI_Controller.getSpecificField(24).setCar(gui_player, true);
    }
}


