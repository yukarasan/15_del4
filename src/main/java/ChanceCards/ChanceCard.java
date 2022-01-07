package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;


public class ChanceCard {
    GUI_Controller gui = new GUI_Controller();


    public void playerLandsOnChanceField(Player player, GUI_Player gui_player){

        int[] chanceFields = {2, 7, 17, 22, 33, 36};

        int sq = player.getSquare();

        int randomCard = (int) (Math.random() * 6 + 1);

        if(IntStream.of(chanceFields).anyMatch(x -> x == sq)) {

            switch (randomCard) {

                case 1:
                    card1(gui_player, player);
                    break;
                case 2:
                    card2(gui_player, player);
                    break;
                case 3:
                    card3(gui_player, player);
                    break;
                case 4:
                    card4(gui_player, player);
                    break;
                case 5:
                    card5(gui_player, player);
                    break;
                case 6:
                    card6(gui_player, player);
                    break;
            }
        }
    }


    private void card1(GUI_Player gui_player, Player player) {

        gui.getInstance().displayChanceCard("Betal 200 DKK for levering af 2 kasser sodavand");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        player.getAccount().setMoney(-200);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    private void card2(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Betal din bilforsikring for 1000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(-1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    private void card3(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Du modtager dit aktieudbytte. Modtag 1000 DKK af banken");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    private void card4(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Du har solgt nogle gamle møbler. Modtag 1000 DKK ");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.getAccount().setMoney(1000);
        gui_player.setBalance(player.getAccount().getMoney());
    }

    private void card5(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Ryk frem til Frederiksberg Allé. Hvis du passerer start så modtag 4000 DKK");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");

        if(player.getSquare()>11){
            gui.getInstance().showMessage("Du modtager 4000 DKK for at passere start");
            player.setMoney(4000);
            gui_player.setBalance(player.getAccount().getMoney());
        }

        player.moveToHere(11);
        gui.getSpecificField(11).setCar(gui_player, true);
    }

    private void card6(GUI_Player gui_player, Player player) {
        gui.getInstance().displayChanceCard("Gå i fængsel. Du modtager ikke 4000 for at passere start");
        gui.getInstance().showMessage(player.getName() + ", træk et chancekort fra bunken");
        player.moveToHere(10);

        gui.getGameBoard().getJail().setPlayerInJail(gui_player, player);
        gui.getSpecificField(10).setCar(gui_player, true);
    }
}
