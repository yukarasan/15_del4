package Fields;

import Main.*;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Jail extends UnownableField {
    MoveWithADelay moveWithADelay = new MoveWithADelay();

              
    public void jailFields(GUI_Field[] fields){
        fields[30].setSubText("Du i fængsel");
        fields[30].setTitle(" ");

        fields[10].setTitle(" ");
        fields[10].setSubText("Besøg fængsel");
    }

    public void setPlayerInJail(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
        gui.getSpecificField(10).setCar(gui_player, true);
        player.moveToHere(10);
        player.setInJail(true);
        player.setWaitATurn(true);
    }

        public void outOfJail (GUI_Player gui_player, Player player, DiceCup diceCup) {

            String chosenElement = gui.getInstance().getUserButtonPressed(player.getName() + ", du har to valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");

            switch (chosenElement) {
                case "Slå to ens terninger" -> {
                    gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                    if (diceCup.getDie1().getFaceValue() == diceCup.getDie2().getFaceValue()) {
                        gui.getInstance().showMessage("Tilykke! Du slog to ens. Du må nu rykke ud af fængslet og slå igen.");
                        player.moveToSquare(diceCup.getDie1().getFaceValue(), diceCup.getDie2().getFaceValue());
                        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

                        gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                        gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());

                        moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup, gui);

                        player.setInJail(false);
                        player.setWaitATurn(false);

                    } else {
                        gui.getInstance().showMessage("Desværre, bedre held næste gang. Du må vente en runde.");
                    }
                }
                case "Betal 1000 DKK" -> {
                    player.getAccount().setMoney(-1000);
                    gui_player.setBalance(player.getAccount().getMoney());
                    gui.getInstance().showMessage("Tak for pengene. Du må nu slå igen for at komme ud.");
                    gui.getInstance().getUserButtonPressed(player.getName() + ", kast terningerne", "Kast");
                    gui.getInstance().setDice(diceCup.getDie1().rollDie(), diceCup.getDie2().rollDie());
                    moveWithADelay.movePlayerWithADelay(gui_player, player, diceCup, gui);
                    player.setInJail(false);
                    player.setWaitATurn(false);
                }
            }
        }
}


