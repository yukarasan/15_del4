package Fields;

import GUI_Controllor.GUI_Controller;
import gui_fields.*;
import java.awt.*;

/**
 * In this class we made different methods for each field that the player is able to own.
 * This was done for our own sake, to have a better overview of our project.
 *
 * The methods themselves have been used in the gameBoard class to create each field.
 */

public class OwnableField {
    /*
    Below is the rent on various occasions of ownable fields such as properties.
    Also, below is cost of eventual upgrades and the rent if a set of one color are owned
     */

    private int rentNoHouse, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel;

    /**
     * Here we create a method that will allow us to create property fields. These will be shown on the GUI
     * by setting them in the GameBoard class.
     */

    public void setFields(GUI_Field[] fields, int fieldNumber, int price, Color color, String name, String description) {
        GUI_Street field = new GUI_Street();
        field.setDescription(" ");
        field.setRent(String.valueOf(price));
        field.setSubText("Pris: " + price);
        field.setBackGroundColor(color);
        field.setTitle(name);
        field.setDescription(description);
        fields[fieldNumber] = field;
    }

    /**
     * Here we create a method that will allow us to create ferry fields. These will be shown on the GUI
     * by setting them in the GameBoard class.
     */

    public void setFerryFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName, String description) {
        field[fieldNumber] = new GUI_Shipping();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setDescription(description);
        field[fieldNumber].setSubText("");
    }

    /**
     * Here we create a method that will allow us to create brewer fields. These will be shown on the GUI
     * by setting them in the GameBoard class.
     */

    public void setBrewerFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName, String description) {
        field[fieldNumber] = new GUI_Brewery();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(description);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setSubText("");
    }
}
