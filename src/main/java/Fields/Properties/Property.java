package Fields.Properties;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

import static java.awt.Color.blue;

/** We made the class called Property to give the players the ability to buy properties and build houses there.
 * It is an ownable field that has various methods such as paying the owner and setting the fields on auction.
 * It has a constructor in which when it is initiated, each field must be initiated by itself with own values
 * of rent on various occasions. Also, it has the following attributes: Cost of eventual upgrades and the
 * rent associated with that. If a set of one color is owned by one player, the rent price gets higher.
 * Rent price based on the number of buildings, building prices and finally the field price.*/

public class Property {
    private Player owner, theOneWhoAuctioned, highestBidder;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
    private int currentBid;

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player
    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice, amountOfHouses,
            currentPriceOfBuilding, guiFieldNumber;
    private static int intHelper;

    protected static boolean allBlueOwned, allOrangeOwned, allDarkYellowOwned, allGreyOwned, allRedOwned,
            allWhiteOwned, allBrightYellowOwned, allPurpleOwned;

    private boolean isOwned, chooseToBuildAgain, chooseAgain, bidAgain, justBought;

    /**For each time a property field is initiated, it will need to start with setting all this info below*/
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


    /** The method below takes in different forms of parameters, it takes in player,
     * and it uses the property array, which is initiated in class GameBoard.
     * As an important parameter, the string color will be told.
     * So, whenever this method is used, it must tell which color is bought,
     * so that the code decides in the switch,
     * which number of specific colors are owned by that player. */
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


    /** This works as setCurrentRentPriceIfOwningTwo, just with three cases because orange are for
     * example three fields and not two*/
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

    /**This returns the current price of the building, if the player reaches hotels, the
     * price has to obviously pay for the hotel price when setting it
     */
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

