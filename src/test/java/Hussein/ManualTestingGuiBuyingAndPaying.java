package Hussein;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.BLACK;
import static java.awt.Color.YELLOW;

//test for buying a ferry and then another player steps on it and pays the owner
public class ManualTestingGuiBuyingAndPaying {

    public static void main(String[] args) {
        GUI_Controller.getInstance();

        Player player = new Player();

        player.setName("Hussein");

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(YELLOW);

        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);

        GUI_Controller.getGameBoard().instantiatingFerries();

        player.moveToHere(5);

        Player[] players = new Player[1];
        GUI_Player[] gui_players = new GUI_Player[1];


        GUI_Controller.getInstance().showMessage("First buy");
        GUI_Controller.getGameBoard().getFerry(player).buyFerry(player, gui_player, GUI_Controller.getGameBoard().getFerries(), players, gui_players);

        System.out.println("Price of first ferry before second buy: " + GUI_Controller.getGameBoard().getFerries()[0].getRentPrice());

        player.moveToHere(15);

        GUI_Controller.getInstance().showMessage("Second buy");
        GUI_Controller.getGameBoard().getFerry(player).buyFerry(player, gui_player, GUI_Controller.getGameBoard().getFerries(), players, gui_players);
        System.out.println("Price of first ferry after second buy: " + GUI_Controller.getGameBoard().getFerries()[1].getRentPrice());
    }
}
