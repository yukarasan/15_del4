package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import java.awt.*;

public class GameBoard {
    private final GUI_Field[] fields = new GUI_Field[40];

    public void instantiatingFields(){

        for(int i=0; i<fields.length; i++){
            fields[i] = new GUI_Street();
        }
        allField();
    }

    public void allField() {
        UnownableField unownableField = new UnownableField();
        Jackpot jackpot = new Jackpot();

        unownableField.startField(fields);
        unownableField.chanceField(fields);
        unownableField.jailField(fields);
        unownableField.freeParking(fields);
        jackpot.jackPot(fields);
    }

    public GUI_Field[] getField() {
        return fields;
    }

    public GUI_Field getSpecificField(int specificField){
        return fields[specificField];
    }

    private void createOwnableField() {
        OwnableField ownableField = new OwnableField();
        ownableField.setFields(fields,1,10, Color.yellow, "hej");

    }

}
