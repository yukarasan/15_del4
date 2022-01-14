package Fields;

import gui_fields.*;

/**
 * In this class we made different methods for each field that the player is not able to own.
 * This was done for our own sake, to have a better overview of our project.
 *
 * The methods themselves have been used in the gameBoard class to create each field.
 */

public class UnownableField {

    /**
     * Creates the start field
     * @param fields
     */

    public void startField(GUI_Field[] fields) {
        fields[0] = new GUI_Start();
        fields[0].setTitle("Start");
        fields[0].setSubText("Modtag 4000");
        fields[0].setDescription("Dette er startfeltet");
    }

    /**
     * Creates the jail fields
     * @param fields
     */

    public void jailField(GUI_Field[] fields) {
        fields[10] = new GUI_Jail();
        fields[30] = new GUI_Jail();
    }

    /**
     * Creates the free parking field
     * @param fields
     */

    public void freeParking(GUI_Field[] fields) {
        fields[20] = new GUI_Refuge();
        fields[20].setTitle("Parkering");
        fields[20].setSubText(" Gratis parkering ");
        fields[20].setDescription(" Du kan parkere gratis her");
    }

    /**
     * Creates the chance card fields
     * @param fields
     */

    public void chanceField(GUI_Field[] fields) {
        fields[2] = new GUI_Chance();
        fields[7] = new GUI_Chance();
        fields[17] = new GUI_Chance();
        fields[22] = new GUI_Chance();
        fields[33] = new GUI_Chance();
        fields[36] = new GUI_Chance();
    }
}

