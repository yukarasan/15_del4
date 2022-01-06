package Main;

public class Player {
    private Account account = new Account();
    private String name;
    private int square = 0;

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

    public int moveSquare(int die1,int die2) {
        this.square += die1+die2;

        square %= 40;
        return square;
    }
}