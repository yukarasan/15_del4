package Hussein;

import java.util.Scanner;

public class TestingSimplerColor {
    private static boolean[] numberOfOption = new boolean[2];
    private static boolean chooseColorAgain;
    private static String[] names;


    public void colorChooser(boolean[] colorChosen,int numberOfOption, String colorString, int playerNumber) {

        if (colorChosen[numberOfOption]) {
            System.out.println(colorString + " is already taken");
            chooseColorAgain = true;
        } else {
            colorChosen[numberOfOption] = true;
            chooseColorAgain = false;
            names[playerNumber] = colorString;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        TestingSimplerColor testingSimplerColor = new TestingSimplerColor();

        names = new String[2];

        for(int i = 0; i<2; i++) {

            chooseColorAgain = true;

            while (chooseColorAgain) {
                chooseColorAgain = false;

                System.out.print("Write in your color: ");
                String inputColor = scan.nextLine();

                switch (inputColor) {
                    case "a":
                        testingSimplerColor.colorChooser(numberOfOption, 0, inputColor, i);
                        break;
                    case "b":
                        testingSimplerColor.colorChooser(numberOfOption, 1, inputColor, i);
                        break;
                }
            }
        }
        System.out.println("Names: ");
        for(int i=0; i<2; i++){
            System.out.print(names[i] + ", ");
        }
    }
}
