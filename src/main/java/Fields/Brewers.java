package Fields;

import Main.Player;
import gui_fields.GUI_Player;

public class Brewers extends OwnableField {
    String chosenOption;

    public void buyBrewerField(Player player, GUI_Player gui_player){
        if (player.getSquare() == 12){
           chosenOption = gui.getInstance().getUserButtonPressed("Du er landet på .., vil du købe feltet","Ja","Nej");
            switch (chosenOption){
                case "Ja":
                    gui.getInstance().showMessage("Du Skal betale 3000 DKK");
                    player.getAccount().setMoney(-3000);
                    gui_player.setBalance(player.getAccount().getMoney());
                case "Nej":
                    gui.getInstance().showMessage("Du skal vente til det bliver din tur");
            }
        }
        if (player.getSquare() == 28){

        }

    }
}
