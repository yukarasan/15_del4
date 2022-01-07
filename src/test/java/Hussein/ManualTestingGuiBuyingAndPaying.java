package Hussein;

import Fields.Ferry;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import static java.awt.Color.BLACK;
import static java.awt.Color.YELLOW;

//test for buying a ferry and then another player steps on it and pays the owner
public class ManualTestingGuiBuyingAndPaying {

    public static void main(String[] args) {
        GUI_Controller gui = new GUI_Controller();

        Player player = new Player();
        Player player1 = new Player();

        player.setName("Hussein");
        player1.setName("Sohaib");

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(YELLOW);

        GUI_Car car1 = new GUI_Car();
        car1.setPrimaryColor(BLACK);

        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);
        GUI_Player gui_player1 = new GUI_Player(player1.getName(), player1.getAccount().getMoney(), car1);

        /*gui.getInstance().addPlayer(gui_player);
        gui.getInstance().addPlayer(gui_player1);
         */

        gui.getGameBoard().instantiatingFerries();

        player.moveToHere(5);

        gui.getInstance().showMessage("First buy");
        gui.getGameBoard().getFerry(player).buyFerry(player,gui_player,gui.getGameBoard().getFerries());
        System.out.println("Price of first ferry before second buy: " + gui.getGameBoard().getFerries()[0].getRentPrice());

        player.moveToHere(15);

        gui.getInstance().showMessage("Second buy");
        gui.getGameBoard().getFerry(player).buyFerry(player, gui_player, gui.getGameBoard().getFerries());
        System.out.println("Price of first ferry after second buy: " + gui.getGameBoard().getFerries()[1].getRentPrice());

    }

}
