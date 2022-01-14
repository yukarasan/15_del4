package Hussein;

import java.util.Scanner;



//This test is about placing three even houses, (it is mostly implemented for 'a')
public class PlaceThreeEvenHouses {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //a, b, and c resemble amounts of houses at orange fields 1, 2 and 3
        int a = 0;
        int b = 0;
        int c = 0;

        while (true) {
            String input = scan.nextLine();

            //It only accepts increasing a house in a, if it is less or equals b and c
            if (input.equals("a") && (a <= b && a <= c) && a<5){
                a += 1;
                System.out.println("counting a: " + a);
            } else if (input.equals("b")) {
                b += 1;
                System.out.println("counting b: " + b);

                //The system will now allow to build on a if it is uneven numbers.
                //It is uneven whenever a is bigger than or equals b+1 or c+1, if that is true
                // then do not place a house at 'a'
            } else if (input.equals("a") && (a >= (b + 1) || a >= (c + 1))){
                System.out.println("There is one two many a's, you have to keep it even, get another one instead");
            } else if (input.equals("c")) {
                c += 1;
                System.out.println("counting c: " + c);
            }
        }
    }
}