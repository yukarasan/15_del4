public class Dicecup extends Die{
    public void throwDice(){
        this.die1 = (int) (Math.random()*6);
        this.die2 = (int) (Math.random()*6);
    }
}
