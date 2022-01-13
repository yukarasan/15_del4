package Fields;
import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Player;
import java.util.concurrent.TimeUnit;

/**
 * In this class we've created some methods that move a player to their field by moving them one at a time
 * This is achieved by using a for-loop and a try catch statement.
 */

public class MoveWithADelay {
    public void movePlayerWithADelay(GUI_Player gui_player, Player player, DiceCup diceCup) {
        for (int i = 0; i < (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()); i++) {
            player.moveOneSquare(1);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Player moves to the square that is added
            GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);
        }
    }

    public void movePlayerWithDelayInChanceCard(Player player, int move, GUI_Player gui_player) {
        for (int i = 0; i < move; i++) {
            player.moveOneSquare(1);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Moves the player to the square dependent on the value of move
            GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);
        }
    }

    public void movePlayerWithDelayToSpecificField(Player player, GUI_Player gui_player, int move) {
        while (player.getSquare() != move) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player.moveOneSquare(1);

            // Moves the player to the square dependent on the value of move
            GUI_Controller.getSpecificField(player.getSquare()).setCar(gui_player, true);
        }
    }
}

