package Sohaib;

import Main.DiceCup;
import Main.Player;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testMoveToSquare() {
        DiceCup diceCup = new DiceCup();
        Player player1 = new Player();
        Player player2 = new Player();
        Player test = new Player();

        player2.moveToSquare(40, 0);
        assertEquals(0, player2.getSquare());
    }
}