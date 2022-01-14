package ChanceCards.UnfinishedChanceCards;

import ChanceCards.ChanceCardParent;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

public class MoveToVimmelskaftet extends ChanceCardParent {
    public void moveToVimmelskaftet(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000");
        if(player.getSquare()>32){
            GUI_Controller.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
            player.setMoney(4000);
            gui_player.setBalance(player.getAccount().getMoney());
        }

        player.moveToHere(32);
        GUI_Controller.getSpecificField(32).setCar(gui_player, true);
    }

}



