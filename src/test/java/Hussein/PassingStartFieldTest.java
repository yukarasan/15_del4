package Hussein;

import Main.Player;

public class PassingStartFieldTest {

    public static void main(String[] args) {
        Player player = new Player();

        System.out.println("Has passed start: " + player.getPassedStartField());

        player.moveToSquare(51,0);

        System.out.println("Has passed start: " + player.getPassedStartField());

        //Resetting the boolean after passing start
        player.resetPassedStartField();

        System.out.println("Has passed start: " + player.getPassedStartField());
    }
}
