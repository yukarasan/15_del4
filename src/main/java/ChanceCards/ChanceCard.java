package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;


public class ChanceCard {

    GUI_Controller gui = new GUI_Controller();

    public void card1(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Betal 200 DKK for levering af 2 kasser sodavand");
        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    public void card2(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Betal din bilforsikring for 1000 DKK");
        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    public void card3(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Du modtager din aktieudbytte. Modtag 1000 DKK af banken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    public void card4(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Du har solgt nogle gamle møbler. Modtag 1000 DKK ");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    public void card5(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Ryk frem til Frederiksberg Allé. Hvis du passerer start så modtag 4000 DKK");
        player.moveToHere(11);
        gui.getSpecificField(11).setCar(gui_player, true);
    }

    public void card6(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Gå i fængsel. Du modtager ikke 4000 for at passere start");
        player.moveToHere(10);
        gui.getSpecificField(10).setCar(gui_player, true);
    }
}
