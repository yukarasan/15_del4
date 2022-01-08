package Yusuf;

import Main.Player;
import java.util.concurrent.TimeUnit;

public class MoveHereWithDelay {

    public void movePlayerWithDelayToSpecificField(Player player, int move) {


        while (player.getSquare() != move) {


            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.moveSquare(1,0);

            // Moves the player to the square dependent on the value of move
            System.out.println("Field: " + player.getSquare());
        }
    }

    public static void main(String[] args) {
        Player player = new Player();

        MoveHereWithDelay moveHereWithDelay = new MoveHereWithDelay();

        player.moveSquare(25,0);
        moveHereWithDelay.movePlayerWithDelayToSpecificField(player, 11);
    }

}
