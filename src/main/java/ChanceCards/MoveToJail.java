package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class MoveToJail extends ChanceCardParent {
    public void moveToJail(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Gå i fængsel. Du modtager ikke 4000 for at passere start");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.moveToHere(10);

        gui.getGameBoard().getJail().setPlayerInJail(gui_player, player);
        gui.getSpecificField(10).setCar(gui_player, true);
    }
}
