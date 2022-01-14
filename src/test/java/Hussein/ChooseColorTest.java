package Hussein;

import java.util.Scanner;

public class ChooseColorTest {


    public static void main(String[] args) {

        //a and b resembles a color
        boolean a = false, b = false;

        Scanner scan = new Scanner(System.in);

        boolean again = true;

        while(true) {
            System.out.println("Which color?");

            again = true;

            while (again) {

                again = false;
                String color = scan.nextLine();

                switch (color) {

                    //If player inputs color 'a'
                    case "a":
                        //If a is already true
                        if (a) {
                            System.out.println("a is already taken, write a new color");
                            again = true;
                            break;
                            //If a is not already true, then set it true
                        } else if(a != true){
                            a = true;
                            break;
                        }
                    case "b":
                        if (b) {
                            System.out.println("b is already taken, write a new color");
                            again = true;
                            break;
                        } else if(b != true) {
                            b = true;
                            break;
                        }
                }
            }
        }
    }
}
