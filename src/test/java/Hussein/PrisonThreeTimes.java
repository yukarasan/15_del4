package Hussein;

import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;


//This test is about when a player is in jail, the third time, if they have enough money,
//they have to pay.
public class PrisonThreeTimes {

    public static void main(String[] args) {

        Game game = new Game();
        GUI_Controller gui = new GUI_Controller();

        Player player = new Player();
        player.setName("Huss");
        GUI_Car car = new GUI_Car();
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);
        gui.getInstance().addPlayer(gui_player);

        //Moving him to the go to jail field
        player.moveToHere(30);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);
        game.setPlayerInJail(gui_player, player);
        player.setWaitATurn(false);

        DiceCup diceCup = new DiceCup();

        //getting an unlimited amount of tries to get out of jail. The tested method is from
        //Game class
        while(true) {
            game.outOfJail(player, gui_player, diceCup);
        }
    }

}
