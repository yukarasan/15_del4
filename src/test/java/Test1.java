import Main.Player;

import static junit.framework.Assert.assertEquals;

public class Test1 {
    public void testGetMoney() {
        Player player = new Player();

        player.setMoney(-1000);
        assertEquals(29_000, player.getAccount().getMoney());

    }
}
