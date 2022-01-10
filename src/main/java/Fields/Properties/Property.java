package Fields.Properties;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import java.util.stream.IntStream;

public class Property {
    private GUI_Controller gui = new GUI_Controller();
    private Player owner;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player

    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice, amountOfHouses, intHelper,
            currentPriceOfBuilding, guiFieldNumber;

    protected boolean isOwned, allBlueOwned, allOrangeOwned, allDarkYellowOwned, allGreyOwned, allRedOwned,
            allWhiteOwned, allBrightYellowOwned, allPurpleOwned;

    private boolean chooseToBuildAgain, chooseAgain;

    //For each time a property field is initiated, it will need to start with setting all this above info
    protected Property(int rentOneOwned, int rentAllOwned, int rentOneHouse, int rentTwoHouse,
                       int rentThreeHouse, int rentFourHouse, int rentHotel, int fieldPrice,
                       int costOfOneHouse, int costOfHotel){

        this.rentOneOwned = rentOneOwned;
        this.rentAllOwned = rentAllOwned;
        this.rentOneHouse = rentOneHouse;
        this.rentTwoHouse = rentTwoHouse;
        this.rentThreeHouse = rentThreeHouse;
        this.rentFourHouse = rentFourHouse;
        this.rentHotel = rentHotel;
        this.fieldPrice = fieldPrice;
        this.costOfOneHouse = costOfOneHouse;
        this.costOfHotel = costOfHotel;
    }

    public void setCurrentRentPriceIfOwning(Player player, Property[] properties){

        if(!allBlueOwned && currentRentPrice != rentAllOwned) {
            switch (player.getBlueOwned()) {
                case 1 -> currentRentPrice = rentOneOwned;
                case 2 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[0].setCurrentRentPrice(rentAllOwned);
                    properties[1].setCurrentRentPrice(rentAllOwned);
                    allBlueOwned = true;
                }
            }
        }

