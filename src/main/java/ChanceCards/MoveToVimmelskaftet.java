package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class MoveToVimmelskaftet extends ChanceCardParent  {

    public void Rådhuspladsen(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000");
        if(player.getSquare()>32){
            gui.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
            player.setMoney(4000);
            gui_player.setBalance(player.getAccount().getMoney());
        }

        player.moveToHere(32);
        gui.getSpecificField(32).setCar(gui_player, true);
    }

}



