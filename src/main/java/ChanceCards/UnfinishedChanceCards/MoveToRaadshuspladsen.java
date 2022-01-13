package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveToRaadshuspladsen extends ChanceCardParent {
    public void moveToRaadshuspladsen(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("Tag til Rådhuspladsen");

        player.moveToHere(39);
        GUI_Controller.getSpecificField(39).setCar(gui_player, true);
    }

}

