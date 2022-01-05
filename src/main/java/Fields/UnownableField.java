package Fields;

import gui_fields.GUI_Chance;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Start;

public class UnownableField extends Field {

    public void startField() {
        fields[0] = new GUI_Start();
    }

    public void jailField() {
        fields[10] = new GUI_Jail();
        fields[30] = new GUI_Jail();
    }

    public void freeParking() {
        fields[20] = new GUI_Refuge();
    }

    public void chanceField() {
        fields[2] = new GUI_Chance();
        fields[7] = new GUI_Chance();
        fields[16] = new GUI_Chance();
        fields[22] = new GUI_Chance();
        fields[33] = new GUI_Chance();
        fields[36] = new GUI_Chance();
    }

}