        if(!allOrangeOwned && currentRentPrice != rentAllOwned) {
            switch (player.getOrangeOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[2].setCurrentRentPrice(rentAllOwned);
                    properties[3].setCurrentRentPrice(rentAllOwned);
                    properties[4].setCurrentRentPrice(properties[4].getRentAllOwned());
                    allOrangeOwned = true;
                }
            }
        }

        if(!allDarkYellowOwned && currentRentPrice != rentAllOwned) {
            switch (player.getDarkYellowOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    currentRentPrice = rentAllOwned;
                    owner.setOwnsAPropertySet(true);
                    properties[5].setCurrentRentPrice(rentAllOwned);
                    properties[6].setCurrentRentPrice(rentAllOwned);
                    properties[7].setCurrentRentPrice(properties[7].getRentAllOwned());
                    allDarkYellowOwned = true;
                }
            }
        }

        if(!allGreyOwned && currentRentPrice != rentAllOwned) {
            switch (player.getGreyOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    currentRentPrice = rentAllOwned;
                    owner.setOwnsAPropertySet(true);
                    properties[8].setCurrentRentPrice(rentAllOwned);
                    properties[9].setCurrentRentPrice(rentAllOwned);
                    properties[10].setCurrentRentPrice(properties[10].getRentAllOwned());
                    allGreyOwned = true;
                }
            }
        }

        if(!allRedOwned && currentRentPrice != rentAllOwned) {
            switch (player.getRedOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    currentRentPrice = rentAllOwned;
                    owner.setOwnsAPropertySet(true);
                    properties[11].setCurrentRentPrice(rentAllOwned);
                    properties[12].setCurrentRentPrice(rentAllOwned);
                    properties[13].setCurrentRentPrice(properties[13].getRentAllOwned());
                    allRedOwned = true;
                }
            }
        }

        if(!allWhiteOwned && currentRentPrice != rentAllOwned) {
            switch (player.getWhiteOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    currentRentPrice = rentAllOwned;
                    owner.setOwnsAPropertySet(true);
                    properties[14].setCurrentRentPrice(rentAllOwned);
                    properties[15].setCurrentRentPrice(rentAllOwned);
                    properties[16].setCurrentRentPrice(properties[16].getRentAllOwned());
                    allWhiteOwned = true;
                }
            }
        }

        if(!allBrightYellowOwned && currentRentPrice != rentAllOwned) {
            switch (player.getBrightYellowOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[17].setCurrentRentPrice(rentAllOwned);
                    properties[18].setCurrentRentPrice(rentAllOwned);
                    properties[19].setCurrentRentPrice(properties[19].getRentAllOwned());
                    allBrightYellowOwned = true;
                }
            }
        }

        if(!allPurpleOwned && currentRentPrice != rentAllOwned) {
            switch (player.getPurpleOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[20].setCurrentRentPrice(rentAllOwned);
                    properties[21].setCurrentRentPrice(rentAllOwned);
                    properties[22].setCurrentRentPrice(properties[22].getRentAllOwned());
                    allPurpleOwned = true;
                }
            }
        }
    }

    public int getCurrentPriceOfBuilding(){

        switch (amountOfHouses){
            case 0,1,2,3,4 -> currentPriceOfBuilding = costOfOneHouse;
            case 5 -> currentPriceOfBuilding = costOfHotel;
        }
        return currentPriceOfBuilding;
    }

    public void setCurrentRentPrice(int currentRentPrice) {
        this.currentRentPrice = currentRentPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public int getRentAllOwned() {
        return rentAllOwned;
    }

    public int getRentOneHouse() {
        return rentOneHouse;
    }

    public int getRentTwoHouse() {
        return rentTwoHouse;
    }

    public int getRentThreeHouse() {
        return rentThreeHouse;
    }

    public int getRentFourHouse() {
        return rentFourHouse;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public void landOnProperty(Player player, GUI_Player gui_player, Property[] properties){
        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && !isOwned){
            optionBuyProperty(player, gui_player, properties);
        }

        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner){
            payOwner(player, gui_player);
        }
    }


    public void optionBuyProperty(Player player, GUI_Player gui_player, Property[] properties){

        String buttonPressed = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                gui.getSpecificField(player.getSquare()).getTitle() + ", vil du købe denne for " + fieldPrice + " DKK?", "Ja", "Nej");

        if(buttonPressed.equals("Ja")){
            this.guiOwner = gui_player;
            this.owner = player;
            isOwned = true;

            owner.getAccount().setMoney(-fieldPrice);
            guiOwner.setBalance(owner.getAccount().getMoney());

            gui.getSpecificField(owner.getSquare()).setSubText(owner.getName());

            switch (owner.getSquare()){
                case 1, 3 -> owner.setBlueOwned();
                case 6, 8, 9 -> owner.setOrangeOwned();
                case 11, 13, 14 -> owner.setDarkYellowOwned();
                case 16, 18, 19 -> owner.setGreyOwned();
                case 21, 23, 24 -> owner.setRedOwned();
                case 26, 27, 29 -> owner.setWhiteOwned();
                case 31, 32, 34 -> owner.setBrightYellowOwned();
            }
            setCurrentRentPriceIfOwning(player, properties);
        }
    }

    public void payOwner(Player player, GUI_Player gui_player){

        gui.getInstance().getUserButtonPressed("Oh oh, " + player.getName() + ", du er landet på " + owner.getName() +
                "'s felt: " + gui.getSpecificField(player.getSquare()).getTitle() +
                ", du skal af med " + currentRentPrice + " DKK", "Betal");

        player.getAccount().setMoney(-currentRentPrice);
        gui_player.setBalance(player.getAccount().getMoney());

        owner.getAccount().setMoney(currentRentPrice);

        guiOwner.setBalance(owner.getAccount().getMoney());
    }

    public int getAmountOfHouses() {
        return amountOfHouses;
    }

    public void setPlusOneAmountOfHouses() {
        this.amountOfHouses += 1;
    }

    public void placeHouse(int fieldNumber, Property[] properties){


        owner.getAccount().setMoney(-properties[intHelper].getCurrentPriceOfBuilding());
        guiOwner.setBalance(owner.getAccount().getMoney());

        properties[intHelper].setPlusOneAmountOfHouses();

        switch (properties[intHelper].getAmountOfHouses()){
            case 1 ->{properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentOneHouse());
                gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 2 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentTwoHouse());
                gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 3 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentThreeHouse());
                gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 4 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentFourHouse());
                gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 5 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentHotel());
                gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(0);
                gui.getGameBoard().getGuiStreet(fieldNumber).setHotel(true);}
        }
    }

    public void placeTwoEvenHouses(int chosenFieldToBuildOn, int fieldOne, int fieldTwo, Property[] properties){

        boolean firstTrue = false, secondTrue = false;

        checkWhichPropertyField(chosenFieldToBuildOn);

        if((intHelper == fieldOne) && properties[fieldOne].getAmountOfHouses() < 5 &&
                properties[fieldOne].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
        && properties[intHelper].getAmountOfHouses() != 5){

            placeHouse(chosenFieldToBuildOn, properties);
            firstTrue = true;
        }

        if(intHelper == fieldTwo && properties[fieldTwo].getAmountOfHouses() < 5 &&
        properties[fieldTwo].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
        && properties[intHelper].getAmountOfHouses() != 5){

            placeHouse(chosenFieldToBuildOn, properties);
            secondTrue = true;

        }
        else if((intHelper == fieldOne) && !firstTrue &&
                properties[fieldOne].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses()+1)
        && properties[intHelper].getAmountOfHouses() != 5){
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
        }
        else if((intHelper == fieldTwo) && !secondTrue &&
                properties[fieldOne].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses()+1)
        && properties[intHelper].getAmountOfHouses() != 5){
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

    }

    public void placeThreeEvenHouses(int chosenFieldToBuildOn, int fieldOne, int fieldTwo, int fieldThree, Property[] properties) {

        boolean firstTrue = false, secondTrue = false, thirdTrue = false;

        checkWhichPropertyField(chosenFieldToBuildOn);

        if ((intHelper == fieldOne) && properties[fieldOne].getAmountOfHouses() < 5 &&
                (properties[fieldOne].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
                        && properties[fieldOne].getAmountOfHouses() <= properties[fieldThree].getAmountOfHouses())
        && properties[intHelper].getAmountOfHouses() != 5) {

            placeHouse(chosenFieldToBuildOn, properties);
            firstTrue = true;
        }

        if (intHelper == fieldTwo && properties[fieldTwo].getAmountOfHouses() < 5 &&
                (properties[fieldTwo].getAmountOfHouses() <= properties[fieldOne].getAmountOfHouses()
                        && properties[fieldTwo].getAmountOfHouses() <= properties[fieldThree].getAmountOfHouses())
        && properties[intHelper].getAmountOfHouses() != 5) {

            placeHouse(chosenFieldToBuildOn, properties);
            secondTrue = true;
        }

        if(intHelper == fieldThree && properties[fieldThree].getAmountOfHouses() < 5 &&
                (properties[fieldThree].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
        && properties[fieldThree].getAmountOfHouses() <= properties[fieldOne].getAmountOfHouses())
        && properties[intHelper].getAmountOfHouses() != 5){

            placeHouse(chosenFieldToBuildOn, properties);
            thirdTrue = true;
        }

        else if ((intHelper == fieldOne) && !firstTrue && !secondTrue && !thirdTrue &&
                (properties[fieldOne].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1)
                        || properties[fieldOne].getAmountOfHouses() >= (properties[fieldThree].getAmountOfHouses() + 1))
        && properties[intHelper].getAmountOfHouses() != 5) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        else if ((intHelper == fieldTwo) && !firstTrue && !secondTrue && !thirdTrue &&
                (properties[fieldTwo].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1)
                        || properties[fieldTwo].getAmountOfHouses() >= (properties[fieldThree].getAmountOfHouses() + 1))
        && properties[intHelper].getAmountOfHouses() != 5) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;


        } else if ((intHelper == fieldThree) && !thirdTrue && !firstTrue && !secondTrue &&
                (properties[fieldThree].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1)
                        || properties[fieldThree].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1))
        && properties[intHelper].getAmountOfHouses() != 5) {
            gui.getInstance().showMessage("3333 Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        if(properties[intHelper].getAmountOfHouses() == 5){
            gui.getInstance().showMessage("Du kan ikke købe mere på her! Du har hotel");
        }
    }

    public void checkWhichPropertyField(int fieldNumber){

        switch (fieldNumber) {
            case 1 -> intHelper = 0;
            case 3 -> intHelper = 1;
            case 6 -> intHelper = 2;
            case 8 -> intHelper = 3;
            case 9 -> intHelper = 4;
            case 11 -> intHelper = 5;
            case 13 -> intHelper = 6;
            case 14 -> intHelper = 7;
            case 16 -> intHelper = 8;
            case 18 -> intHelper = 9;
            case 19 -> intHelper = 10;
            case 21 -> intHelper = 11;
            case 23 -> intHelper = 12;
            case 24 -> intHelper = 13;
            case 26 -> intHelper = 14;
            case 27 -> intHelper = 15;
            case 29 -> intHelper = 16;
            case 31 -> intHelper = 17;
            case 32 -> intHelper = 18;
            case 34 -> intHelper = 19;
            case 37 -> intHelper = 20;
            case 39 -> intHelper = 21;
        }
    }

    private void checkGuiFieldNumberFromPropertyNumber(int propertyNumber){
        switch (propertyNumber){
            case 0 -> guiFieldNumber = 1;
            case 1 -> guiFieldNumber = 3;
            case 2 -> guiFieldNumber = 6;
            case 3 -> guiFieldNumber = 8;
            case 4 -> guiFieldNumber = 9;
            case 5 -> guiFieldNumber = 11;
            case 6 -> guiFieldNumber = 13;
            case 7 -> guiFieldNumber = 14;
            case 8 -> guiFieldNumber = 16;
            case 9 -> guiFieldNumber = 18;
            case 10 -> guiFieldNumber = 19;
            case 11 -> guiFieldNumber = 21;
            case 12 -> guiFieldNumber = 23;
            case 13 -> guiFieldNumber = 24;
            case 14 -> guiFieldNumber = 26;
            case 15 -> guiFieldNumber = 27;
            case 16 -> guiFieldNumber = 29;
            case 17 -> guiFieldNumber = 31;
            case 18 -> guiFieldNumber = 32;
            case 19 -> guiFieldNumber = 34;
            case 20 -> guiFieldNumber = 37;
            case 21 -> guiFieldNumber = 39;
        }
    }

    public void resetProperty(int propertyNumber){

        checkGuiFieldNumberFromPropertyNumber(propertyNumber);

        int price = Integer.parseInt(gui.getGameBoard().getGuiStreet(guiFieldNumber).getRent());

        gui.getSpecificField(guiFieldNumber).setSubText("Pris: " + price);

        this.owner = null;
        this.guiOwner = null;
        this.isOwned = false;
        this.currentRentPrice = rentOneOwned;

        gui.getGameBoard().getGuiStreet(guiFieldNumber).setHouses(0);
        gui.getGameBoard().getGuiStreet(guiFieldNumber).setHotel(false);
    }


    public void optionsWhenOwningAllFields(Property[] properties, Player player){

        chooseAgain = true;

        while(chooseAgain) {
            chooseAgain = false;

            String colorPressed = gui.getInstance().getUserButtonPressed(player.getName() + ", vælg hvor du vil bygge", "Blå",
                    "Orange", "Mørkegul", "Grå", "Rød", "Hvid", "Lysegul", "Lilla", "Ingen");

            chooseToBuildAgain = true;
            while (chooseToBuildAgain) {

                switch (colorPressed) {
                    case "Blå" -> caseTwoColorsChosen(player, properties, allBlueOwned, 1, 3, 0, 1, "blå");

                    case "Orange" -> caseThreeColorsChosen(player, properties, allOrangeOwned, 6, 8, 9,
                            2, 3, 4, "orange");

                    case "Mørkegul" -> caseThreeColorsChosen(player, properties, allDarkYellowOwned, 11, 13, 14, 5,
                            6, 7, "mørkegul");

                    case "Grå" -> caseThreeColorsChosen(player, properties, allGreyOwned, 16, 18, 19, 8,
                            9, 10, "grå");

                    case "Rød" -> caseThreeColorsChosen(player, properties, allRedOwned, 21, 23, 24, 11,
                            12, 13, "rød");

                    case "Hvid" -> caseThreeColorsChosen(player, properties, allWhiteOwned, 26, 27, 29, 14,
                            15, 16, "hvid");

                    case "Lysegul" -> caseThreeColorsChosen(player, properties, allBrightYellowOwned, 31, 32, 34,
                            17, 18, 19, "lysegul");

                    case "Lilla" -> caseTwoColorsChosen(player, properties, allPurpleOwned, 37, 39, 20,
                            21, "lilla");

                    case "Ingen" -> {chooseAgain = false; chooseToBuildAgain = false;}
                }
            }
        }
    }

    public void caseTwoColorsChosen(Player player, Property[] properties, boolean whichAllColor, int guiField1,
                                    int guiField2, int propertyField1, int propertyField2, String colorString){

        if(whichAllColor && player == properties[propertyField1].getOwner()){

                chooseToBuildAgain = false;

                String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                        gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK",
                        gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK",
                        "ingen, jeg vil ikke bygge i " + colorString);

                if (secondButton.equals(gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK")) {
                    placeTwoEvenHouses(guiField1, propertyField1, propertyField2, properties);
                    chooseAgain = true;
                }
                if (secondButton.equals(gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK")) {
                    placeTwoEvenHouses(guiField2, propertyField1, propertyField2, properties);
                    chooseAgain = true;
                }
                if (secondButton.equals("ingen, jeg vil ikke bygge i " + colorString)) {
                    chooseAgain = true;
                    chooseToBuildAgain = false;
            }

        }else if(player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()){
            gui.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden farve");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
    }

    public void caseThreeColorsChosen(Player player, Property[] properties, boolean whichAllColor, int guiField1, int guiField2,
                                      int guiField3, int propertyField1, int propertyField2, int propertyField3, String colorString) {

        if (whichAllColor && player == properties[propertyField1].getOwner()) {

            String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                    gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                            + " DKK", gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                            + " DKK", gui.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                            + " DKK", "ingen, jeg vil ikke købe i " + colorString);

            if (secondButton.equals(gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                    + " DKK")) {
                placeThreeEvenHouses(guiField1, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                    + " DKK")) {
                placeThreeEvenHouses(guiField2, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(gui.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                    + " DKK")) {
                placeThreeEvenHouses(guiField3, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals("ingen, jeg vil ikke købe i " + colorString)) {
                chooseAgain = true;
                chooseToBuildAgain = false;
            }

        } else if (player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()
                || player != properties[propertyField3].getOwner()) {
            gui.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
    }
}