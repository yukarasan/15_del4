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
    private final CarRepair card9 = new CarRepair();
    private final NewTires card10 = new NewTires();
    private final ParkingFee card11 = new ParkingFee();
    private final Cigarettes card12 = new Cigarettes();
    private final Dentist card13 = new Dentist();
    private final Lottery card14 = new Lottery();
    private final Municipality card15 = new Municipality();
    private final WageIncrease card16 = new WageIncrease();
    private final BonusBond card17 = new BonusBond();
    private final UtilityGarden card18 = new UtilityGarden();
    private final MoveThreeFieldsForward card19 = new MoveThreeFieldsForward();
    private final MoveThreeFieldsBack card20 = new MoveThreeFieldsBack();
    private final Tipping card21 = new Tipping();
    int counter = 1;

    public void playerLandsOnChanceField(Player player, GUI_Player gui_player) {
        int[] chanceFields = {2, 7, 17, 22, 33, 36};
        int sq = player.getSquare();

        if(IntStream.of(chanceFields).anyMatch(x -> x == sq)) {

            if(counter==26){
                counter=1;
            }
            int pileOfCards = counter;

            switch (pileOfCards) {
                case 1 -> card1.deliveryOfSoda(gui_player, player);
                case 2 -> card2.carInsurance(gui_player, player);
                case 3 -> card3.dividend(gui_player, player);
                case 4 -> card4.oldFurniture(gui_player, player);
                case 5 -> card6.moveToJail(gui_player, player);
                case 6 -> card3.dividend(gui_player, player);
                case 7 -> card5.moveToFrederiksberg(gui_player, player);
                case 8 -> card8.carWash(gui_player, player);
                case 9 -> card7.runRedLight(gui_player, player);
                case 10 -> card3.dividend(gui_player, player);
                case 11 -> card9.carRepair(gui_player, player);
                case 12 -> card11.parkingFee(gui_player, player);
                case 13 -> card10.newTires(gui_player, player);
                case 14 -> card9.carRepair(gui_player, player);
                case 15 -> card12.cigarettes(gui_player, player);
                case 16 -> card13.dentist(gui_player, player);
                case 17 -> card14.lottery(gui_player, player);
                case 18 -> card17.bonusBond(gui_player, player);
                case 19 -> card15.municipality(gui_player, player);
                case 20 -> card16.wageIncrease(gui_player, player);
                case 21 -> card14.lottery(gui_player, player);
                case 22 -> card18.utilityGarden(gui_player, player);
                case 23 -> card19.moveThreeFieldsForward(gui_player, player);
                case 24 -> card20.moveThreeFieldsBack(gui_player, player);
                case 25 -> card21.tipping(gui_player, player);
            }
            counter++;
        }

    }
}
