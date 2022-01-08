package Fields;

import Main.DiceCup;
import Main.Player;
import gui_fields.GUI_Player;

import java.util.stream.IntStream;

public class Brewer extends OwnableField {
    private Player owner;
    private GUI_Player guiOwner;
    private int fieldPrice;
    private final int[] brewerFields = {12, 28};
    private boolean isOwned;

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
}
