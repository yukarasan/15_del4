package Main;

public class Player {
    private Account account = new Account();
    private String name;
    private int square = 0;
    private boolean passedStartField;


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
}