package Fields;

import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;
import java.awt.*;
import GUI_Controllor.*;

public class OwnableField extends Field{
    Player owner;

    public void setFields(GUI_Field[] fields, int fieldNumber, int price, Color color, String name) {
        GUI_Street field = new GUI_Street();
        field.setDescription(" ");
        field.setRent(String.valueOf(price));
        field.setSubText("Pris: " + price + " DKK");
        field.setBackGroundColor(color);
        field.setTitle(name);
        fields[fieldNumber] = field;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
