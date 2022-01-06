package Hussein;

import Main.Player;

public class PassingStartFieldTest {

    public static void main(String[] args) {
        Player player = new Player();

        System.out.println("Has passed start: " + player.getPassedStartField());

        player.moveSquare(51,0);

        System.out.println("Has passed start: " + player.getPassedStartField());

        //Resetting the boolean
        player.resetPassedStartField();

        System.out.println("Has passed start: " + player.getPassedStartField());
    }
}
