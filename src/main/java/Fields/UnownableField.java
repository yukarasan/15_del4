package Fields;

import gui_fields.*;

public class UnownableField extends Field{

    public void startField(GUI_Field[] fields) {
        fields[0] = new GUI_Start();
   

    }

    public void jailField(GUI_Field[] fields) {
        fields[10] = new GUI_Jail();
        fields[30] = new GUI_Jail();
    }

    public void freeParking(GUI_Field[] fields) {
        fields[20] = new GUI_Refuge();
    }

    public void chanceField(GUI_Field[] fields) {
        fields[2] = new GUI_Chance();
        fields[7] = new GUI_Chance();
        fields[16] = new GUI_Chance();
        fields[22] = new GUI_Chance();
        fields[33] = new GUI_Chance();
        fields[36] = new GUI_Chance();
    }
}
