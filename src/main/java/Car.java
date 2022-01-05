import gui_fields.GUI_Car;
import java.awt.*;

public class Car {

    // Creating a constructor for the car class where the a color can be set
    Car(Color color){
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(color);
    }
}
