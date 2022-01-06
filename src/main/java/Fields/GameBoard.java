package Fields;

import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameBoard {
    private final GUI_Field[] fields = new GUI_Field[40];

    public void instantiatingFields(){

        for(int i=0; i<fields.length; i++){
            fields[i] = new GUI_Street();
        }
        allField();
    }

    public void allField() {
        UnownableField unownableField = new UnownableField();
        Jackpot jackpot = new Jackpot();
        unownableField.startField(fields);
        unownableField.chanceField(fields);
        unownableField.jailField(fields);
        unownableField.freeParking(fields);
        jackpot.jackPot(fields);
        Jail jail = new Jail();
        jail.jailFields(fields);
        createOwnableField();
    }

    public GUI_Field[] getField() {
        return fields;
    }

    public GUI_Field getSpecificField(int specificField){
        return fields[specificField];
    }

    public GUI_Street getSpecificStreetField(int specificField){
        return (GUI_Street) fields[specificField];
    }

    private void createOwnableField() {
        OwnableField ownableField = new OwnableField();
        ownableField.setFields(fields,1,1200, Color.blue, "Rødovrevej");
        ownableField.setFields(fields,3, 1200,Color.blue,"Hvidovrevej");
        ownableField.setFerryFields(fields, 5, Color.cyan, "Scandlines", "Helsingør-Helsinborg");
        ownableField.setFields(fields,6, 2000,Color.orange,"Roskildevej");
        ownableField.setFields(fields,8, 2000,Color.orange,"Valby Langgade");
        ownableField.setFields(fields,9, 2400,Color.orange,"Allegade");
        ownableField.setFields(fields,11, 2800,Color.yellow,"Frederiksberg Alle");
        ownableField.setFields(fields,13, 2800,Color.yellow,"Bulowsvej");
        ownableField.setFields(fields,14,3200,Color.yellow,"Gl. Kongevej");
        ownableField.setFerryFields(fields, 15, Color.RED, "Mols-linien", "En genvej i Danmark");
        ownableField.setFields(fields,16,3600,Color.gray,"Bernstorffsvej");
        ownableField.setFields(fields,18,3600,Color.gray,"Hellerupvej");
        ownableField.setFields(fields,19,4000,Color.gray,"Strandvejen");
        ownableField.setFields(fields,21,4400,Color.RED,"Trianglen");
        ownableField.setFields(fields,23,4400,Color.RED,"Østerbrogade");
        ownableField.setFields(fields,24,4800,Color.RED,"Grønningen");
        ownableField.setFerryFields(fields,25,Color.blue,"Scandlines", "Gedser-Rostock");
        ownableField.setFields(fields,26, 5200,Color.white,"Bredgade");
        ownableField.setFields(fields,27, 5200,Color.white,"Kgs.Nytorv");
        ownableField.setFields(fields,29, 5600,Color.white,"Østergade");
        ownableField.setFields(fields,31, 6000,Color.yellow,"Amagertorv");
        ownableField.setFields(fields,32, 6000,Color.yellow,"Vimmelskaffet");
        ownableField.setFields(fields,34, 6400,Color.yellow,"Nygade");
        ownableField.setFerryFields(fields,35,Color.blue,"Scandlines", "Rødby-Puttgarden");
        ownableField.setFields(fields,37, 7000,Color.magenta,"Frederiksberg gade");
        ownableField.setFields(fields,39, 8000,Color.magenta,"Rådhuspladsen");
    }

    public int[] allOwnableFields(){
        int[] allOwnableFields = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
        return allOwnableFields;
    }

    public void buyField(Player player, GUI_Player gui_player){



        player.getAccount();

    }

    public static void main(String[] args) {
        //getSpecificStreetField(player.getSquare()).getRent();
    }

}
