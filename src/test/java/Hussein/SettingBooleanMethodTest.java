package Hussein;

public class SettingBooleanMethodTest {
    private boolean waitInJail;

    public void setInJail(boolean trueOrFalse){
        waitInJail = trueOrFalse;
    }

    public boolean getInJail(){
        return waitInJail;
    }

    public static void main(String[] args) {
        SettingBooleanMethodTest settingBooleanMethodTest = new SettingBooleanMethodTest();

        //Making the boolean false
        settingBooleanMethodTest.setInJail(false);
        System.out.println("This should be false: " + settingBooleanMethodTest.getInJail());

        //Making the boolean true
        settingBooleanMethodTest.setInJail(true);
        System.out.println("This should be true: " + settingBooleanMethodTest.getInJail());
    }
}