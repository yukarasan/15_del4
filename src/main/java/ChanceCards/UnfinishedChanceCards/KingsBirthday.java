package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class KingsBirthday extends ChanceCardParent {
    public void kingsBirthday(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
    }
}
