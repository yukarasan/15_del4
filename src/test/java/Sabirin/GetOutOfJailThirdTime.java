package Sabirin;
import Fields.GameBoard;
import Fields.Jail;
import GUI_Controllor.GUI_Controller;
import Main.Account;
import Main.DiceCup;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class GetOutOfJailThirdTime {


    public static void main(String[] args) {
        Game game = new Game();

        GameBoard gameBoard = new GameBoard();

        GUI_Controller gui = new GUI_Controller();
        gui.getInstance();

        DiceCup diceCup = new DiceCup();
        Account account = new Account();

        Jail jail = new Jail();
        GUI_Car car1 = new GUI_Car();

        Player player = new Player();

        player.setName("Tester");
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car1);
        gui.getInstance().addPlayer(gui_player);

        gui.getSpecificField(10).setCar(gui_player, true);

        gui.getInstance().showMessage("du må kaste med terningerne");

        gui.getInstance().setDice(3,4);

        if(diceCup.getDie1()!= diceCup.getDie2() && player.getInJail()){
            gui.getInstance().showMessage("DI HAR IKKE SLÅET DET SAMME OG DERVED SKAL DU STAIDG I FÆNGSEL");
            player.setInJail(true);
            player.getWaitATurn();
            gui.getSpecificField(10).setCar(gui_player, true);
        }

        gui.getInstance().setDice(5,4);
        gui.getInstance().showMessage("du må kaste med terningerne igen");

        if(diceCup.getDie1()!= diceCup.getDie2()&& player.getInJail()){
            gui.getInstance().showMessage("DU HAR IKKE SLÅET DET SAMME OG DERVED SKAL DU STADITherefore, we have worked with an in-depth analysis of the customer's needs to create a Use case and a variety of other diagrams that explain how we'd build and construct our system.G I FÆNGSEL");
            player.setInJail(true);
            player.getWaitATurn();
            gui.getSpecificField(10).setCar(gui_player, true);
        }

        gui.getInstance().setDice(2,4);
        gui.getInstance().showMessage("du må kaste med terningerne igen igen");

        if(diceCup.getDie1() != diceCup.getDie2() && !player.getInJail()){
            gui.getInstance().showMessage("Du har ikke slået det samme men nu skal du betale 1000 DKK for at komme ud af fængslet");
            player.getAccount().setMoney(-1000);
            gui_player.setBalance(player.getAccount().getMoney());
            gui.getSpecificField(10).setCar(gui_player, false);
            gui.getSpecificField(32).setCar(gui_player, true);
        }
    }

}

