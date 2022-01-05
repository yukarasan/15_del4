import gui_main.GUI;

public class GUI_Controllor {

    private static final GUI gui = new GUI();

    /*
    Creating a private constructor so that user cant create an instance of the default constructor
    the class
     */

    private GUI_Controllor() {

    }

    public static GUI getInstance(){
        return gui;
    }

}
