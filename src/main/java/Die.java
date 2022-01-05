public class Die {
    private int faceValue;


    public Die(){
        faceValue=1;
    }

    public int rollDice(){
        final int MAX= 6;
        return faceValue= (int) (Math.random()*MAX+1);
    }



    }

