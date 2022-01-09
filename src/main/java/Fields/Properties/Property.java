package Fields.Properties;

import Fields.GameBoard;
import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.stream.IntStream;

import static java.awt.Color.blue;
import static java.awt.Color.red;

public class Property {
    private GUI_Controller gui = new GUI_Controller();
    private Player owner;
    private GUI_Player guiOwner;
    private int[] propertyFieldNumbers = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 23, 34, 37, 39};

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player

    protected int rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice;
    protected boolean allBlueOwned, isOwned;

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

        if(!allBlueOwned) {
            switch (player.getBlueOwned()) {
                case 1 -> currentRentPrice = rentOneOwned;
                case 2 -> {
                    currentRentPrice = rentAllOwned;
                    for (int i = 0; i < properties.length; i++) {

                        if (player == properties[i].getOwner()) {
                            properties[i].setCurrentRentPrice(rentAllOwned);
                        }
                    }
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

    public static void main(String[] args) {
        //Testing out landOnProperty method

        GameBoard gameBoard = new GameBoard();

        gameBoard.createPropertiesPrices();

        Player player = new Player();
        player.setName("Hussein");

        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(blue);
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getAccount().getMoney(), car);

        GUI_Controller gui = new GUI_Controller();

        gui.getInstance().addPlayer(gui_player);

        //Moving player to a blue property and choosing to buy
        player.moveToHere(1);
        gui.getSpecificField(player.getSquare()).setCar(gui_player, true);

        gameBoard.getProperty(player).landOnProperty(player, gui_player, gameBoard.getProperties());

        //Creating a new player and makes the new player land on the now owned blue property
        Player player1 = new Player();
        player1.setName("Tester1");

        GUI_Car car1 = new GUI_Car();
        car1.setPrimaryColor(red);

        GUI_Player gui_player1 = new GUI_Player(player1.getName(), player1.getAccount().getMoney(), car1);
        gui.getInstance().addPlayer(gui_player1);

        //Now moving the new player
        player1.moveToHere(1);
        gui.getSpecificField(player.getSquare()).setCar(gui_player1, true);

        gameBoard.getProperty(player1).landOnProperty(player1, gui_player1, gameBoard.getProperties());
    }
}