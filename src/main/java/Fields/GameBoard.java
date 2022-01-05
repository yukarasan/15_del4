package Fields;

import Fields.OwnableField;
import Fields.UnownableField;
import GUI_Controllor.GUI_Controllor;
import gui_fields.GUI_Field;
import gui_main.GUI;

import java.awt.*;

public class GameBoard {



    public void placeBoard() {
        GUI gui_controllor = GUI_Controllor.getInstance();
    }

    Field field = new Field();

    public void allField() {
        UnownableField unownableField = new UnownableField();

        unownableField.startField();
        unownableField.chanceField();
        unownableField.jailField();
        unownableField.freeParking();

        createOwnableField();
    }

    public GUI_Field[] getField() {
        return field.getFields();
    }

    private void createOwnableField() {
        OwnableField ownableField = new OwnableField();
        ownableField.setFields(1,10, Color.black);
    }



    public void showField() {

    }


}
