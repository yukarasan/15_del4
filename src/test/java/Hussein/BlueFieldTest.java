package Hussein;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.blue;
import static java.awt.Color.red;

public class BlueFieldTest {

    public static void main(String[] args) {
        //Testing out landOnProperty method from Property class

        GameBoard gameBoard = new GameBoard();

        gameBoard.createPropertiesPrices();

        Player player = new Player();
        player.setName("tester");
        player.getAccount().setMoney(-25000);

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(blue);
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);

        GUI_Controller gui = new GUI_Controller();

        gui.getInstance().addPlayer(gui_player);

        //Moving player to a blue property and choosing to buy
        player.moveToHere(1);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        Player[] players = new Player[1];
        players[0] = new Player();

        GUI_Player[] gui_players = new GUI_Player[2];

        gameBoard.getProperty(player).landOnProperty(player, gui_player, gameBoard.getProperties(), players, gui_players);

        //Creating a new player and makes the new player land on the now owned blue property
        Player player1 = new Player();
        player1.setName("Tester1");

        GUI_Car car1 = new GUI_Car();
        car1.setPrimaryColor(red);

        GUI_Player gui_player1 = new GUI_Player(player1.getName(), player1.getAccount().getMoney(), car1);
        gui.getInstance().addPlayer(gui_player1);

        //Now moving the new player
        player1.moveToHere(1);
        gui.getSpecificField(player1.getSquare()).setCar(gui_player1, true);

        gameBoard.getProperty(player1).landOnProperty(player1, gui_player1, gameBoard.getProperties(), players, gui_players);

        //Moving the owner to the other blue field to make the same owner of two blue fields
        //to check if the price changes from 50 to 100

        player.moveToHere(3);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);
        gameBoard.getProperty(player).landOnProperty(player, gui_player, gameBoard.getProperties(), players, gui_players);

        gameBoard.getProperty(player).optionsWhenOwningAllFields(gameBoard.getProperties(), player);


        //Now checking the tester player on the same field as before, and see if the price has changed
        //since the same owner owns both blue
        gameBoard.getProperty(player1).landOnProperty(player1, gui_player1, gameBoard.getProperties(), players, gui_players);
    }
}
