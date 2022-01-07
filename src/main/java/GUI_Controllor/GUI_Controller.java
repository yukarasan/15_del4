package GUI_Controllor;

import Fields.*;
import gui_fields.GUI_Field;
import gui_main.GUI;
import java.awt.*;

public final class GUI_Controller {
    private static GUI GUI_instance = null;
    private static final GameBoard gameBoard = new GameBoard();

    /*
    Creating a private constructor so that user can't create more than one instance of this singleton class
     */

    private GUI GUI_controller() {
        gameBoard.instantiatingFields();
        Color customColor = new Color(113, 157, 1);  // Creating a custom color for the boardGame
        return new GUI(gameBoard.getField(), customColor);
    }

    public GUI getInstance() {
        if (GUI_instance == null) {
            GUI_Controller k = new GUI_Controller();
            GUI_instance = k.GUI_controller();
        }
        return GUI_instance;
    }

    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public GUI_Field getSpecificField(int specificField) {
        return gameBoard.getSpecificField(specificField);
    }
}