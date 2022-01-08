package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

/*
    In this class we've created a method that takes in a gui_player and player as arguments. When the arguments are
    passed they'll get used in the body of the method, which will first display what will happen to the player
    who pulls the card, and make them pull the card by pressing OK, then lastly the player will be moved to
    Frederiksberg Allé - which has the field number 11.
*/

public class MoveToFrederiksberg extends ChanceCardParent {
    public void moveToFrederiksberg(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Ryk frem til Frederiksberg Allé. Hvis du passerer start så modtag " +
                "4000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        // Creating a simple data type, which contains the location of the player before they'll be moved to
        // Frederiksberg Allé.
        int beforeMoving = player.getSquare();

        // Moving the player
        moveWithADelay.movePlayerWithDelayToSpecificField(player, gui_player, gui, 11);

        // Checking if the location of the player before they move exceeds 11. If this is true, then the player
        // must pass the start field in order to get to Frederiksberg Allé.
        if (beforeMoving > 11) {
            gui.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
            player.setMoney(4000);
            gui_player.setBalance(player.getAccount().getMoney());

            /*
            We've created a method that changes the boolean value of passedStartField to be false. When it is false
            the player won't receive 4000 DKK two times when checking if player has passed the start field in the
            Game class.
             */
            player.resetPassedStartField();
        }
    }
}
