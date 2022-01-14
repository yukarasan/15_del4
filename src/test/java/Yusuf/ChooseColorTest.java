package Yusuf;

import java.util.Objects;
import java.util.Scanner;

public class ChooseColorTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean a = false, b = false;
        boolean aAgain = false, bAgain = false;

        while(true) {

            System.out.println("Which color?");
            String color = scanner.nextLine();

            switch (color) {

                case "a":
                    if (a) {
                        System.out.println("a is already taken");
                        aAgain = true;
                        break;
                    }
                case "b":
                    if (b) {
                        System.out.println("b is already taken");
                        bAgain = true;
                        break;
                    }
            }

            if (Objects.equals(color, "a")) {
                a = true;
            } else if (Objects.equals(color, "b")) {
                b = true;
            }

            if (aAgain) {
                break;
            } else if (bAgain) {
                break;
            }
        }
    }
}
