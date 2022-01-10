package Fields;

import ChanceCards.ChanceCard;
import Fields.Properties.PropertyField;
import Fields.Properties.Property;
import Main.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import javax.swing.*;
import java.awt.*;

public class GameBoard {
    private final GUI_Field[] fields = new GUI_Field[40];
    private final Jail jail = new Jail();
    private final ChanceCard chanceCard = new ChanceCard();
    private final Ferry[] ferries = new Ferry[4];
    private final Brewer[] brewers = new Brewer[2];
    private int intHelper, intHelper1, intHelper2;
    private Jackpot jackpot = new Jackpot();
    private Property[] properties = new Property[22];

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

    public GUI_Street getGuiStreet(int fieldNumber){
        return (GUI_Street) fields[fieldNumber];
    }

    public void createPropertiesPrices(){

        //Blue fields
        properties[0] = new PropertyField(50, 100, 250, 750,
                2250, 4000, 6000, 1200, 1000, 1000);

        properties[1] = new PropertyField(50, 100, 250, 750,
                2250, 4000, 6000, 1200, 1000, 1000);

        //Orange fields
        properties[2] = new PropertyField(100, 250, 600, 1800,
                5400, 8000, 11000, 2000, 1000, 1000);

        properties[3] = new PropertyField(100, 350, 600, 1800,
                5400, 8000, 11000, 2000, 1000, 1000);

        properties[4] = new PropertyField(150, 400, 800, 2000,
                6000, 9000, 12000, 2400, 1000, 1000);

        //Dark yellow fields
        properties[5] = new PropertyField(200, 400, 1000, 3000,
                9000, 12500, 15000, 2800, 2000, 2000);

        properties[6] = new PropertyField(200, 400, 1000, 3000,
                9000, 12500, 15000, 2800, 2000, 2000);

        properties[7] = new PropertyField(250, 450, 1250, 3750,
                10000, 14000, 18000, 3400, 2000, 2000);

        //Grey fields
        properties[8] = new PropertyField(300, 600, 1400, 4000,
                11000, 15000, 19000, 3600, 2000, 2000);

        properties[9] = new PropertyField(300, 600, 1400, 4000,
                11000, 15000, 19000, 3600, 2000, 2000);

        properties[10] = new PropertyField(350, 700, 1600, 4400,
                12000, 16000, 20000, 4000, 2000, 2000);

        //Red fields
        properties[11] = new PropertyField(350, 750, 1800, 5000,
                14000, 17500, 21000, 4400, 3000, 3000);

        properties[12] = new PropertyField(350, 750, 1800, 5000,
                14000, 17500, 21000, 4400, 3000, 3000);

        properties[13] = new PropertyField(400, 850, 2000, 6000,
                15000, 18500, 22000, 4800, 3000, 3000);


        //White fields
        properties[14] = new PropertyField(450, 1000, 2200, 6600,
                16000, 19500, 23000, 5200, 3000, 3000);

        properties[15] = new PropertyField(450, 1000, 2200, 6600,
                16000, 19500, 23000, 5200, 3000, 3000);

        properties[16] = new PropertyField(500, 1100, 2400, 7200,
                17000, 20500, 24000, 5600, 3000, 3000);

        //Bright yellow fields
        properties[17] = new PropertyField(550, 1150,2600, 7800, 18000,
                22000, 25000, 6000, 4000, 4000);

        properties[18] = new PropertyField(550, 1150,2600, 7800, 18000,
                22000, 25000, 6000, 4000, 4000);

        properties[19] = new PropertyField(600, 1250,3000, 9000, 20000,
                24000, 28000, 6400, 4000, 4000);

        //Purple fields
        properties[20] = new PropertyField(700, 2250, 3500, 10000,
                22000, 26000, 30000, 7000, 3500, 3500);

        properties[21] = new PropertyField(1000, 2500, 4000, 12000,
                28000, 34000, 40000, 8000, 4000, 4000);
    }

    public Property getProperty(Player player){
        checkIfLandedPropertyField(player);

        return properties[intHelper2];
    }

