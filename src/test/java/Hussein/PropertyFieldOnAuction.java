package Hussein;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.blue;

public class PropertyFieldOnAuction {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        gameBoard.createPropertiesPrices();

        Player[] players = new Player[2];
        players[0] = new Player();
        players[0].setName("tester");
        players[0].getAccount().setMoney(-25000);

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(blue);

        GUI_Player[] gui_players = new GUI_Player[2];
        gui_players[0] = new GUI_Player(players[0].getName(), players[0].getAccount().getMoney(), car);

        players[1] = new Player();
        players[1].setName("hussein");
        GUI_Car car1 = new GUI_Car();
        gui_players[1] = new GUI_Player(players[1].getName(), players[1].getAccount().getMoney(), car1);

        GUI_Controller.getInstance().addPlayer(gui_players[0]);
        GUI_Controller.getInstance().addPlayer(gui_players[1]);

        //Moving player to a blue property and choosing to buy
        players[0].moveToHere(1);
        GUI_Controller.getSpecificField(players[0].getSquare()).setCar(gui_players[0], true);


        gameBoard.getProperty(players[0]).landOnProperty(players[0], gui_players[0], gameBoard.getProperties(), players, gui_players);

        System.out.println("Testing: " + players[0].getName());

    }

}
