package Fields;

import GUI_Controllor.GUI_Controller;
import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;

public class Brewer extends OwnableField {
    private Player owner;
    private GUI_Player guiOwner;
    private int fieldPrice;
    private final int[] brewerFields = {12, 28};
    private boolean isOwned, isMortgaged;
    private int mortgagePrice = 2000;

    public void setBrewerOnMortgage(Player player){

        gui.getInstance().showMessage(player.getName() + ", du har valgt at pantsætte denne brewer");

        }



    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public void buyBrewerField(Player player, GUI_Player gui_player) {

        String chosenOption;

        if (IntStream.of(brewerFields).anyMatch(x -> x == player.getSquare()) && !isOwned) {

            chosenOption = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                    gui.getSpecificField(player.getSquare()).getTitle() + ", vil du købe feltet for 3000 DKK?", "Ja", "Nej");

            if (chosenOption.equals("Ja")) {
                player.getAccount().setMoney(-3000);
                gui_player.setBalance(player.getAccount().getMoney());
                gui.getSpecificField(player.getSquare()).setSubText(player.getName());
                player.setBrewersOwned();
                this.owner = player;
                this.guiOwner = gui_player;
                isOwned = true;
            }
        }
    }

    public void payOwnerOfBrewer(Player player, GUI_Player gui_player, DiceCup diceCup) {

        if (IntStream.of(brewerFields).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner) {
            switch (owner.getBrewersOwned()) {
                case 1 -> fieldPrice = (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()) * 100;
                case 2 -> fieldPrice = (diceCup.getDie1().getFaceValue() + diceCup.getDie2().getFaceValue()) * 200;
            }

            gui.getInstance().getUserButtonPressed("Oh oh.. " + player.getName() + ", du er landet på " +
            owner.getName() + "'s sodavandsfabrik: " + gui.getSpecificField(player.getSquare()).getTitle() +
            ". Du skal betale " + fieldPrice + " DKK", "Betal");

            player.getAccount().setMoney(-fieldPrice);
            gui_player.setBalance(player.getAccount().getMoney());

            owner.getAccount().setMoney(fieldPrice);
            guiOwner.setBalance(owner.getAccount().getMoney());
        }
    }

    public Player getOwner() {
        return owner;
    }

    /*public static void main(String[] args) {

        GUI_Controller gui = new GUI_Controller();

        Player player = new Player();
        player.setName("Hussein");
        GUI_Car car = new GUI_Car();
        GUI_Player gui_player = new GUI_Player("Hussein", player.getAccount().getMoney(), car);

        Brewer brewer = new Brewer();

        player.moveToHere(12);
        brewer.buyBrewerField(player, gui_player);

        System.out.println("Owner name now: " + brewer.getOwner().getName());

        String chosenOption = gui.getInstance().getUserButtonPressed("Hvad ønsker du nu at gøre?",
                "Afslut tur", "Pantsæt ejendom", "");

    if(chosenOption.equals("Pantsæt ejendom")){

        brewer.setMortgaged(true);

        }
    }

     */
}
