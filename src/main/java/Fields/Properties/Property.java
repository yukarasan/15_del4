package Fields.Properties;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

import static java.awt.Color.blue;
import static java.awt.Color.red;

public class Property {
    private GUI_Controller gui = new GUI_Controller();
    private Player owner;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
    private int currentBid;

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player
    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice, amountOfHouses, intHelper,
            currentPriceOfBuilding, guiFieldNumber;

    protected static boolean allBlueOwned, allOrangeOwned, allDarkYellowOwned, allGreyOwned, allRedOwned,
            allWhiteOwned, allBrightYellowOwned, allPurpleOwned;

    private boolean isOwned, chooseToBuildAgain, chooseAgain, bidAgain, justBought;

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

    public int getCurrentRentPrice() {
        return currentRentPrice;
    }


    public void setCurrentRentPriceIfOwningTwo(Player player, Property[] properties, boolean whichAllColor,
                                               int propertyNumberOne, int propertyNumberTwo, String color){

        int amountOfSpecificColorOwned = 0;

        switch(color){
            case "red" -> amountOfSpecificColorOwned = player.getRedOwned();
            case "brightYellow" -> amountOfSpecificColorOwned = player.getBrightYellowOwned();
            case "purple" -> amountOfSpecificColorOwned = player.getPurpleOwned();
            case "darkYellow" -> amountOfSpecificColorOwned = player.getDarkYellowOwned();
            case "blue" -> amountOfSpecificColorOwned = player.getBlueOwned();
            case "orange" -> amountOfSpecificColorOwned = player.getOrangeOwned();
            case "white" -> amountOfSpecificColorOwned = player.getWhiteOwned();
            case "grey" -> amountOfSpecificColorOwned = player.getGreyOwned();
        }

        if(!whichAllColor && properties[propertyNumberOne].getCurrentRentPrice() != properties[propertyNumberOne].getRentAllOwned()) {
            switch (amountOfSpecificColorOwned) {
                case 1 -> currentRentPrice = rentOneOwned;
                case 2 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[propertyNumberOne].setCurrentRentPrice(rentAllOwned);
                    properties[propertyNumberTwo].setCurrentRentPrice(rentAllOwned);
                    whichAllColor = true;
                }
            }
        }
    }


    public void setCurrentRentPriceIfOwningThree(Player player, Property[] properties, boolean whichAllColor, int fieldOne,
                                                 int fieldTwo, int fieldThree, String color){

        int amountOfSpecificColorOwned = 0;

        switch(color){
            case "red" -> amountOfSpecificColorOwned = player.getRedOwned();
            case "brightYellow" -> amountOfSpecificColorOwned = player.getBrightYellowOwned();
            case "purple" -> amountOfSpecificColorOwned = player.getPurpleOwned();
            case "darkYellow" -> amountOfSpecificColorOwned = player.getDarkYellowOwned();
            case "blue" -> amountOfSpecificColorOwned = player.getBlueOwned();
            case "orange" -> amountOfSpecificColorOwned = player.getOrangeOwned();
            case "white" -> amountOfSpecificColorOwned = player.getWhiteOwned();
            case "grey" -> amountOfSpecificColorOwned = player.getGreyOwned();
        }

        if(!whichAllColor && currentRentPrice != rentAllOwned) {
            switch (amountOfSpecificColorOwned) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    owner.setOwnsAPropertySet(true);
                    currentRentPrice = rentAllOwned;
                    properties[fieldOne].setCurrentRentPrice(rentAllOwned);
                    properties[fieldTwo].setCurrentRentPrice(rentAllOwned);
                    properties[fieldThree].setCurrentRentPrice(properties[fieldThree].getRentAllOwned());
                    whichAllColor = true;
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

    public void landOnProperty(Player player, GUI_Player gui_player, Property[] properties, Player[] players, GUI_Player[] gui_players){
        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && !isOwned){
            optionBuyProperty(player, gui_player, properties, players, gui_players);
        }

        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner && !justBought){
            payOwner(player, gui_player);
        }

        if(justBought){
            justBought = false;
        }

    }

    public void optionBuyProperty(Player player, GUI_Player gui_player, Property[] properties, Player[] players, GUI_Player[] gui_players) {
//////////IKKE HVIS HAN IKKE HAR RÅD FIX DET

        String buttonPressed = null;

        if (player.getAccount().getMoney() > fieldPrice) {
            buttonPressed = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                            gui.getSpecificField(player.getSquare()).getTitle() + ", vil du købe denne for " + fieldPrice + " DKK?", "Ja",
                    "Nej, sæt feltet på auktion");
        }

        if (player.getAccount().getMoney() < fieldPrice) {
            buttonPressed = gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                            gui.getSpecificField(player.getSquare()).getTitle() + ", den koster " + fieldPrice + " DKK, men du har" +
                            " ikke råd. Du bliver nødt til at sætte den på auktion",
                    "Sæt feltet på auktion");

            if (buttonPressed.equals("Ja")) {
                this.guiOwner = gui_player;
                this.owner = player;
                isOwned = true;

                owner.getAccount().setMoney(-fieldPrice);
                guiOwner.setBalance(owner.getAccount().getMoney());

                gui.getSpecificField(owner.getSquare()).setSubText(owner.getName());
                gui.getGameBoard().getGuiStreet(owner.getSquare()).setBorder(guiOwner.getCar().getPrimaryColor());

                switch (player.getSquare()) {
                    case 1, 3 -> {player.setBlueOwned(); setCurrentRentPriceIfOwningTwo(player, properties,
                            allBlueOwned, 0, 1, "blue");}
                    case 6, 8, 9 -> {player.setOrangeOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allOrangeOwned, 2,3,4, "orange");}
                    case 11, 13, 14 -> {player.setDarkYellowOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allDarkYellowOwned, 5, 6, 7, "darkYellow");}
                    case 16, 18, 19 -> {player.setGreyOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allGreyOwned, 8, 9, 10, "grey");}
                    case 21, 23, 24 -> {player.setRedOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allRedOwned, 11, 12, 13, "red");
                    }
                    case 26, 27, 29 -> {player.setWhiteOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allWhiteOwned, 14, 15, 16, "white");}
                    case 31, 32, 34 -> {player.setBrightYellowOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                            allBrightYellowOwned, 17, 18, 19, "brightYellow");}
                    case 37, 39 -> {player.setPurpleOwned(); setCurrentRentPriceIfOwningTwo(player, properties,
                            allPurpleOwned, 20, 21, "purple");}
                }
            }
            if(buttonPressed.equals("Nej, sæt feltet på auktion") || buttonPressed.equals("Sæt feltet på auktion")){
                setPropertyOnAuction(player, players, properties, gui_players);
            }
        }
    }

    public void setPropertyOnAuction(Player player, Player[] players, Property[] properties, GUI_Player[] gui_players){

        checkWhichPropertyField(player.getSquare());
        gui.getInstance().showMessage("Feltet " + gui.getSpecificField(player.getSquare()).getTitle() + " er sat på auktion" +
                ", andre spillere kan nu byde på denne");

        Player isOutOfAuction[] = new Player[players.length];
        Player highestBidder = null;

        boolean bought = false;
        int isOut = 0;

        int richestAmount = 0;
        for (int i = 0; i < players.length; i++) {

            if(players[i].getAccount().getMoney() > richestAmount){
                richestAmount = players[i].getAccount().getMoney();
            }
        }

        while(!bought) {

            for (int i = 0; i < players.length; i++) {

                bidAgain = true;

                if (players[i] != isOutOfAuction[i] && !bought
                        && players[i].getAccount().getMoney() > (currentBid + 100) && !players[i].getPlayerOutOfGame()) {
                    while (bidAgain && players[i].getAccount().getMoney() > (currentBid + 50) && players[i] != isOutOfAuction[i]
                            && currentBid < richestAmount ) {
                        bidAgain = false;
                        String bid = gui.getInstance().getUserButtonPressed(players[i].getName() + ", du kan byde på feltet "
                                        + gui.getSpecificField(player.getSquare()).getTitle() + ". (Oprindelig pris: " + properties[intHelper].getFieldPrice()
                                        + "). Det nuværende bud er " + currentBid + ". Hvad vil du byde med mere",
                                "50", "100", "500", "1000", "2000", "5000", "Ønsker ikke at byde");

                        switch (bid) {
                            case "50", "100", "500", "1000", "2000", "5000" -> placeBit(player, Integer.parseInt(bid) + currentBid, highestBidder);
                            case "Ønsker ikke at byde" -> {
                                isOutOfAuction[i] = players[i];
                                isOut++;
                            }
                        }
                    }
                }
                if (isOut == (isOutOfAuction.length - 1) || currentBid >= richestAmount - 50) {
                    bought = true;
                }
            }
        }

        if(bought){

            for (int i = 0; i < players.length; i++) {

                if(players[i] != isOutOfAuction[i]) {
                    checkGuiFieldNumberFromPropertyNumber(intHelper);
                    gui.getInstance().showMessage("Tillykke " + players[i].getName() + ", du har købt feltet " +
                            gui.getSpecificField(guiFieldNumber).getTitle() + " for " + currentBid);
                    properties[intHelper].boughtFieldFromAuction(gui_players[i], players[i], properties);
                }
            }
        }
    }

    private void placeBit(Player player, int bid, Player highestBidder){

        if(bid >= player.getAccount().getMoney()){
            gui.getInstance().showMessage(player.getName() + ", du kan ikke byde højere end det du har, byd en anden værdi");
        }else{
            currentBid = bid;
            highestBidder = player;
        }

    }

    public boolean setIsOwned(boolean isOwned) {
        return isOwned = isOwned;
    }

    public boolean getJustBought(){
        return justBought;
    }

    public void boughtFieldFromAuction(GUI_Player gui_player, Player player, Property[] properties){

        properties[intHelper].setGuiOwner(gui_player);
        properties[intHelper].setOwner(player);
        properties[intHelper].setIsOwned(true);

        player.getAccount().setMoney(-currentBid);
        gui_player.setBalance(player.getAccount().getMoney());

        checkGuiFieldNumberFromPropertyNumber(intHelper);
        gui.getSpecificField(guiFieldNumber).setSubText(player.getName());
        gui.getGameBoard().getGuiStreet(guiFieldNumber).setBorder(gui_player.getCar().getPrimaryColor());

        justBought = true;

        switch (guiFieldNumber) {
            case 1, 3 -> {player.setBlueOwned(); setCurrentRentPriceIfOwningTwo(player, properties,
                    allBlueOwned, 0, 1, "blue");}
            case 6, 8, 9 -> {player.setOrangeOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allOrangeOwned, 2,3,4, "orange");}
            case 11, 13, 14 -> {player.setDarkYellowOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allDarkYellowOwned, 5, 6, 7, "darkYellow");}
            case 16, 18, 19 -> {player.setGreyOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allGreyOwned, 8, 9, 10, "grey");}
            case 21, 23, 24 -> {player.setRedOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allRedOwned, 11, 12, 13, "red");}
            case 26, 27, 29 -> {player.setWhiteOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allWhiteOwned, 14, 15, 16, "white");}
            case 31, 32, 34 -> {player.setBrightYellowOwned(); setCurrentRentPriceIfOwningThree(player, properties,
                    allBrightYellowOwned, 17, 18, 19, "brightYellow");}
            case 37, 39 -> {player.setPurpleOwned(); setCurrentRentPriceIfOwningTwo(player, properties,
                    allPurpleOwned, 20, 21, "purple");}
        }
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setGuiOwner(GUI_Player guiOwner) {
        this.guiOwner = guiOwner;
    }

    public int getFieldPrice() {
        return fieldPrice;
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

        if ((intHelper == fieldOne) && properties[fieldOne].getAmountOfHouses() < 5 &&
                (properties[fieldOne].getAmountOfHouses() <= properties[fieldTwo].getAmountOfHouses())
                && properties[intHelper].getAmountOfHouses() != 5) {

            placeHouse(chosenFieldToBuildOn, properties);
            chooseToBuildAgain = true;
            firstTrue = true;
        }

        if (intHelper == fieldTwo && properties[fieldTwo].getAmountOfHouses() < 5 &&
                (properties[fieldTwo].getAmountOfHouses() <= properties[fieldOne].getAmountOfHouses())
                && properties[intHelper].getAmountOfHouses() != 5) {

            placeHouse(chosenFieldToBuildOn, properties);
            chooseToBuildAgain = true;
            secondTrue = true;
        }

        else if ((intHelper == fieldOne) && !firstTrue && !secondTrue &&
                (properties[fieldOne].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1))
                && properties[intHelper].getAmountOfHouses() != 5) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        else if ((intHelper == fieldTwo) && !firstTrue && !secondTrue &&
                (properties[fieldTwo].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1))
                && properties[intHelper].getAmountOfHouses() != 5) {
            gui.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        if(properties[intHelper].getAmountOfHouses() == 5){
            gui.getInstance().showMessage("Du kan ikke købe mere på her! Du har hotel");
            chooseToBuildAgain = true;
            chooseAgain = true;
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
                    case "Blå" -> caseTwoColorsChosen(player, properties, 1, 3, 0, 1, "blå");

                    case "Orange" -> caseThreeColorsChosen(player, properties, 6, 8, 9,
                            2, 3, 4, "orange");

                    case "Mørkegul" -> caseThreeColorsChosen(player, properties, 11, 13, 14, 5,
                            6, 7, "mørkegul");

                    case "Grå" -> caseThreeColorsChosen(player, properties, 16, 18, 19, 8,
                            9, 10, "grå");

                    case "Rød" -> caseThreeColorsChosen(player, properties, 21, 23, 24, 11,
                            12, 13, "rød");

                    case "Hvid" -> caseThreeColorsChosen(player, properties, 26, 27, 29, 14,
                            15, 16, "hvid");

                    case "Lysegul" -> caseThreeColorsChosen(player, properties, 31, 32, 34,
                            17, 18, 19, "lysegul");

                    case "Lilla" -> caseTwoColorsChosen(player, properties, 37, 39, 20,
                            21, "lilla");

                    case "Ingen" -> {chooseAgain = false; chooseToBuildAgain = false;}
                }
            }
        }
    }

    public void caseTwoColorsChosen(Player player, Property[] properties, int guiField1, int guiField2, int propertyField1,
                                    int propertyField2, String colorString){

        if(player == properties[propertyField1].getOwner() && player == properties[propertyField2].getOwner()){

                chooseToBuildAgain = false;

                String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                        gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK",
                        gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK",
                        "ingen, jeg vil ikke bygge i " + colorString);

                if (secondButton.equals(gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK"
                ) && player.getAccount().getMoney() > properties[propertyField1].getCurrentPriceOfBuilding()) {
                    placeTwoEvenHouses(guiField1, propertyField1, propertyField2, properties);
                    chooseAgain = true;
                }
                if (secondButton.equals(gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK"
                ) && player.getAccount().getMoney() > properties[propertyField2].getCurrentPriceOfBuilding()) {
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

        int buildingField1 = properties[propertyField1].getCurrentPriceOfBuilding();
        int buildingField2 = properties[propertyField2].getCurrentPriceOfBuilding();
        int playerBal = player.getAccount().getMoney();

        if(playerBal < buildingField1 || playerBal < buildingField2){
            gui.getInstance().getUserButtonPressed("Du har ikke råd til at bygge her", "Okay");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }


    }

    public void caseThreeColorsChosen(Player player, Property[] properties, int guiField1, int guiField2,
                                      int guiField3, int propertyField1, int propertyField2, int propertyField3, String colorString) {

        if (player == properties[propertyField1].getOwner() && player == properties[propertyField1].getOwner()
        && player == properties[propertyField3].getOwner()) {

            String secondButton = gui.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                    gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                            + " DKK", gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                            + " DKK", gui.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                            + " DKK", "ingen, jeg vil ikke købe i " + colorString);

            if (secondButton.equals(gui.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField1].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
                placeThreeEvenHouses(guiField1, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(gui.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField2].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
                placeThreeEvenHouses(guiField2, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(gui.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField3].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
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
        int buildingField1 = properties[propertyField1].getCurrentPriceOfBuilding();
        int buildingField2 = properties[propertyField2].getCurrentPriceOfBuilding();
        int buildingField3 = properties[propertyField3].getCurrentPriceOfBuilding();
        int playerBal = player.getAccount().getMoney();

        if(playerBal < buildingField1 || playerBal < buildingField2 || playerBal < buildingField3){
            gui.getInstance().getUserButtonPressed("Du har ikke råd til at bygge her", "Okay");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        gameBoard.createPropertiesPrices();

        Player[] players = new Player[2];
        players[0] = new Player();
        players[0].setName("tester");
        players[0].getAccount().setMoney(-25000);

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(blue);

        GUI_Player[] gui_players = new GUI_Player[2];
        gui_players[0] = new GUI_Player(players[0].getName(), players[0].getAccount().getMoney(), car);

        players[1] = new Player();
        players[1].setName("hussein");
        GUI_Car car1 = new GUI_Car();
        gui_players[1] = new GUI_Player(players[1].getName(), players[1].getAccount().getMoney(), car1);

        GUI_Controller gui = new GUI_Controller();

        gui.getInstance().addPlayer(gui_players[0]);
        gui.getInstance().addPlayer(gui_players[1]);

        //Moving player to a blue property and choosing to buy
        players[0].moveToHere(1);
        gui.getSpecificField(players[0].getSquare()).setCar(gui_players[0], true);


        gameBoard.getProperty(players[0]).landOnProperty(players[0], gui_players[0], gameBoard.getProperties(), players, gui_players);

        System.out.println("Testing: " + players[0].getName());
        //Creating a new player and makes the new player land on the now owned blue property

    }
}