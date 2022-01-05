import gui_fields.GUI_Car;
import gui_main.GUI;

import java.awt.*;

public class Car {

    // Creating a constructor for the car class where the color can be set
    Car(Color color){
        GUI_Car car = new GUI_Car();  // Creating an instance of the GUI_Car class
        car.setPrimaryColor(color);   // Setting the color
    }

    public static void main(String[] args) {
        Car car=new Car();
        GUI gui= new GUI();


    }
}
