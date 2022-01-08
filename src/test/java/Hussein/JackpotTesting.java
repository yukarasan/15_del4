package Hussein;

import Fields.GameBoard;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class JackpotTesting {

    public static void main(String[] args) {
        //To test out jackpot class

        Player player = new Player();
        GUI_Car car = new GUI_Car();

        GUI_Player gui_player = new GUI_Player("Hussein", player.getAccount().getMoney(), car);

        GameBoard gameBoard = new GameBoard();

        System.out.println("Player money before moving to pay jackpot: " + player.getAccount().getMoney());

        //Moving player to the payer field of jackpot and makes player pay with method
        player.moveToHere(4);
        gameBoard.getJackpot().payToJackpot(player, gui_player);
        System.out.println("Player money after moving to pay jackpot: " + player.getAccount().getMoney());


        //Moving the player to receiver field of jackpot and makes player receive with method
        player.moveToHere(38);
        gameBoard.getJackpot().receiveJackpot(player, gui_player);
        System.out.println("Player money before moving to receive jackpot: " + player.getAccount().getMoney());


    }

}
