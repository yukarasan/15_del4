package Main;

public class Player {
    private Account account = new Account();
    private String name;
    private int square = 0;
    private boolean passedStartField;
    private boolean inJail;
    private boolean waitATurn;

    public void setName(String name){
        this.name = name;
    }

    public Account getAccount(){
        return account;
    }

    public String getName(){
        return name;
    }

    public int getSquare(){
        return square;
    }

    public void moveToHere(int currentInt){
        square = currentInt;
    }

    public void moveSquare(int die1,int die2) {
        this.square += die1+die2;

        if(square > 39){
            passedStartField = true;
        }
        square %= 40;
    }

    public boolean getPassedStartField(){
        return passedStartField;
    }

    public void resetPassedStartField(){
        passedStartField = false;
    }
    public void setMoney(int money) {
        account.setMoney(money);
    }

    public void setInJail(boolean trueOrFalse) {
        inJail = trueOrFalse;
    }

    public boolean getInJail(){
        return inJail;
    }

    public void setWaitATurn(boolean trueOrFalse){
        waitATurn = trueOrFalse;
    }

    public boolean getWaitATurn(){
        return waitATurn;
    }


}