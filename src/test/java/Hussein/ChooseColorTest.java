package Hussein;

import java.util.Scanner;

public class ChooseColorTest {


    public static void main(String[] args) {

        boolean a = false, b = false;

        Scanner scan = new Scanner(System.in);



        boolean again = true;

        while(true) {

            System.out.println("Which color?");
            String color = scan.nextLine();

            while (again) {
                again = false;

                switch (color) {

                    case "a":
                        if (a) {
                            System.out.println("a is already taken");
                            again = true;
                            break;
                        } else if(a != true){
                            a = true;
                            break;
                        }
                    case "b":
                        if (b) {
                            System.out.println("b is already taken");
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
