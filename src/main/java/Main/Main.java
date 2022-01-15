package Main;

/**
 * This is the main class. Here is where our program will start running. We've chosen to keep this class very
 * simple and clean.This helps with separation of concerns because the Main class only has one responsibility,
 * which is to run the program.
 *
 * We've instantiated a game object from the Game class, and called its method startGame, which will run
 * other methods that we've created elsewhere.
 */

public class Main {
    /**
     * This method is where our program will start running
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