        checkWhichPropertyField(player.getSquare());

        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && !isOwned && player != owner
        && !properties[intHelper].getIsOwned()){
            optionBuyProperty(player, gui_player, properties, players, gui_players);
        }
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    /**it asks if the player has enough money to be able to press
     * “ja” (yes) to buy the field, if the player presses the button, the player
     * pays for the field. If the player does not have enough money, the field is auctioned
     * . If the player simply does not want to buy, the method setPropertyOnAuction is used:*/
    private void optionBuyProperty(Player player, GUI_Player gui_player, Property[] properties, Player[] players, GUI_Player[] gui_players) {

        String buttonPressed = null;

        if (player.getAccount().getMoney() > fieldPrice && player != owner) {
            buttonPressed = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                            GUI_Controller.getSpecificField(player.getSquare()).getTitle() + ", vil du købe denne for " + fieldPrice + " DKK?", "Ja",
                    "Nej, sæt feltet på auktion");
        }

        if (player.getAccount().getMoney() < fieldPrice) {
            buttonPressed = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " +
                            GUI_Controller.getSpecificField(player.getSquare()).getTitle() + ", den koster " + fieldPrice + " DKK, men du har" +
                            " ikke råd. Du bliver nødt til at sætte den på auktion",
                    "Sæt feltet på auktion");
        }

            if (buttonPressed.equals("Ja")) {
                this.guiOwner = gui_player;
                this.owner = player;
                isOwned = true;

                owner.getAccount().setMoney(-fieldPrice);
                guiOwner.setBalance(owner.getAccount().getMoney());

                GUI_Controller.getSpecificField(owner.getSquare()).setSubText(owner.getName());
                GUI_Controller.getGameBoard().getGuiStreet(owner.getSquare()).setBorder(guiOwner.getCar().getPrimaryColor());

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

        /**This method starts to see which amount is the richest amount out of players balances,
         * in a for loop. If the player is not in the game
         * (the player has gone bankrupt and is out of the game), they can of course not bid in the auction.
         * The method then starts a while loop; while the field is not bought yet.
         * It then gives a player in the for loop a chance to bid the current amount of bid,
         * then increases it with the banknotes that are in the real-life matador game, so it resembles it.
         * If the player presses that they do not wish to bid, they are out of auction and
         * amount of isOut increases by one. If there is all except one out of the auction here, the last standing
         *  bidder will be given the field for their currentBid price*/
    public void setPropertyOnAuction(Player player, Player[] players, Property[] properties, GUI_Player[] gui_players){
        theOneWhoAuctioned = player;
        GUI_Controller.getInstance().showMessage("Feltet " + GUI_Controller.getSpecificField(player.getSquare()).getTitle() + " er sat på auktion" +
                ", andre spillere kan nu byde på denne");

        Player[] isOutOfAuction = new Player[players.length];
        Player highestBidder = null;

        boolean bought = false;
        int isOut = 0;

        int richestAmount = 0;
        for (int i = 0; i < players.length; i++) {

            if(players[i].getAccount().getMoney() > richestAmount){
                richestAmount = players[i].getAccount().getMoney();
            }
        }

        for (int i = 0; i < players.length; i++) {
            if(players[i].getPlayerOutOfGame()){
                isOutOfAuction[i] = players[i];
            }
        }

        while(!bought) {

            for (int i = 0; i < players.length; i++) {

                bidAgain = true;

                if (players[i] != isOutOfAuction[i] && !bought
                        && players[i].getAccount().getMoney() > (currentBid + 101) && !players[i].getPlayerOutOfGame()) {
                    while (bidAgain && players[i].getAccount().getMoney() > (currentBid + 50) && players[i] != isOutOfAuction[i]
                            && currentBid < richestAmount ) {
                        bidAgain = false;
                        String bid = GUI_Controller.getInstance().getUserButtonPressed(players[i].getName() + ", du kan byde på feltet "
                                        + GUI_Controller.getSpecificField(player.getSquare()).getTitle() + ". (Oprindelig pris: " + properties[intHelper].getFieldPrice()
                                        + "). Det nuværende bud er " + currentBid + ". Hvad vil du byde med mere",
                                "50", "100", "500", "1000", "2000", "5000", "Ønsker ikke at byde");

                        switch (bid) {
                            case "50", "100", "500", "1000", "2000", "5000" -> placeBit(player, Integer.parseInt(bid) + currentBid);
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

            if(currentBid >= richestAmount - 101){

                for (int i = 0; i < players.length; i++) {
                    if(players[i] == highestBidder) {

                        checkGuiFieldNumberFromPropertyNumber(intHelper);
                        GUI_Controller.getInstance().showMessage("Tillykke " + highestBidder.getName() + ", du har købt feltet " +
                                GUI_Controller.getSpecificField(guiFieldNumber).getTitle() + " for " + currentBid);

                        properties[intHelper].boughtFieldFromAuction(gui_players[i], players[i], properties);
                    }
                }
            }

            if (isOut == (isOutOfAuction.length - 1)) {

                for (int i = 0; i < players.length; i++) {

                    if (players[i] != isOutOfAuction[i]) {
                        checkGuiFieldNumberFromPropertyNumber(intHelper);
                        GUI_Controller.getInstance().showMessage("Tillykke " + players[i].getName() + ", du har købt feltet " +
                                GUI_Controller.getSpecificField(guiFieldNumber).getTitle() + " for " + currentBid);
                        properties[intHelper].boughtFieldFromAuction(gui_players[i], players[i], properties);
                    }
                }
            }
        }
    }

    /**If the player has enough money, the player is now the highest bidder. If the player does not have enough money,
     * they will be asked to bid again*/
    private void placeBit(Player player, int bid){

        if(bid >= player.getAccount().getMoney()){
            GUI_Controller.getInstance().showMessage(player.getName() + ", du kan ikke så højere end det du har, byd en anden værdi");
        }else{
            currentBid = bid;
            highestBidder = player;
        }
    }

    public void setJustBought(boolean trueOrFalse){
        justBought = trueOrFalse;
    }

    public boolean setIsOwned(boolean isOwned) {
        return isOwned = isOwned;
    }

    public boolean getJustBought(){
        return justBought;
    }

    /** This functions just as when pressing "ja" (yes) in optionBuyField, this should just subtract the bid instead of the
     * field price.*/
    public void boughtFieldFromAuction(GUI_Player gui_player, Player player, Property[] properties){

        this.isOwned = true;
        this.guiOwner = gui_player;
        this.owner = player;

        player.getAccount().setMoney(-currentBid);
        gui_player.setBalance(player.getAccount().getMoney());

        checkGuiFieldNumberFromPropertyNumber(intHelper);
        GUI_Controller.getSpecificField(guiFieldNumber).setSubText(player.getName());
        GUI_Controller.getGameBoard().getGuiStreet(guiFieldNumber).setBorder(gui_player.getCar().getPrimaryColor());

        properties[intHelper].setJustBought(true);

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

    /**If the place is owned, the lander has to pay the owner*/
    public void payOwner(Player player, GUI_Player gui_player) {

        if (IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && isOwned
                && player != owner) {
            GUI_Controller.getInstance().getUserButtonPressed("Oh oh, " + player.getName() + ", du er landet på " + owner.getName() +
                    "'s felt: " + GUI_Controller.getSpecificField(player.getSquare()).getTitle() +
                    ", du skal af med " + currentRentPrice + " DKK", "Betal");

            player.getAccount().setMoney(-currentRentPrice);
            gui_player.setBalance(player.getAccount().getMoney());

            owner.getAccount().setMoney(currentRentPrice);

            guiOwner.setBalance(owner.getAccount().getMoney());
        }
    }

    public int getAmountOfHouses() {
        return amountOfHouses;
    }

    public void setPlusOneAmountOfHouses() {
        this.amountOfHouses += 1;
    }

   /** This method places a house somewhere on the board itself (gui), increases the amount of
    * houses, and sets the fields price to the specific price for that field*/
    public void placeHouse(int fieldNumber, Property[] properties){

        owner.getAccount().setMoney(-properties[intHelper].getCurrentPriceOfBuilding());
        guiOwner.setBalance(owner.getAccount().getMoney());

        properties[intHelper].setPlusOneAmountOfHouses();

        switch (properties[intHelper].getAmountOfHouses()){

            case 1 ->{properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentOneHouse());
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 2 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentTwoHouse());
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 3 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentThreeHouse());
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 4 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentFourHouse());
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHouses(properties[intHelper].getAmountOfHouses());}

            case 5 -> {properties[intHelper].setCurrentRentPrice(properties[intHelper].getRentHotel());
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHouses(0);
                GUI_Controller.getGameBoard().getGuiStreet(fieldNumber).setHotel(true);}
        }
    }

    /** There is a test on this called placeThreeHouses or placeTwoHouses. It makes the player not able to
     * buy uneven amount of houses in one place rather than the other on the same color*/
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
            GUI_Controller.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        else if ((intHelper == fieldTwo) && !firstTrue && !secondTrue &&
                (properties[fieldTwo].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1))
                && properties[intHelper].getAmountOfHouses() != 5) {
            GUI_Controller.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        if(properties[intHelper].getAmountOfHouses() == 5){
            GUI_Controller.getInstance().showMessage("Du kan ikke købe mere på her! Du har hotel");
            chooseToBuildAgain = true;
            chooseAgain = true;
        }
    }

    /**There is a test on this called placeThreeHouses or placeTwoHouses. It makes the player not able to
     * buy uneven amount of houses in one place rather than the other on the same color*/
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
            GUI_Controller.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        else if ((intHelper == fieldTwo) && !firstTrue && !secondTrue && !thirdTrue &&
                (properties[fieldTwo].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1)
                        || properties[fieldTwo].getAmountOfHouses() >= (properties[fieldThree].getAmountOfHouses() + 1))
        && properties[intHelper].getAmountOfHouses() != 5) {
            GUI_Controller.getInstance().showMessage("Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;


        } else if ((intHelper == fieldThree) && !thirdTrue && !firstTrue && !secondTrue &&
                (properties[fieldThree].getAmountOfHouses() >= (properties[fieldTwo].getAmountOfHouses() + 1)
                        || properties[fieldThree].getAmountOfHouses() >= (properties[fieldOne].getAmountOfHouses() + 1))
        && properties[intHelper].getAmountOfHouses() != 5) {
            GUI_Controller.getInstance().showMessage("3333 Du har for mange bygninger her, vælg et andet sted at bygge");
            chooseToBuildAgain = true;
        }

        if(properties[intHelper].getAmountOfHouses() == 5){
            GUI_Controller.getInstance().showMessage("Du kan ikke købe mere på her! Du har hotel");
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

    /**If the player is out of the game, the property has to be reset so that other players can buy it*/
    public void resetProperty(int propertyNumber){

        checkGuiFieldNumberFromPropertyNumber(propertyNumber);

        int price = Integer.parseInt(GUI_Controller.getGameBoard().getGuiStreet(guiFieldNumber).getRent());

        GUI_Controller.getSpecificField(guiFieldNumber).setSubText("Pris: " + price);

        this.owner = null;
        this.guiOwner = null;
        this.isOwned = false;
        this.currentRentPrice = rentOneOwned;

        GUI_Controller.getGameBoard().getGuiStreet(guiFieldNumber).setHouses(0);
        GUI_Controller.getGameBoard().getGuiStreet(guiFieldNumber).setHotel(false);
    }


    /**If the player owns a set of fields, this method will ask the player if they want to build somewhere on any color they own
     *  a set of*/
    public void optionsWhenOwningAllFields(Property[] properties, Player player){

        chooseAgain = true;

        while(chooseAgain) {
            chooseAgain = false;

            String colorPressed = GUI_Controller.getInstance().getUserButtonPressed(player.getName() + ", vælg hvor du vil bygge", "Blå",
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

    /** This method checks if the player owns the two fields they want to build on. If yes
     * , the fields name get shown as buttons, if no, the player gets to choose another color*/
    public void caseTwoColorsChosen(Player player, Property[] properties, int guiField1, int guiField2, int propertyField1,
                                    int propertyField2, String colorString){

        if(player == properties[propertyField1].getOwner() && player == properties[propertyField2].getOwner()){

                chooseToBuildAgain = false;

                String secondButton = GUI_Controller.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                        GUI_Controller.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK",
                        GUI_Controller.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK",
                        "ingen, jeg vil ikke bygge i " + colorString);

                if (secondButton.equals(GUI_Controller.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding() + " DKK"
                ) && player.getAccount().getMoney() > properties[propertyField1].getCurrentPriceOfBuilding()) {
                    placeTwoEvenHouses(guiField1, propertyField1, propertyField2, properties);
                    chooseAgain = true;
                }
                if (secondButton.equals(GUI_Controller.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding() + " DKK"
                ) && player.getAccount().getMoney() > properties[propertyField2].getCurrentPriceOfBuilding()) {
                    placeTwoEvenHouses(guiField2, propertyField1, propertyField2, properties);
                    chooseAgain = true;
                }
                if (secondButton.equals("ingen, jeg vil ikke bygge i " + colorString)) {
                    chooseAgain = true;
                    chooseToBuildAgain = false;
            }

        }else if(player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()){
            GUI_Controller.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden farve");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }

        int buildingField1 = properties[propertyField1].getCurrentPriceOfBuilding();
        int buildingField2 = properties[propertyField2].getCurrentPriceOfBuilding();
        int playerBal = player.getAccount().getMoney();

        if(playerBal < buildingField1 || playerBal < buildingField2){
            GUI_Controller.getInstance().getUserButtonPressed("Du har ikke råd til at bygge her", "Okay");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
    }

    /** This method checks if the player owns the three fields they want to build on. If yes
     * , the fields name get shown as buttons, if no, the player gets to choose another color*/
    public void caseThreeColorsChosen(Player player, Property[] properties, int guiField1, int guiField2,
                                      int guiField3, int propertyField1, int propertyField2, int propertyField3, String colorString) {

        if (player == properties[propertyField1].getOwner() && player == properties[propertyField1].getOwner()
        && player == properties[propertyField3].getOwner()) {

            String secondButton = GUI_Controller.getInstance().getUserButtonPressed("Vælg hvilket felt du ønsker at bygge på",
                    GUI_Controller.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                            + " DKK", GUI_Controller.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                            + " DKK", GUI_Controller.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                            + " DKK", "ingen, jeg vil ikke købe i " + colorString);

            if (secondButton.equals(GUI_Controller.getSpecificField(guiField1).getTitle() + " " + properties[propertyField1].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField1].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
                placeThreeEvenHouses(guiField1, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(GUI_Controller.getSpecificField(guiField2).getTitle() + " " + properties[propertyField2].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField2].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
                placeThreeEvenHouses(guiField2, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals(GUI_Controller.getSpecificField(guiField3).getTitle() + " " + properties[propertyField3].getCurrentPriceOfBuilding()
                    + " DKK") && properties[propertyField3].getCurrentPriceOfBuilding() < player.getAccount().getMoney()) {
                placeThreeEvenHouses(guiField3, propertyField1, propertyField2, propertyField3, properties);
                chooseAgain = true;

            } else if (secondButton.equals("ingen, jeg vil ikke købe i " + colorString)) {
                chooseAgain = true;
                chooseToBuildAgain = false;
            }

        } else if (player != properties[propertyField1].getOwner() || player != properties[propertyField2].getOwner()
                || player != properties[propertyField3].getOwner()) {
            GUI_Controller.getInstance().showMessage("Du ejer ikke alle i " + colorString + ", vælg en anden");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
        int buildingField1 = properties[propertyField1].getCurrentPriceOfBuilding();
        int buildingField2 = properties[propertyField2].getCurrentPriceOfBuilding();
        int buildingField3 = properties[propertyField3].getCurrentPriceOfBuilding();
        int playerBal = player.getAccount().getMoney();

        if(playerBal < buildingField1 || playerBal < buildingField2 || playerBal < buildingField3){
            GUI_Controller.getInstance().getUserButtonPressed("Du har ikke råd til at bygge her", "Okay");
            chooseAgain = true;
            chooseToBuildAgain = false;
        }
    }
}