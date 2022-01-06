package Hussein;

import java.util.Scanner;

public class TestingSimplerColor {
    private static boolean[] numberOfOption = new boolean[2];
    private static boolean chooseColorAgain;


    public void colorChooser(boolean[] colorChosen,int numberOfOption, boolean chooseColorAgain, String colorString) {

        if (colorChosen[numberOfOption]) {
            System.out.println(colorString + " is already taken");
            chooseColorAgain = true;
        } else {
            colorChosen[numberOfOption] = true;
            chooseColorAgain = false;
        }
    }

    public static void setNumberOfOption(boolean[] numberOfOption) {
        TestingSimplerColor.numberOfOption = numberOfOption;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        TestingSimplerColor testingSimplerColor = new TestingSimplerColor();



        while (true) {

            chooseColorAgain = true;

            while (chooseColorAgain) {
                chooseColorAgain = false;

                System.out.print("Write in your color: ");
                String inputColor = scan.nextLine();

                switch (inputColor) {
                    case "a":
                        testingSimplerColor.colorChooser(numberOfOption, 0, chooseColorAgain, inputColor);
                        break;
                    case "b":
                        testingSimplerColor.colorChooser(numberOfOption, 1, chooseColorAgain, inputColor);
                        break;
                }
            }
        }
    }
}
