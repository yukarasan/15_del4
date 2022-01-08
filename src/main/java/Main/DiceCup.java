package Main;

/**
 * This class represent a Dice Cup witch will contain two objects which will be instantiated from the Die class.
 * We've created two methods
 */

public class DiceCup {
    // Creating two dice objects:
    private final Die die1 = new Die();
    private final Die die2 = new Die();

    public Die getDie1(){
        return die1;
    }
    public Die getDie2(){
        return die2;
    }

}
