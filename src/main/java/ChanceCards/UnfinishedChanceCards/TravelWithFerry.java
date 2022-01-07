package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class TravelWithFerry extends ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();
    public void TravelWithFerry(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000");
    }

}

