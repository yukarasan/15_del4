package ChanceCards.UnfinishedChanceCards;

import ChanceCards.*;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class TravelWithMolsLinien extends ChanceCardParent {
    
    GUI_Controller gui = new GUI_Controller();

    public void travelWithMolsLinien (GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.");

        player.moveToHere(15);
        gui.getSpecificField(15).setCar(gui_player, true);
    }
    }




