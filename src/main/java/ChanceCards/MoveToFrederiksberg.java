package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class MoveToFrederiksberg extends ChanceCardParent {
    public void moveToFrederiksberg(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Ryk frem til Frederiksberg Allé. Hvis du passerer start så modtag 4000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        int beforeMoving = player.getAccount().getMoney();

        moveWithADelay.movePlayerWithDelayToSpecificField(player, gui_player, gui, 11);

        if(beforeMoving>11) {
            gui.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
            player.setMoney(4000);
            gui_player.setBalance(player.getAccount().getMoney());
        }



    }
}
