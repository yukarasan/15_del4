import Main.Player;
import junit.framework.TestCase;

public class MoveToSquareTest extends TestCase {

    public void testMoveToSquare() {
        Player player2 = new Player();
        player2.moveToSquare(40, 0);
        assertEquals(0, player2.getSquare());
    }
}