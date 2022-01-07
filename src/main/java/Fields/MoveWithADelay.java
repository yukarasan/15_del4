package Fields;
import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Player;
import java.util.concurrent.TimeUnit;

public class MoveWithADelay {

    public void movePlayerWithADelay(GUI_Player gui_player, Player player, DiceCup diceCup, GUI_Controller gui){

        for (int i = 0; i < (diceCup.getDie1().getDie() + diceCup.getDie2().getDie()); i++) {

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
}
