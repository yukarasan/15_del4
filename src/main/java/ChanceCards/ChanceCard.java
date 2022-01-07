package ChanceCards;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;


public class ChanceCard {
    protected GUI_Controller gui = new GUI_Controller();
    private final DeliveryOfSoda card1 = new DeliveryOfSoda();
    private final CarInsurance card2 = new CarInsurance();
    private final Dividend card3 = new Dividend();
    private final OldFurniture card4 = new OldFurniture();
    private final MoveToFrederiksberg card5 = new MoveToFrederiksberg();
    private final MoveToJail card6 = new MoveToJail();

    public void playerLandsOnChanceField(Player player, GUI_Player gui_player){

        int[] chanceFields = {2, 7, 17, 22, 33, 36};

        int sq = player.getSquare();

        int randomCard = (int) (Math.random() * 6 + 1);

        if(IntStream.of(chanceFields).anyMatch(x -> x == sq)) {

            switch (randomCard) {
                case 1:
                    card1.deliveryOfSoda(gui_player, player);
                    break;
                case 2:
                    card2.carInsurance(gui_player, player);
                    break;
                case 3:
                    card3.dividend(gui_player, player);
                    break;
                case 4:
                    card4.oldFurniture(gui_player, player);
                    break;
                case 5:
                    card5.moveToFrederiksberg(gui_player, player);
                    break;
                case 6:
                    card6.moveToJail(gui_player, player);
                    break;
            }
        }
    }
}
