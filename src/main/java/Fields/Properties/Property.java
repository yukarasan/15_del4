package Fields.Properties;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Player;
import java.util.stream.IntStream;

public class Property {
    private GUI_Controller gui = new GUI_Controller();
    private Player owner;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 23, 34, 37, 39};

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player

    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice;
    protected boolean isOwned, allBlueOwned, allOrangeOwned, allDarkYellowOwned, allGreyOwned;

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
                    properties[5].setCurrentRentPrice(rentAllOwned);
                    properties[6].setCurrentRentPrice(rentAllOwned);
                    properties[7].setCurrentRentPrice(properties[7].getRentAllOwned());
                    allOrangeOwned = true;
                }
            }
        }

        if(!allGreyOwned && currentRentPrice != rentAllOwned) {
            switch (player.getGreyOwned()) {
                case 1, 2 -> currentRentPrice = rentOneOwned;
                case 3 -> {
                    currentRentPrice = rentAllOwned;
                    properties[8].setCurrentRentPrice(rentAllOwned);
                    properties[9].setCurrentRentPrice(rentAllOwned);
                    properties[10].setCurrentRentPrice(properties[7].getRentAllOwned());
                    allOrangeOwned = true;
                }
            }
        }


    }

    public void setCurrentPriceDarkYellow(){

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
}