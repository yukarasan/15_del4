package Fields;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.*;

import java.awt.*;
import java.util.stream.IntStream;

public class OwnableField extends Field{
    protected Player owner;

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

    public void setFerryFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName) {
        field[fieldNumber] = new GUI_Shipping();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setSubText("");
    }

    public void setBrewerFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName) {
        field[fieldNumber] = new GUI_Brewery();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setSubText("");
    }

    public void buyFerry(Player player, GUI_Player gui_player) {
        int[] ferryFields = {5, 15, 25, 35};
        int s = player.getSquare();

        if (IntStream.of(ferryFields).anyMatch(x -> x == s)){
            gui.getInstance().getUserButtonPressed("");
        }
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
