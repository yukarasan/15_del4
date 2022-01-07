package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveThreeFieldsBack extends ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();
    public void MoveThreeFieldsBack(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Ryk tre felter tilbage");
    }

}

