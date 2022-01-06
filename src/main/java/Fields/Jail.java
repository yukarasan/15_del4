package Fields;

import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;

public class Jail extends UnownableField{

    public void jailFields(GUI_Field[] fields){
        GUI_Jail inJail = new GUI_Jail();
        fields[30].setSubText("Du i fængsel");
        fields[30].setTitle(" ");

        GUI_Field visitJail = new GUI_Jail();
        fields[10].setTitle(" ");
        fields[10].setSubText("Besøg fængsel");
    }

    public void inJail(Player player){
        if(player.getSquare() == 30) {
            gui.getInstance().showMessage("Du er i fængsel og kan ikke komme ud");
            String chosenElement = gui.getInstance().getUserSelection("Du har tre valgmuligheder", "Slå to ens terninger", "Betal 1000 DKK");
        }
    }
}


