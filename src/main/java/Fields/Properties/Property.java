package Fields.Properties;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

public class Property {
    private GUI_Controller gui = new GUI_Controller();
    private Player owner;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player

    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice, amountOfHouses, intHelper;

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



    public void setCurrentRentPrice(int currentRentPrice) {
        this.currentRentPrice = currentRentPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public int getRentOneOwned() {
        return rentOneOwned;
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

    public int getFieldPrice() {
        return fieldPrice;
    }

    public int getCostOfOneHouse() {
        return costOfOneHouse;
    }

    public int getCostOfHotel() {
        return costOfHotel;
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


        owner.getAccount().setMoney(-properties[intHelper].getCostOfOneHouse());
        guiOwner.setBalance(owner.getAccount().getMoney());

        properties[intHelper].setPlusOneAmountOfHouses();

        switch (properties[intHelper].getAmountOfHouses()){
            case 1 -> properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentOneHouse());
            case 2 -> properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentTwoHouse());
            case 3 -> properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentThreeHouse());
            case 4 -> properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentFourHouse());
        }
        gui.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());
    }

    public void placeTwoEvenHouses(int chosenFieldToBuildOn, int fieldOne, int fieldTwo, Property[] properties){

        boolean firstTrue = false, secondTrue = false;

        checkWhichPropertyField(chosenFieldToBuildOn);

        if((intHelper == fieldOne) &&
                properties[fieldOne].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()){

            placeHouse(chosenFieldToBuildOn, properties);
            firstTrue = true;
        }

        if(intHelper == fieldTwo &&
        properties[fieldTwo].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()){

            placeHouse(chosenFieldToBuildOn, properties);
            secondTrue = true;

        }
        else if((intHelper == fieldOne) && !firstTrue &&
                properties[fieldOne].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses()+1)){
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
        }
        else if((intHelper == fieldTwo) && !secondTrue &&
                properties[fieldOne].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses()+1)){
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

    }

    public void placeThreeEvenHouses(int chosenFieldToBuildOn, int fieldOne, int fieldTwo, int fieldThree, Property[] properties) {

        boolean firstTrue = false, secondTrue = false, thirdTrue = false;

        checkWhichPropertyField(chosenFieldToBuildOn);

        if ((intHelper == fieldOne) &&
                (properties[fieldOne].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
                        && properties[fieldOne].getAmountOfHouses() <= properties[fieldThree].getAmountOfHouses())) {

            placeHouse(chosenFieldToBuildOn, properties);
            firstTrue = true;
        }

        if (intHelper == fieldTwo &&
                (properties[fieldTwo].getAmountOfHouses() <= properties[fieldOne].getAmountOfHouses()
                        && properties[fieldTwo].getAmountOfHouses() <= properties[fieldThree].getAmountOfHouses())) {

            placeHouse(chosenFieldToBuildOn, properties);
            secondTrue = true;

        }

        if(intHelper == fieldThree && (properties[fieldThree].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses()
        && properties[fieldThree].getAmountOfHouses() <= properties[fieldOne].getAmountOfHouses())){

            placeHouse(chosenFieldToBuildOn, properties);
            thirdTrue = true;
        }

        else if ((intHelper == fieldOne) && !firstTrue &&
                (properties[fieldOne].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1)
                        || properties[fieldOne].getAmountOfHouses() >= (properties[fieldThree].getAmountOfHouses()+1))) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;

        } else if ((intHelper == fieldTwo) && !secondTrue &&
                properties[fieldTwo].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1)
                || properties[fieldTwo].getAmountOfHouses() >= (properties[fieldThree].getAmountOfHouses()+1)) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;

        } else if ((intHelper == fieldThree) && !thirdTrue &&
                (properties[fieldThree].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1)
                        || properties[fieldThree].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses()+1))) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
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

    public void optionsWhenOwningAllFields(Property[] properties, Player player){

        chooseAgain = true;

        while(chooseAgain) {
            chooseAgain = false;

            String colorPressed = gui.getInstance().getUserButtonPressed(player.getName() + ", vælg hvor du vil bygge", "Blå",
                    "Orange", "Mørkegul", "Grå", "Rød", "Hvid", "Lysegul", "Lilla", "Ingen");

            switch (colorPressed) {
                case "Blå" -> caseTwoColorsChosen(player, properties, allBlueOwned, 1, 3, 0, 1, "blå");

                case "Orange" -> caseThreeColorsChosen(player, properties, allOrangeOwned, 6,8,9,
                        2,3,4, "orange");

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
            }
        }
    }

    public void caseTwoColorsChosen(Player player, Property[] properties, boolean whichAllColor, int guiField1,
                                    int guiField2, int propertyField1, int propertyField2, String colorString){

        if(whichAllColor && player == properties[propertyField1].getOwner()){

            chooseToBuildAgain = true;
            while (chooseToBuildAgain) {
                chooseToBuildAgain = false;

                String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                        gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getFieldPrice() + " DKK",
                        gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getFieldPrice() + " DKK",
                        "ingen af husene, jeg vil ikke købe i " + colorString);

                if (secondButton.equals(gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getFieldPrice() + " DKK")) {
                    placeTwoEvenHouses(guiField1, propertyField1, propertyField2, properties);
                    chooseAgain = true;

                } else if (secondButton.equals(gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getFieldPrice() + " DKK")) {
                    placeTwoEvenHouses(guiField2, propertyField1, propertyField2, properties);
                    chooseAgain = true;

                } else if (secondButton.equals("ingen, jeg vil ikke bygge i " + colorString)) {
                    chooseAgain = true;
                }
            }

        }else if(player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()){
            gui.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden farve");
            chooseAgain = true;
        }
    }

    public void caseThreeColorsChosen(Player player, Property[] properties, boolean whichAllColor, int guiField1, int guiField2,
                                      int guiField3, int propertyField1, int propertyField2, int propertyField3, String colorString){

        if(whichAllColor && player == properties[propertyField1].getOwner()){

            chooseToBuildAgain = true;
            while (chooseToBuildAgain) {
                chooseToBuildAgain = false;

                String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                        gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getFieldPrice()
                                + " DKK", gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getFieldPrice()
                                + " DKK", gui.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getFieldPrice()
                                + " DKK", "ingen, jeg vil ikke købe i " + colorString);

                if (secondButton.equals(gui.getSpecificField(guiField1).getTitle())) {
                    placeThreeEvenHouses(guiField1, propertyField1, propertyField2, propertyField3, properties);
                    chooseAgain = true;

                } else if (secondButton.equals(gui.getSpecificField(guiField2).getTitle())) {
                    placeThreeEvenHouses(guiField2, propertyField1, propertyField2, propertyField3, properties);
                    chooseAgain = true;

                } else if (secondButton.equals(gui.getSpecificField(guiField3).getTitle())) {
                    placeThreeEvenHouses(guiField3, propertyField1, propertyField2, propertyField3, properties);
                    chooseAgain = true;

                } else if (secondButton.equals("ingen, jeg vil ikke købe i " + colorString)) {
                    chooseAgain = true;
                }
            }

        }else if(player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()
        || player != properties[propertyField3].getOwner()){
            gui.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden");
            chooseAgain = true;
        }
    }


    public static void main(String[] args) {

        GUI_Controller gui = new GUI_Controller();
        GameBoard gameBoard = new GameBoard();
        gameBoard.createPropertiesPrices();

        Player player = new Player();
        player.setName("Huss");
        GUI_Car car = new GUI_Car();
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);

        gui.getInstance().addPlayer(gui_player);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        player.moveToHere(1);
        gameBoard.getProperty(player).landOnProperty(player, gui_player, gameBoard.getProperties());

        player.moveToHere(3);
        gameBoard.getProperty(player).landOnProperty(player, gui_player, gameBoard.getProperties());








    }


}