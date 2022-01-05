package GUI_Controllor;
import Fields.*;

import gui_main.GUI;

import java.awt.*;

public final class GUI_Controller {
    private static GUI board;
    private static GUI GUI_instance = null;
    private static GameBoard fields = new GameBoard();

    /*
    Creating a private constructor so that user cant create an instance of the default constructor
    the class
     */

    private GUI GUI_controller() {
        fields.instantializeFields();
        board = new GUI(fields.getField(), Color.lightGray);
        return board;
    }

    public GUI getInstance() {
        if (GUI_instance == null) {
            GUI_Controller k = new GUI_Controller();
            GUI_instance = k.GUI_controller();
        }
        return GUI_instance;
    }
}