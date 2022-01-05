package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;
import java.awt.*;
import GUI_Controllor.*;

public class OwnableField{

    public void setFields(GUI_Field[] fields, int fieldNumber, int price, Color color) {
        GUI_Street field = new GUI_Street();
        field.setDescription(" ");
        field.setRent(String.valueOf(price));
        field = (GUI_Street) fields[fieldNumber];
    }

}
