package Main;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;

public class Game {
    GUI_Controller gui = new GUI_Controller();



    public void startGame() {

        gui.getInstance();
        round();
    }

    public void round() {
        DiceCup diceCup = new DiceCup();
        gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());



    }

}
