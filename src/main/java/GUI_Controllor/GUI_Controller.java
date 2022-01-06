package GUI_Controllor;

import Fields.*;
import gui_fields.GUI_Field;
import gui_main.GUI;
import java.awt.*;

public final class GUI_Controller {
    private static GUI GUI_instance = null;
    private static final GameBoard fields = new GameBoard();

    /*
    Creating a private constructor so that user can't create more than one instance of this singleton class
     */

    private GUI GUI_controller() {
        fields.instantiatingFields();
        Color customColor = new Color(91,112,17);  // Creating a custom color for the boardGame
        return new GUI(fields.getField(), customColor);
    }

    public GUI getInstance() {
        if (GUI_instance == null) {
            GUI_Controller k = new GUI_Controller();
            GUI_instance = k.GUI_controller();
        }
        return GUI_instance;
    }



    public GameBoard getJustFields(){
        return fields;
    }

    public GUI_Field getSpecificField(int specificField) {
        return fields.getSpecificField(specificField);
    }
}