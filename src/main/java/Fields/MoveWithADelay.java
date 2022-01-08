package Fields;
import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Player;
import java.util.concurrent.TimeUnit;

public class MoveWithADelay {

    public void movePlayerWithADelay(GUI_Player gui_player, Player player, DiceCup diceCup, GUI_Controller gui) {

        for (int i = 0; i < (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()); i++) {

            player.moveSquare(1, 0);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Player moves to the square that is plussed
            gui.getSpecificField(player.getSquare()).setCar(gui_player, true);
        }
    }

    public void movePlayerWithDelayInChanceCard(Player player, int move, GUI_Player gui_player, GUI_Controller gui) {

        for (int i = 0; i < move; i++) {
            player.moveSquare(1, 0);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Moves the player to the square dependent on the value of move
            gui.getSpecificField(player.getSquare()).setCar(gui_player, true);
        }
    }

    public void movePlayerWithDelayToSpecificField(Player player, GUI_Player gui_player, GUI_Controller gui, int move) {

        while (player.getSquare() != move) {

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.moveSquare(1, 0);

            // Moves the player to the square dependent on the value of move

            gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        }
    }
}

