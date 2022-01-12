import Fields.Jail;
import Main.Player;
import junit.framework.TestCase;

public class Test2 extends TestCase {

    public void testOutOfJail() {
        Player player = new Player();
        Jail jail = new Jail();

        player.setInJail(true);
        player.setMoney(-1000);
        assertEquals(29000, player.getAccount().getMoney());
        player.setInJail(false);

    }
}