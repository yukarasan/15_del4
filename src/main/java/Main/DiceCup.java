package Main;

/**
 * This class represent a Dice Cup. It will contain two objects which will be instantiated from the Die class.
 * When instantiating these object, we will have the option to call the methods that are contained within the Die
 * class by using the two methods that returns each of the die objects.
 */

public class DiceCup {
    // Creating two dice objects:
    private final Die die1 = new Die();
    private final Die die2 = new Die();

    // Creating two methods that returns each die object:
    public Die getDie1() {
        return die1;
    }

    public Die getDie2() {
        return die2;
    }
}
