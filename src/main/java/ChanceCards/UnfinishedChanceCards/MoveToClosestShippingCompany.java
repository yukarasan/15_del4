package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveToClosestShippingCompany extends ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();
    public void rådhuspladsen(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken");
    }

}


