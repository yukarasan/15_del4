package Hussein;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.blue;

public class BrewerOnAuctionTest {

    public static void main(String[] args) {

        GUI_Controller gui = new GUI_Controller();
        gui.getGameBoard().createPropertiesPrices();
        gui.getGameBoard().initializeBrewers();

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

        gui.getInstance().addPlayer(gui_players[0]);
        gui.getInstance().addPlayer(gui_players[1]);

        //Moving player to a blue property and choosing to buy
        players[0].moveToHere(12);
        gui.getSpecificField(players[0].getSquare()).setCar(gui_players[0], true);

        gui.getGameBoard().getBrewer(players[0]).buyBrewer(players[0], gui.getGameBoard().getBrewers(), players, gui_players);
    }

}
