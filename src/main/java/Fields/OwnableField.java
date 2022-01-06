package Fields;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import java.awt.*;

public class OwnableField extends Field{
    private Player owner;

    //Below is the rent on various occasions of ownable fields such as properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned
    private int rentNoHouse, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
    rentHotel, fieldPrice, costOfOneHouse, costOfHotel;

    public void setFields(GUI_Field[] fields, int fieldNumber, int price, Color color, String name) {
        GUI_Street field = new GUI_Street();
        field.setDescription(" ");
        field.setRent(String.valueOf(price));
        field.setSubText("Pris: " + price);
        field.setBackGroundColor(color);
        field.setTitle(name);
        fields[fieldNumber] = field;
    }


    public void setUpOwnableField(int rentNoHouse, int rentAllOwned, int rentOneHouse, int rentTwoHouse, int rentThreeHouse,
                                  int rentFourHouse, int rentHotel, int fieldPrice, int fieldNumber,
                                  int costOfOneHouse, int costOfHotel, GUI_Controller gui){
        this.costOfHotel = costOfHotel;
        this.costOfOneHouse = costOfOneHouse;
        gui.getSpecificField(fieldNumber);
    }


    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
