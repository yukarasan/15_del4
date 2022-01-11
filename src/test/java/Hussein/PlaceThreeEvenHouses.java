package Hussein;

import java.util.Scanner;

public class PlaceThreeEvenHouses {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int a = 0;
        int b = 0;
        int c = 0;

        while (true) {
            String input = scan.nextLine();
            if (input.equals("a") && (a <= b && a <= c) && a<5){
                a += 1;
                System.out.println("counting a: " + a);
            } else if (input.equals("b")) {
                b += 1;
                System.out.println("counting b: " + b);
            } else if (input.equals("a") && (a >= (b + 1) || a >= (c + 1))){
                System.out.println("There is one two many a's, you have to keep it even, get another one instead");
            } else if (input.equals("c")) {
                c += 1;
                System.out.println("counting c: " + c);
            }
        }
    }
}