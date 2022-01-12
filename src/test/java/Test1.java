import Main.Player;
import junit.framework.TestCase;
//This JUnit testes the method setMoney. So if we know set money to -100, then the players
// account should be 29_900.
public class Test1 extends TestCase {

    public void testSetMoney() {
        Player player = new Player();
        player.setName("Tester");

        player.setMoney(-100);
        assertEquals(29_900, player.getAccount().getMoney());
        System.out.println(player.getName() + " Du har nu " + player.getAccount().getMoney() + " DKK tilbage");
    }
}