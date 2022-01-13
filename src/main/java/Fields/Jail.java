package Fields;

import Main.*;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Jail extends UnownableField {

    public void jailFields(GUI_Field[] fields) {
        fields[30].setSubText("Gå i fængsel");
        fields[30].setTitle(" ");

        fields[10].setTitle(" ");
        fields[10].setSubText("Besøg fængsel");
    }

}


