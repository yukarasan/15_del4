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
        GUI_Controller.getInstance();
        GUI_Controller.getGameBoard().initializeBrewers();
        GUI_Car car = new GUI_Car();
        GUI_Player randomGuiPlayer = new GUI_Player("hej", 1, car);
        GUI_Car car1 = new GUI_Car();
        GUI_Player randomGuiPlayer1 = new GUI_Player("Nej", 2, car1);

        Player player = new Player();
        player.setName("Hej");

        //Buying first brewer field
        player.moveToSquare(12,0);
        //gameBoard.getBrewer(player).buyBrewer(player, gui.getga);


        Player player1 = new Player();
        player1.setName("tester");

        //Another player tests out the price when one is owned by the same player
        player1.moveToSquare(12,0);

        DiceCup diceCup = new DiceCup();

        Brewer[] brewers = new Brewer[2];
        GUI_Player[] gui_players = new GUI_Player[2];
        Player[] players = new Player[2];



        GUI_Controller.getGameBoard().getBrewer(player).buyBrewer(player, randomGuiPlayer, GUI_Controller.getGameBoard().getBrewers(), players, gui_players);

        //Buying second brewer field to same player, to see if the rent increases
        player.moveToHere(28);
        GUI_Controller.getGameBoard().getBrewer(player).buyBrewer(player, randomGuiPlayer, GUI_Controller.getGameBoard().getBrewers(), players, gui_players);


        //Another player tests out the price when two is owned by the same player
        player1.moveToHere(28);

        GUI_Controller.getGameBoard().getBrewer(player).payOwnerOfBrewer(player1, randomGuiPlayer1, diceCup, brewers);
    }
}