    public Property[] getProperties() {
        return properties;
    }

    public void checkIfLandedPropertyField(Player player){

        switch (player.getSquare()) {
            case 1 -> intHelper2 = 0;
            case 3 -> intHelper2 = 1;
            case 6 -> intHelper2 = 2;
            case 8 -> intHelper2 = 3;
            case 9 -> intHelper2 = 4;
            case 11 -> intHelper2 = 5;
            case 13 -> intHelper2 = 6;
            case 14 -> intHelper2 = 7;
            case 16 -> intHelper2 = 8;
            case 18 -> intHelper2 = 9;
            case 19 -> intHelper2 = 10;
            case 21 -> intHelper2 = 11;
            case 23 -> intHelper2 = 12;
            case 24 -> intHelper2 = 13;
            case 26 -> intHelper2 = 14;
            case 27 -> intHelper2 = 15;
            case 29 -> intHelper2 = 16;
            case 31 -> intHelper2 = 17;
            case 32 -> intHelper2 = 18;
            case 34 -> intHelper2 = 19;
            case 37 -> intHelper2 = 20;
            case 39 -> intHelper2 = 21;
        }
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
        ownableField.setFields(fields,1,1200, Color.blue, "Rødovrevej", "Navn:Rødovrevej,pris:1200 DKK, pris per hus: 1.000 DKK,Pris per hotel: 1.000 DKK + 4 huse,leje af grund: " +
                "50 DKK,Med 1 hus:250 DKK,med 2 huse 750 DKK, med 3 huse:2.250 DKK, med 4 huse: 4.000 DKK, med 1 hotel: 6.000 DKK,Pantsætningsværdi: 600 DKK");
        ownableField.setFields(fields,3, 1200,Color.blue,"Hvidovrevej","Navn:Hvidorevej, pris: 1200 DKK, pris per hus: 1.000 DKK,Pris per hotel: 1.000 DKK + 4 huse,leje af grund: 50 DKK,Med 1 hus:250 DKK,med 2 huse 750 DKK, med 3 huse:2.250 DKK, med 4 huse: 4.000 DKK, med 1 hotel: 6.000 DKK, Pantsætning: 600 DKK");
        ownableField.setFerryFields(fields, 5, Color.cyan, "Scandlines", "Helsingør-Helsinborg","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,6, 2000,Color.orange,"Roskildevej","Navn: Roskildevej, pris: 2000 DKK, pris per hus:1.000 DKK, pris per hotel: 1.000 DKK+4 huse,leje af grund:100 DKK, med 1 hus:600 DKK,med 2 huse:1.800,med 3 huse:5.400 DKK,med 4 huse:8.000 DKK,med 1 hotel: 11.000 DKK,pantsætningsværdi: 1.000 DKK ");
        ownableField.setFields(fields,8, 2000,Color.orange,"Valby Langgade","Navn: Valby Langgade, Pris 2.000,pris per hus:1.000 DKK,pris per hotel: pris per hotel: 1.000 DKK+ 4 huse, leje af grund: 100 DKK, med 1 hus:600 DKK,med 2 huse:1.800,med 3 huse:5.400 DKK,med 4 huse:8.000 DKK,med 1 hotel: 11.000 DKK,pantsætningsværdi: 1.000 DKK");
        ownableField.setFields(fields,9, 2400,Color.orange,"Allegade","Navn:Allegade,pris:2400, pris per hus:1.000, pris per hotel:1.000 DKK+4 huse, leje af grund:150 DKK, med 1 hus:800, med 2 huse: 2.000 DKK, med 3 huse: 6.000 DKK, med 4 huse: 9.000 DKK,med 1 hotel: 12.000 DKK, pantsætningsværdi: 1.200 DKK ");
        ownableField.setBrewerFields(fields,12,Color.red,"Squash", "Tuborg, Hvis 1 virksomehed ejes, betales 100 gange så meget,som øjnene viser. Hvis både Coca Cola og Squash ejes betales 200 gange så meget som øjnene viser , pantsætningsærdi: 1.500 DKK","");
        ownableField.setFerryFields(fields, 15, Color.RED, "Mols-linien", "En genvej i Danmark","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,16,3600,Color.gray,"Bernstorffsvej","Navn Bernstorffsvej, Pris: 3600 DKK,Pris per hus: 2.000 DKK, pris per hotel: 2.000 DKK+4 huse, ");
        ownableField.setFerryFields(fields,25,Color.blue,"Scandlines", "Gedser-Rostock","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setBrewerFields(fields,28,Color.red,"Coca Cola", "Hvis 1 virksomehed ejes, betales 100 gange så meget,som øjnene viser. Hvis både Coca Cola og Squash ejes betales 200 gange så meget som øjnene viser , pantsætningsærdi: 1.500 DKK"," ");
        ownableField.setFerryFields(fields,35,Color.blue,"Scandlines", "Rødby-Puttgarden","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,13, 2800,Color.yellow,"Bulowsvej","pris pr hus: 2.000 DKK, pris pr hotel: 2.000 + 4 huse, Leje af tom jord: 200 DKK m/ 1 hus: 1.000 DKK m/ 2 huse: 3.000 DKK m/ 3 huse: 9.000 DKK m/4 huse: 12.500 DKK m/ hotel: 15.000 DKK Pantværdi: 1.400 DKK");
        ownableField.setFields(fields,14,3200,Color.yellow,"Gl. Kongevej","pris pr hus: 2.000 DKK, pris pr hotel: 2.000 + 4 huse, Leje af tom jord: 250 DKK m/ 1 hus: 1.250 DKK m/ 2 huse: 3.750 DKK m/ 3 huse: 10.000 DKK m/4 huse: 14.000 DKK m/ hotel: 18.000 DKK Pantværdi: 1.600 DKK");
        ownableField.setFerryFields(fields, 15, Color.RED, "Mols-linien", "En genvej i Danmark","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,16,3600,Color.gray,"Bernstorffsvej","pris pr hus: 2.000 DKK, pris pr hotel: 2.000 + 4 huse, Leje af tom jord: 300 DKK m/ 1 hus: 1.400 DKK m/ 2 huse: 4.000 DKK m/ 3 huse: 11.000 DKK m/4 huse: 15.000 DKK m/ hotel: 19.000 DKK Pantværdi: 1.800 DKK");
        ownableField.setFields(fields,18,3600,Color.gray,"Hellerupvej","pris pr hus: 2.000 DKK, pris pr hotel: 2.000 + 4 huse, Leje af tom jord: 300 DKK m/ 1 hus: 1.400 DKK m/ 2 huse: 4.000 DKK m/ 3 huse: 11.000 DKK m/4 huse: 15.000 DKK m/ hotel: 19.000 DKK Pantværdi: 1.800 DKK");
        ownableField.setFields(fields,19,4000,Color.gray,"Strandvejen","pris pr hus: 2.000 DKK, pris pr hotel: 2.000 + 4 huse, Leje af tom jord: 350 DKK m/ 1 hus: 1.600 DKK m/ 2 huse: 4.400 DKK m/ 3 huse: 12.000 DKK m/4 huse: 16.000 DKK m/ hotel: 20.000 DKK Pantværdi: 2.000 DKK");
        ownableField.setFields(fields,21,4400,Color.RED,"Trianglen","pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom jord: 350 DKK m/ 1 hus: 1.800 DKK m/ 2 huse: 5.000 DKK m/ 3 huse: 14.000 DKK m/4 huse: 17.500 DKK m/ hotel: 21.000 DKK Pantværdi: 2.200 DKK");
        ownableField.setFields(fields,23,4400,Color.RED,"Østerbrogade","pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom jord: 350 DKK m/ 1 hus: 1.800 DKK m/ 2 huse: 5.000 DKK m/ 3 huse: 14.000 DKK m/4 huse: 17.500 DKK m/ hotel: 21.000 DKK Pantværdi: 2.200 DKK");
        ownableField.setFields(fields,24,4800,Color.RED,"Grønningen","pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom jord: 400 DKK m/ 1 hus: 2.000 DKK m/ 2 huse: 6.000 DKK m/ 3 huse: 15.000 DKK m/4 huse: 18.500 DKK m/ hotel: 22.000 DKK Pantværdi: 2.400 DKK");
        ownableField.setFerryFields(fields,25,Color.blue,"Scandlines", "Gedser-Rostock","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,26, 5200,Color.white,"Bredgade", "pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom jord: 450 DKK m/ 1 hus: 2.200 DKK m/ 2 huse: 6.600 DKK m/ 3 huse: 16.000 DKK m/4 huse: 19.500 DKK m/ hotel: 23.000 DKK Pantværdi: 2.600 DKK");
        ownableField.setFields(fields,27, 5200,Color.white,"Kgs.Nytorv","pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom jord: 450 DKK m/ 1 hus: 2.200 DKK m/ 2 huse: 6.600 DKK m/ 3 huse: 16.000 DKK m/4 huse: 19.500 DKK m/ hotel: 23.000 DKK Pantværdi: 2.600 DKK");
        ownableField.setFields(fields,29, 5600,Color.white,"Østergade","pris pr hus: 3.000 DKK, pris pr hotel: 3.000 + 4 huse, Leje af tom grund: 500 kr. m/ 1 hus: 2.400 kr. m/ 2 huse: 7.200 kr. m/ 3 huse: 17.000 kr. m/4 huse: 20.500 kr. m/ hotel: 24.000 kr. Pantværdi: 2.800 kr.");
        ownableField.setFields(fields,31, 6000,Color.yellow,"Amagertorv","pris pr hus: 4.000 DKK, pris pr hotel: 4.000 + 4 huse, Leje af tom jord: 550 DKK m/ 1 hus: 2.600 DKK m/ 2 huse: 7.800 DKK m/ 3 huse: 18.000 DKK m/4 huse: 22.000 DKK m/ hotel: 25.000 DKK Pantværdi: 3.000 DKK");
        ownableField.setFields(fields,32, 6000,Color.yellow,"Vimmelskaffet","pris pr hus: 4.000 DKK, pris pr hotel: 4.000 + 4 huse, Leje af tom jord: 550 DKK m/ 1 hus: 2.600 DKK m/ 2 huse: 7.800 DKK m/ 3 huse: 18.000 DKK m/4 huse: 22.000 DKK m/ hotel: 25.000 DKK Pantværdi: 3.000 DKK");
        ownableField.setFields(fields,34, 6400,Color.yellow,"Nygade","pris pr hus: 4.000 DKK, pris pr hotel: 4.000 + 4 huse, Leje af tom jord: 600 DKK m/ 1 hus: 3.000 DKK m/ 2 huse: 9.000 DKK m/ 3 huse: 20.000 DKK m/4 huse: 24.000 DKK m/ hotel: 28.000 DKK Pantværdi: 3.200 DKK");
        ownableField.setFerryFields(fields,35,Color.blue,"Scandlines", "Rødby-Puttgarden","Leje:500 DKK, med 2 rederier: 1.000 DKK, med 3 rederier: 2.000 DKK,med 4 rederier: 4.000 DKK, Pantsætningværdi: 2.000 DKK");
        ownableField.setFields(fields,37, 7000,Color.magenta,"Frederiksberg gade","pris pr hus: 4.000 DKK, pris pr hotel: 4.000 + 4 huse, Leje af tom jord: 700 DKK m/ 1 hus: 3.500 DKK m/ 2 huse: 10.000 DKK m/ 3 huse: 22.000 DKK m/4 huse: 26.000 DKK m/ hotel: 30.000 DKK Pantværdi: 3.500 DKK");
        ownableField.setFields(fields,39, 8000,Color.magenta,"Rådhuspladsen","pris pr hus: 4.000 DKK, pris pr hotel: 4.000 + 4 huse, Leje af tom jord: 1.000 DKK m/ 1 hus: 4.000 DKK m/ 2 huse: 12.000 DKK m/ 3 huse: 28.000 DKK m/4 huse: 34.000 DKK m/ hotel: 40.000 DKK Pantværdi: 4.000 DKK");

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
