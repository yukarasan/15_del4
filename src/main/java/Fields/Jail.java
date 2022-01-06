package Fields;


import Main.*;
import Main.Account;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Jail extends UnownableField {



              
    public void jailFields(GUI_Field[] fields){
        fields[30].setSubText("Du i fængsel");
        fields[30].setTitle(" ");

        fields[10].setTitle(" ");
        fields[10].setSubText("Besøg fængsel");
    }

    public void inJail(GUI_Player gui_player, Player player) {

            if(player.getSquare() == 30) {
                gui.getInstance().showMessage(gui_player.getName() + " rykkes nu til fængsel");
                gui.getSpecificField(10).setCar(gui_player, true);
                player.moveToHere(10);
            }

        }


        public void outOfJail (GUI_Player gui_player, Player player, DiceCup diceCup) {

            String chosenElement = gui.getInstance().getUserSelection("Du har to valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");
                switch (chosenElement) {
                    case "Slå to ens terninger":
                        gui.getInstance().setDice(diceCup.getDie1().rollDice(), diceCup.getDie2().rollDice());

                        break;
                    case "Betal 1000 DKK":
                        Account account = new Account();
                        player.getAccount().setMoney(-1000);
                }
        }
}


