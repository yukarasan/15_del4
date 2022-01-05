import gui_fields.GUI_Car;
import gui_main.GUI;

import java.awt.*;

public class Car {
    Car(Color color){
        GUI_Car car=new GUI_Car();
        car.setPrimaryColor(color);
    }

    public static void main(String[] args) {
        Car car= new Car(Color.blue);
    }
}
