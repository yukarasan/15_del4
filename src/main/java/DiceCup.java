public class DiceCup {
    Die die1=new Die();
    Die die2=new Die();

    public static void main(String[] args) {
        DiceCup diceCup=new DiceCup();
        System.out.println(diceCup.die1.rollDice());
    }

}
