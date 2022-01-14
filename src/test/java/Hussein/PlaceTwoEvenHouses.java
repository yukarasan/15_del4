package Hussein;

import java.util.Scanner;

public class PlaceTwoEvenHouses {


    //This test is described in PlaceThreeEvenHouses, it works the same, just with two house amounts
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = 0;
        int b = 0;

        while (true) {
            String input = scan.nextLine();
            if (input.equals("a") && a <= b) {
                a += 1;
                System.out.println("counting a: " + a);
            } else if (input.equals("b")) {
                b += 1;
                System.out.println("counting b: " + b);
            } else if (input.equals("a") && a >= (b + 1)) {
                System.out.println("There is one two many a's, you have to keep it even, get a b instead");
            }
        }
    }
}
