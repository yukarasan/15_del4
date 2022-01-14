package GUI_Controllor;

import Fields.*;
import gui_fields.GUI_Field;
import gui_main.GUI;
import java.awt.*;

/**
 * This class is a very important class, since it allows us to create multiples instances of the gui object,
 * without having to actually create more than one object from the GUI class.
 *
 * This choice of design is called the Singleton Pattern.
 */

public final class GUI_Controller {
    private static GUI GUI_instance = null;  //
    private static final GameBoard gameBoard = new GameBoard();

    /**
    Creating a public method so that user can't create more than one instance of this singleton class
    If the GUI_instance equals null, then a new GUI_controller will be created. If the if-statement is false
    then method will simply return the already created object gui_instance
     */

    public static GUI getInstance() {
        if (GUI_instance == null) {
            GUI_Controller gui = new GUI_Controller();
            GUI_instance = gui.GUI_controller();
        }
        return GUI_instance;
    }

    /**
     * The method below is what the gui object will contain. This is simply put, where we decide what our gui
     * should contain.
     * Here we've instantiated the fields and created a custom board that suits the color of the real board.
     */

    private GUI GUI_controller() {
        gameBoard.instantiatingFields();
        Color customColor = new Color(104, 141, 5);  // Creating a custom color for the boardGame
        return new GUI(gameBoard.getField(), customColor);
    }

    /**
     * We've decided to incorporate a game board object that contains all the methods that we've created in
     * game board class.
     */

    public static GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * We've made this method below to make our code shorter when calling methods in the gui. Instead of writing
     * gui.getGameBoard.getSpecificField we can now instead write gui.getSpecificField.
     */

    public static GUI_Field getSpecificField(int specificField) {
        return gameBoard.getSpecificField(specificField);
    }
}