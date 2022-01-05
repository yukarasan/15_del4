public class Die {

    // Creating a variable that will show the face value of the die:
    private int faceValue;

    // Creating a constructor that sets the face value to be 1:
    public Die() {
        faceValue = 1;
    }

    // Creating a roll method for when the player throws their dice, and thus changing the face value.
    public int rollDice() {
        // Setting the maximum value of a die to be:
        final int MAX = 6;
        return faceValue = (int) (Math.random() * MAX + 1);
    }
}

