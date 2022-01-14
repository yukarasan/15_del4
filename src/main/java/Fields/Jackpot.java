package Fields;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Tax;
import java.awt.*;

public class Jackpot extends UnownableField {
    private static int jackpotBundle;

    /** Setting the jackpot fields design on the board itself*/
    public void jackpotField(GUI_Field[] field) {
        field[4] = new GUI_Tax();
        field[4].setSubText("Betal 1000");
        field[4].setTitle("Jackpot?");
        field[4].setBackGroundColor(Color.darkGray);
        field[4].setForeGroundColor(Color.RED);

        field[38] = new GUI_Tax();
        field[38].setSubText(jackpotBundle + " DKK");
        field[38].setTitle("Jackpot?");
        field[38].setBackGroundColor(Color.darkGray);
        field[38].setForeGroundColor(Color.GREEN);
    }

    /**If the player lands on the jackpot field on field 4, the player has to pay 1000 to the other field*/
    public void payToJackpot(Player player, GUI_Player gui_player) {
        if (player.getSquare() == 4) {
            GUI_Controller.getInstance().showMessage(player.getName() + ", du er landet på Jackpot og skal betale 1000 DKK");
            player.getAccount().setMoney(-1000);
            gui_player.setBalance(player.getAccount().getMoney());
            jackpotBundle += 1000;

            GUI_Controller.getSpecificField(38).setSubText(jackpotBundle + " DKK");
        }
    }

    /**If there is more than zero amount in the jackpotBundle, the player receives them
     *  when they land on square number 38*/
    public void receiveJackpot(Player player, GUI_Player gui_player) {
        if (player.getSquare() == 38 && jackpotBundle>0) {
            GUI_Controller.getInstance().showMessage(player.getName() + ", du har landet på jackpot og skal modtage "
                                            + jackpotBundle + " DKK!");
            player.getAccount().setMoney(jackpotBundle);
            gui_player.setBalance(player.getAccount().getMoney());
            jackpotBundle = 0;
            GUI_Controller.getSpecificField(38).setSubText(jackpotBundle + " DKK");
        }
    }
}