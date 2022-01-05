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
        createOwnableField();
    }

    public GUI_Field[] getField() {
        return fields;
    }

    public GUI_Field getSpecificField(int specificField){
        return fields[specificField];
    }

    private void createOwnableField() {
        OwnableField ownableField = new OwnableField();
        ownableField.setFields(fields,1,1200, Color.blue, "Rødovrevej");
        ownableField.setFields(fields,3, 1200,Color.blue,"Hvidovrevej");
        ownableField.setFields(fields,6, 2000,Color.red,"Roskildevej");
        ownableField.setFields(fields,8, 2000,Color.red,"Valby Langgade");
        ownableField.setFields(fields,9, 2400,Color.red,"Allegade");
        ownableField.setFields(fields,11, 2800,Color.yellow,"Frederiksberg Alle");
        ownableField.setFields(fields,13, 2800,Color.yellow,"Bulowsvej");
        ownableField.setFields(fields,14,3200,Color.yellow,"Gl. Kongevej");
        ownableField.setFields(fields,16,3600,Color.gray,"Bernstorffsvej");
        ownableField.setFields(fields,18,3600,Color.gray,"Hellerupvej");
        ownableField.setFields(fields,19,4000,Color.gray,"Strandvejen");
        ownableField.setFields(fields,21,4400,Color.RED,"Trianglen");
        ownableField.setFields(fields,23,4400,Color.RED,"Østerbrogade");
        ownableField.setFields(fields,24,4800,Color.RED,"Grønningen");
        ownableField.setFields(fields,26, 5200,Color.white,"Bredgade");
        ownableField.setFields(fields,27, 5200,Color.white,"Kgs.Nytorv");
        ownableField.setFields(fields,29, 5600,Color.white,"Østergade");
        ownableField.setFields(fields,31, 6000,Color.yellow,"Amagertorv");
        ownableField.setFields(fields,32, 6000,Color.yellow,"Vimmelskaffet");
        ownableField.setFields(fields,34, 6400,Color.yellow,"Nygade");
        ownableField.setFields(fields,37, 7000,Color.magenta,"Frederiksberg gade");
        ownableField.setFields(fields,39, 8000,Color.magenta,"Rådhuspladsen");
    }

}
