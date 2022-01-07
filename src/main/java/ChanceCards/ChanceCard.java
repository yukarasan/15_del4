package ChanceCards;

import Main.Player;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;


public class ChanceCard {
    private final DeliveryOfSoda card1 = new DeliveryOfSoda();
    private final CarInsurance card2 = new CarInsurance();
    private final Dividend card3 = new Dividend();
    private final OldFurniture card4 = new OldFurniture();
    private final MoveToFrederiksberg card5 = new MoveToFrederiksberg();
    private final MoveToJail card6 = new MoveToJail();
    private final RunRedLight card7 = new RunRedLight();
    private final CarWash card8 = new CarWash();

    public void playerLandsOnChanceField(Player player, GUI_Player gui_player) {

        int[] chanceFields = {2, 7, 17, 22, 33, 36};

        int sq = player.getSquare();

        int randomCard = (int) (Math.random() * 6 + 1);

        if(IntStream.of(chanceFields).anyMatch(x -> x == sq)) {

            switch (randomCard) {
                case 1 -> card1.deliveryOfSoda(gui_player, player);
                case 2 -> card2.carInsurance(gui_player, player);
                case 3 -> card3.dividend(gui_player, player);
                case 4 -> card4.oldFurniture(gui_player, player);
                case 5 -> card5.moveToFrederiksberg(gui_player, player);
                case 6 -> card6.moveToJail(gui_player, player);
                case 7 -> card7.runRedLight(gui_player, player);
                case 8 -> card8.carWash(gui_player, player);
            }
        }
    }
}
