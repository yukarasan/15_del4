package Fields;

import ChanceCards.ChanceCard;
import Fields.Properties.BlueProperties.HvidovreVej;
import Fields.Properties.Property;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import java.awt.*;

public class GameBoard {
    private final GUI_Field[] fields = new GUI_Field[40];
    private final Jail jail = new Jail();
    private final ChanceCard chanceCard = new ChanceCard();
    private final Ferry[] ferries = new Ferry[4];
    private final Brewer[] brewers = new Brewer[2];
    private int intHelper;
    private int intHelper1;
    private Jackpot jackpot = new Jackpot();
    private Property[] properties = new Property[20];

    public void instantiatingFields(){

        for(int i=0; i<fields.length; i++){
            fields[i] = new GUI_Street();
        }
        allField();
    }

    public void instantiatingFerries() {
        for (int i = 0; i < ferries.length; i++) {
            ferries[i] = new Ferry();
        }
    }

    public void createProperties(){
        properties[0] = new HvidovreVej(50, 100, 250, 750,
                2250, 4000, 6000, 1200, 1000, 1000);
    }



    public void allField() {
        UnownableField unownableField = new UnownableField();
        Jackpot jackpot = new Jackpot();
        unownableField.startField(fields);
        unownableField.chanceField(fields);
        unownableField.jailField(fields);
        unownableField.freeParking(fields);
        jackpot.jackpotField(fields);
        jail.jailFields(fields);
        createOwnableField();
    }

    public GUI_Field[] getField() {
        return fields;
    }

    public GUI_Field getSpecificField(int specificField){
        return fields[specificField];
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
        ownableField.setBrewerFields(fields,12,Color.red,"Squash", "Tuborg");
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
        ownableField.setBrewerFields(fields,28,Color.red,"Coca Cola", " ");
        ownableField.setFields(fields,29, 5600,Color.white,"Østergade");
        ownableField.setFields(fields,31, 6000,Color.yellow,"Amagertorv");
        ownableField.setFields(fields,32, 6000,Color.yellow,"Vimmelskaffet");
        ownableField.setFields(fields,34, 6400,Color.yellow,"Nygade");
        ownableField.setFerryFields(fields,35,Color.blue,"Scandlines", "Rødby-Puttgarden");
        ownableField.setFields(fields,37, 7000,Color.magenta,"Frederiksberg gade");
        ownableField.setFields(fields,39, 8000,Color.magenta,"Rådhuspladsen");

    }

    public Jail getJail() {
        return jail;
    }

    public ChanceCard getChanceCard(){
        return chanceCard;
    }

    public Ferry getFerry(Player player){
        checkIfLandedFerryField(player);
        return ferries[intHelper];
    }

    public void checkIfLandedFerryField(Player player){

        switch (player.getSquare()) {
            case 5 -> intHelper = 0;
            case 15 -> intHelper = 1;
            case 25 -> intHelper = 2;
            case 35 -> intHelper = 3;
        }
    }

    public Ferry[] getFerries() {
        return ferries;
    }

    public void initializeBrewers(){
        for (int i = 0; i < brewers.length; i++) {
            brewers[i] = new Brewer();
        }
    }

    public void checkIfLandedBrewerField(Player player){

        switch (player.getSquare()) {
            case 12 -> intHelper1 = 0;
            case 28 -> intHelper1 = 1;
        }
    }

    public Brewer getBrewer(Player player){
        checkIfLandedBrewerField(player);
        return brewers[intHelper1];
    }

    public Jackpot getJackpot() {
        return jackpot;
    }

    //NOTE: This method below is not used in the method in the class Brewer, buyBrewerField
  //  public Brewer[] getBrewers() {
  //      return brewers;
  // }
}
