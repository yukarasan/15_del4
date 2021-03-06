package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

/**
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by pressing OK, then lastly the player will be moved three
    fields backwards and showed on the gui. When the player moves, they will not jump to field like what happened
    in other projects. Insted they'll move with a delay, so that it'll look like a person moves the player itself.
*/

public class MoveThreeFieldsBack extends ChanceCardParent {
    public void moveThreeFieldsBack(GUI_Player gui_player, Player player) {
        GUI_Controller.getInstance().displayChanceCard("Ryk tre felter tilbage.");
        GUI_Controller.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        /*
        Here we are using the moveWithADelay object and its method movePlayerWithDelayInChanceCard which have been
        instantiated in the ChanceCardParent class. This method makes the player move to their respective field,
        with a delay, so that they move 1 field backwards, until they reach their destination. This is done with
        a for-loop.
         */
        moveWithADelay.movePlayerWithDelayInChanceCard(player, -3, gui_player);
    }
}
