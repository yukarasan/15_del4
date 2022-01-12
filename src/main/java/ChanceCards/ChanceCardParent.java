package ChanceCards;

import Fields.MoveWithADelay;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;

/**
In this class we've made one instance of a gui object from the GUI_Controller class and an instance of a moveWithADelay
object from the MoveWithADelay class. These two objects will be used in all off the chance cards classes.
We achieve this by making the chance cards inherent from this class by using the keyword "extends", and thereby making
this class their parent class. This was done, so we did not have to instantiate these objects in every class.
This way, our code adheres to the information expert as a design pattern and gives our code high cohesion.
 */

public abstract class ChanceCardParent {
    GUI_Controller gui = new GUI_Controller();
    MoveWithADelay moveWithADelay = new MoveWithADelay();

    public abstract void makePayment(GUI_Player gui_player, Player player);

    public abstract void acceptPayment(GUI_Player gui_player, Player player);
}

