package Fields;

import Main.DiceCup;
import Main.Die;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Player;

public class Jail extends UnownableField{

    public void jailFields(GUI_Field[] fields){
        fields[30].setSubText("Du i fængsel");
        fields[30].setTitle(" ");

        fields[10].setTitle(" ");
        fields[10].setSubText("Besøg fængsel");
    }

    public void inJail(GUI_Player gui_player, Player player){
        String chosenElement = null;
        if(player.getSquare() == 30) {
            gui.getInstance().showMessage("Du er i fængsel og kan ikke komme ud");
            gui.getSpecificField(10).setCar(gui_player, true);
            chosenElement = gui.getInstance().getUserSelection("Du har to valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");
        }
        switch (chosenElement){
            case "Slå to ens terninger":
                DiceCup diceCup = new DiceCup();
                gui.getInstance().setDice(diceCup die1, diceCup die2);






               Main.DiceCup diceCup = new DiceCup();
               gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());




            case "Betal 1000 DKK":


        }
    }
}


