package Hussein;

import GUI_Controllor.GUI_Controller;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.blue;

public class PlayerLosesMatchAndFieldsRestore {

    public static void main(String[] args) {

        GUI_Controller gui = new GUI_Controller();

        gui.getInstance();
        gui.getGameBoard().instantiatingFerries();
        gui.getGameBoard().initializeBrewers();
        gui.getGameBoard().createPropertiesPrices();

        Game game = new Game();

        Player player = new Player();
        player.setName("tester");

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(blue);
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);

        gui.getInstance().addPlayer(gui_player);


        player.moveToHere(1);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        Player[] players = new Player[1];
        players[0] = new Player();

        GUI_Player[] gui_players = new GUI_Player[2];

        //Making the player buy a field
        gui.getGameBoard().getProperty(player).landOnProperty(player, gui_player, gui.getGameBoard().getProperties(), players, gui_players);

        //Causing the player to go bankrupt
        player.getAccount().setMoney(-31000);

        //Checking this method from game, if the player is bankrupt, the player's field will
        //restore
        game.checkIfPlayerLooses(player, gui_player);

        //Creating another player
        Player player1 = new Player();
        player1.setName("Hej");
        GUI_Car car1 = new GUI_Car();
        GUI_Player gui_player1 = new GUI_Player("hej", player1.getAccount().getMoney(), car1);

        gui.getInstance().addPlayer(gui_player1);

        player1.moveToHere(1);

        //Making the other player buy the field that was just restored, to see if it works
        gui.getSpecificField(player1.getSquare()).setCar(gui_player1, true);
        gui.getGameBoard().getProperty(player1).landOnProperty(player1, gui_player1, gui.getGameBoard().getProperties(), players, gui_players);

    }

}
