package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

public class MoveThreeFieldsBack extends ChanceCardParent {
    public void moveThreeFieldsBack(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Ryk tre felter tilbage");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        moveWithADelay.movePlayerWithDelayInChanceCard(player, -3, gui_player, gui);
    }
}
