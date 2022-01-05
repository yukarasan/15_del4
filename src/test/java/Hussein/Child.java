package Hussein;

public class Child extends Parent{

    public void setNumber(){
        numbers[3] = 2;
    }

    public int[] getNumber(){
        return numbers;
    }



    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        child.setNumber();



    }
}
