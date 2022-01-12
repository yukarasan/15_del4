package Main;

/**
 * This is the Player class. In here we have instantiated an object from the Account class which will be used
 * to acces the methods inside the account class when we want to change a players account.
 */

public class Player {
    private final Account account = new Account();
    private String name;
    private int square = 0;
    private boolean passedStartField, inJail, waitATurn, ownsAPropertySet, outOfGame;
    private int ferriesOwned, brewersOwned;
    private int blueOwned, orangeOwned, darkYellowOwned, greyOwned, redOwned, whiteOwned,
            brightYellowOwned, purpleOwned;
    private int turnNumberInJail;

    /**
     * Creating a method setName that takes a name as a parameter and sets this.name (the name in this class) equal to
     * what has been written as a parameter.
     *
     * The method getName below it, simply returns this name.
     */

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Creating a method setMoney that takes an integer value representing an amount of money as its parameter.
     * Using the account object we can acces its method as well and add or subtract the money in there.
     *
     * The method getAccount below it simply returns the account object.
     */

    public void setMoney(int money) {
        account.setMoney(money);
    }

    public Account getAccount() {
        return account;
    }

    /**
     * The method moveToHere takes an integer as its parameter and sets the value of square equal to this.
     * This method is useful in scenarios where we want to move a player to a specific field.
     *
     * The method getSquare below simply returns value of square.
     */

    public void moveToHere(int currentInt) {
        square = currentInt;
    }

    public int getSquare() {
        return square;
    }

    /**
     * Creating a method moveToSquare that takes an integer value representing the value of two dice.
     * The main use of this method is present in testing. This method has been created because we wanted to
     * check whether a player has passed the start field every time he or she moves past the start field.
     */

    public void moveToSquare(int die1, int die2) {
        this.square += die1 + die2;

        if(square > 39) {
            passedStartField = true;
        }
        square %= 40;
    }

    /**
     * Similarly the method moveOneSquare below also checks for whether a player has passed the start field.
     * We've made this method because we wanted to move a player by one square for every field they have to move
     * past by, after throwing their dice. This way we can achieve movement in the game that represents real life.
     *
     * The method has been implemented in the MoveWithADelay class.
     */

    public void moveOneSquare(int die) {
        this.square += die;

        if(square > 39) {
            passedStartField = true;
        }

        square %= 40;
    }

    /**
     * The method resetPassedStartField set the boolean value of passedStartField to be false.
     *
     * The method getPassedStartField simply returns its boolean value
     */

    public void resetPassedStartField() {
        passedStartField = false;
    }


    public boolean getPassedStartField() {
        return passedStartField;
    }

    /**
     * The method setInJail sets the boolean value of inJail to be equal to trueOrFalse
     *
     * The method getInJail simply returns this boolean value.
     */

    public void setInJail(boolean trueOrFalse) {
        inJail = trueOrFalse;
    }

    public boolean getInJail() {
        return inJail;
    }

    /**
     * The method setWaitATurn is used if a player has to wait a turn, by passing either a true or false statement
     * as its parameters.
     *
     * The method getWaitATurn below simply returns this value.
     */

    public void setWaitATurn(boolean trueOrFalse) {
        waitATurn = trueOrFalse;
    }

    /**
     * ATTENTIVE:
     *
     * Now that we've explained a lot of the setters and getters, we believe that the rest of the methods are
     * self-explanatory and therefore need no further explaining, since the names of the methods also describe
     * what they do as well.
     */

    public boolean getWaitATurn() {
        return waitATurn;
    }

    public int getFerriesOwned() {
        return ferriesOwned;
    }

    public void setFerriesOwned() {
        this.ferriesOwned += 1;
    }

    public void setBrewersOwned() {
        this.brewersOwned += 1;
    }

    public int getBrewersOwned() {
        return brewersOwned;
    }

    public void setBlueOwned() {
        this.blueOwned += 1;
    }

    public int getBlueOwned() {
        return blueOwned;
    }

    public int getOrangeOwned() {
        return orangeOwned;
    }

    public void setOrangeOwned() {
        this.orangeOwned += 1;
    }

    public void setDarkYellowOwned() {
        this.darkYellowOwned += 1;
    }

    public int getDarkYellowOwned() {
        return darkYellowOwned;
    }

    public void setGreyOwned() {
        this.greyOwned += 1;
    }

    public int getGreyOwned() {
        return greyOwned;
    }

    public void setRedOwned() {
        this.redOwned += 1;
    }

    public int getRedOwned() {
        return redOwned;
    }

    public void setWhiteOwned() {
        this.whiteOwned += 1;
    }

    public int getWhiteOwned() {
        return whiteOwned;
    }

    public void setBrightYellowOwned() {
        this.brightYellowOwned += 1;
    }

    public int getBrightYellowOwned() {
        return brightYellowOwned;
    }

    public void setPurpleOwned() {
        this.purpleOwned += 1;
    }

    public int getPurpleOwned() {
        return purpleOwned;
    }

    public void setOwnsAPropertySet(boolean ownsAPropertySet) {
        this.ownsAPropertySet = ownsAPropertySet;
    }

    public boolean getOwnsAPropertySet() {
        return ownsAPropertySet;
    }

    public void setPlayerOutOfGame(boolean outOfGame) {
        this.outOfGame = outOfGame;
    }

    public boolean getPlayerOutOfGame() {
        return outOfGame;
    }

    public void setTurnNumberInJail() {
        this.turnNumberInJail += 1;
    }

    public int getTurnNumberInJail() {
        return turnNumberInJail;
    }

    public void resetNumberInJail(){
        turnNumberInJail = 0;
    }
}