package Hussein;

import Fields.Ferry;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;

public class FerryTesting {
    private int intHelper;
    private Ferry[] ferries = new Ferry[4];
    private int[] ferryFields = {5, 15, 25, 35};
    private int getRent = 500;


    public void initializeFerries(){
        for(int i=0; i<ferries.length; i++){
            ferries[i] = new Ferry();
        }
    }

    public void checkIfLandedFerryField(Player player){

        switch (player.getSquare()) {
            case 5 -> intHelper = 0;
            case 15 -> intHelper = 1;
            case 25 -> intHelper = 2;
            case 35 -> intHelper = 3;
        }
    }

    public void buyFerry(Player player, GUI_Player randomGuiPlayer) {

        checkIfLandedFerryField(player);

        if (IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && !ferries[intHelper].getIsFerryOwned()) {

            System.out.println(player.getName() + ", du er landet på en færge, vil du købe den for 4000 DKK?");

            ferries[intHelper].setOwner(player, randomGuiPlayer, ferries);

        }
    }

    public void payOwnerOfFerry(Player player) {

        checkIfLandedFerryField(player);


        if (ferries[intHelper].getIsFerryOwned()) {

            System.out.println("Oh oh.. " + player.getName() + ", du har landet på " +
            ferries[intHelper].getOwner().getName() + "'s færge, du skal betale " + ferries[intHelper].getRentPrice() + " DKK");


            player.getAccount().setMoney(-ferries[intHelper].getRentPrice());
            ferries[intHelper].getOwner().getAccount().setMoney(ferries[intHelper].getRentPrice());



        }
    }

    public static void main(String[] args) {
        FerryTesting ferryTesting = new FerryTesting();

        ferryTesting.initializeFerries();

        Player player = new Player();
        player.setName("Tester");

        Player player1 = new Player();
        player1.setName("Tester1");

        System.out.println("Money now before buy: " + player.getAccount().getMoney());

        player.moveToSquare(5,0);

        //
        GUI_Car randomCar = new GUI_Car();
        GUI_Player randomNoUse = new GUI_Player("test", 1, randomCar);
        //

        ferryTesting.buyFerry(player, randomNoUse);

        System.out.println("Money now after buy: " + player.getAccount().getMoney());

        player1.moveToSquare(5,0);

        ferryTesting.payOwnerOfFerry(player1);

        System.out.println("Your money now after landing: " + player1.getAccount().getMoney());

    }

}
