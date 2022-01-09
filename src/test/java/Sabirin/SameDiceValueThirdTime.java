package Sabirin;

import Fields.GameBoard;
import Fields.Jail;
import GUI_Controllor.GUI_Controller;
import Main.Die;
import Main.Game;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class SameDiceValueThirdTime {
   /*  public static void throwingSameDiceValue(Player player, GUI_Player gui_player, GUI_Controller gui, Jail jail) {
       if(getDice() == getDice() && getDice() == getDice() && !player.getInJail()) {
           gui.getInstance().showMessage(player.getName() + ", du har slået det samme, du må derfor slå igen");


       }
       if(getDice() == getDice() && getDice() == getDice() && getDice() == getDice() && getDice() == getDice()
               && !player.getInJail()){
           gui.getInstance().showMessage(player.getName() + ", du har slået det samme igen igen slå igen");
           gui.getInstance().setDice(5,5);
       }
       if (getDice() == getDice() && getDice() == getDice() && getDice() == getDice() && getDice() == getDice() &&
               getDice() == getDice() && getDice() == getDice() && !player.getInJail()) {
           gui.getInstance().showMessage(player.getName() + ", du har slået det samme igen igen igen, " +
                   "du må derfor gå i fængsel");
           jail.setPlayerInJail(gui_player, player);
       }
   }*/
    private static int dice;

    private void setDice(int die1, int die2) {
        this.dice = die1 + die2;
    }

    public static int getDice() {
        return dice;
    }


    public static void main(String[] args) {
        Game game = new Game();

        GameBoard gameBoard = new GameBoard();

        GUI_Controller gui = new GUI_Controller();

        Jail jail = new Jail();
        GUI_Car car1 = new GUI_Car();

        GUI_Player gui_player = new GUI_Player("f", 0, car1);
        Player player = new Player();
        player.setName("Tester");


        Die die = new Die();

        gui.getInstance().showMessage(player.getName() + ", du må slå med terningerne");

        gui.getInstance().setDice(5, 5);

        if (getDice() == getDice() && getDice() == getDice() && !player.getInJail()) {
            gui.getInstance().showMessage(player.getName() + ", du har slået det samme, du må derfor slå igen");
        }
        gui.getInstance().setDice(5, 5);

        if (getDice() == getDice() && getDice() == getDice() && getDice() == getDice() && getDice() == getDice() && !player.getInJail()) {
            gui.getInstance().showMessage(player.getName() +
                    ", du har slået det samme igen igen, du må slå en sidste gang");
        }
        gui.getInstance().setDice(5, 5);
        if(getDice() == getDice() && getDice() == getDice() && getDice() == getDice() && getDice() == getDice()
                && getDice() == getDice() && getDice() == getDice() && !player.getInJail()){
            gui.getInstance().showMessage(player.getName() + ", du har slået det samme tre gange " +
                    "og derfor skal du i fængsel");
            jail.setPlayerInJail(gui_player,player);
        }
       // throwingSameDiceValue(player,gui_player,gui,jail);
    }

}
