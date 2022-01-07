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

        gui.getInstance().addPlayer(gui_player);
        gui.getInstance().addPlayer(gui_player1);

        gui.getInstance();

        gui.getInstance().showMessage("Next step");

        gui.getSpecificField(0).setCar(gui_player, true);

        gui.getInstance().showMessage("Next step");

        gui.getSpecificField(0).setCar(gui_player1, true);

        player.moveSquare(5,0);
        player1.moveSquare(5,0);

        gui.getInstance().showMessage("Next step");

        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        Ferry ferry = new Ferry();

        gui.getInstance().showMessage("Next step");

        ferry.buyFerry(player, gui_player);

        gui.getInstance().showMessage("Next step");

        gui.getSpecificField(player1.getSquare()).setCar(gui_player1, true);

        gui.getInstance().showMessage("Next step");

        ferry.payOwnerOfFerry(player1, gui_player1);


    }

}
