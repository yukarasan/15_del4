package Main;

/**
 * This is the Account class, which is used keep track of a players balance. In our game, you could say that
 * this class represents a "bank". The bank has a start value of 30.000 DKK, which can be changed or accessed
 * through the two methods that we've created.
 */

public class Account {
    private int money = 30_000;

    /**
     * The first method takes an int value as its parameter, and then sets the variable money in this class
     * by adding with the parameter value money.
     */

    public void setMoney(int money) {
        this.money += money;    // This code is the same as: this.money = this.money + money
    }

    /**
     * The second method simply returns the value money.
     */

    public int getMoney() {
        return money;
    }
}
