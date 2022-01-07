package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveThreeFieldsForward extends ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();

    public void moveThreeFieldsForward(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Ryk tre felter frem");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.moveSquare(3,0);
    }
}





