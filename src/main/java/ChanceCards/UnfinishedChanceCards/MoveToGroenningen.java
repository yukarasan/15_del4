package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveToGroenningen extends ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();
    public void MoveToGrønningen (GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000");
    }

}

