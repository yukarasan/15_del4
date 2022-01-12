package Sohaib;

import Main.Player;
import junit.framework.TestCase;

public class Test4 extends TestCase {

    public void testCheckIfPlayerLooses() {

        Player player1 = new Player();
        player1.getAccount().setMoney(-30000);
        player1.getAccount().getMoney();

        player1.setPlayerOutOfGame(true);

        System.out.println("player er gået bankerot fordi du har " + player1.getAccount().getMoney() + " DKK");
        assertEquals(0, player1.getAccount().getMoney());

    }
}