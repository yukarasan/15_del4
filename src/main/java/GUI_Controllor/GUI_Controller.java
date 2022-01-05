package GUI_Controllor;

import Fields.*;
import gui_main.GUI;
import java.awt.*;

public final class GUI_Controller {
    private static GUI GUI_instance = null;
    private static final GameBoard fields = new GameBoard();

    /*
    Creating a private constructor so that user can't create more than one instance of this singleton class
     */

    public GUI getInstance() {
        if (GUI_instance == null) {
            GUI_Controller k = new GUI_Controller();
            GUI_instance = k.GUI_controller();
        }
        return GUI_instance;
    }

    private GUI GUI_controller() {
        fields.instantiatingFields();
        return new GUI(fields.getField(), Color.lightGray);
    }

}