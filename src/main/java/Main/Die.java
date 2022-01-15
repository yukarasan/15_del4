package Main;

/**
 * This class represent a single die that be rolled (raffled) and give a random face value of the die.
 *
 * We start by setting the default face value of the die to be 1. We choose this since it would not make
 * sense to have a default face value of 0 because a die can't show 0.
 *
 * We've created a rollDie method which rolls the die.
 * And another method which only returns the value of a singular die.
 */

public class Die {
    // Creating a variable that will show the face value of the die:
    private int faceValue;

    /**
     * Creating a constructor that sets the face value to be 1
     */
    public Die() {
        faceValue = 1;
    }

    /**
     * Creating a roll method for when the player throws their dice, and thus changing the face value.
     */

    public int rollDie() {
        // Setting the maximum value of a die to be:
        final int MAX = 6;
        return faceValue = (int) (Math.random() * MAX + 1);
    }

    /**
     * Creating a method that will return the face value of the dice.
     */

    public int getFaceValue() {
        return faceValue;
    }
}

