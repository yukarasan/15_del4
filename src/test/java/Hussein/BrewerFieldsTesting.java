package Hussein;

import Fields.Brewer;
import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class BrewerFieldsTesting {
    public static void main(String[] args) {
        GUI_Controller gui = new GUI_Controller();
        GUI_Car car = new GUI_Car();
        GUI_Player randomGuiPlayer = new GUI_Player("hej", 1, car);
        GUI_Car car1 = new GUI_Car();
        GUI_Player randomGuiPlayer1 = new GUI_Player("Nej", 2, car1);

        Player player = new Player();
        player.setName("Hej");

        GameBoard gameBoard = new GameBoard();
        gameBoard.initializeBrewers();

        //Buying first brewer field
        player.moveToSquare(12,0);
        //gameBoard.getBrewer(player).buyBrewer(player, gui.getga);


        Player player1 = new Player();
        player1.setName("tester");

        //Another player tests out the price when one is owned by the same player
        player1.moveToSquare(12,0);

        DiceCup diceCup = new DiceCup();

        Brewer[] brewers = new Brewer[2];
        gameBoard.getBrewer(player).payOwnerOfBrewer(player1, randomGuiPlayer1, diceCup, brewers);

        //Buying second brewer field to same player
        player.moveToHere(28);
        //gameBoard.getBrewer(player).buyBrewerField(player, randomGuiPlayer);


        //Another player tests out the price when two is owned by the same player
        player1.moveToHere(28);

        gameBoard.getBrewer(player).payOwnerOfBrewer(player1, randomGuiPlayer1, diceCup, brewers);
    }
}