package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

public class GameBoard {
    private GUI_Field[] starters = new GUI_Field[40];

    /*public void placeBoard() {

     */

    public void instantializeFields(){

        for(int i=0; i<starters.length; i++){
            starters[i] = new GUI_Street();
        }
        allField();
    }

    public void allField() {
        UnownableField unownableField = new UnownableField();

        unownableField.startField(starters);
        unownableField.chanceField(starters);
        unownableField.jailField(starters);
        unownableField.freeParking(starters);

        createOwnableField();
    }


    public GUI_Field[] getField() {
        return starters;
    }

    private void createOwnableField() {
        OwnableField ownableField = new OwnableField();
        ownableField.setFields(starters,1,10, Color.black);
    }



}
