package Fields;

import GUI_Controllor.GUI_Controller;

/**
 * This class works as a parent class to all the other classes in this package. That way we won't have to
 * instantiate the gui object multiple times.
 * This in return makes the program more efficient, and prevents unnecessary memory usage.
 */

public class Field {
    GUI_Controller gui = new GUI_Controller();
}
