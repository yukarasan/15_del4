package Hussein;

import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class PrisonThreeTimes {

    public static void main(String[] args) {

        Game game = new Game();
        GUI_Controller gui = new GUI_Controller();

        Player player = new Player();
        player.setName("Huss");
        GUI_Car car = new GUI_Car();
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);
        gui.getInstance().addPlayer(gui_player);

        player.moveToHere(30);
        player.getAccount().setMoney(-28000);

        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);
        game.setPlayerInJail(gui_player, player);
        player.setWaitATurn(false);

        DiceCup diceCup = new DiceCup();

        while(true) {
            game.outOfJail(player, gui_player, diceCup);
        }



    }

}
