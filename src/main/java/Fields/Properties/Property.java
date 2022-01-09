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

    protected int rentNoHouse, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel, currentRentPrice;
    private boolean allOwned, isOwned;

    //For each time a property field is initiated, it will need to start with setting all this above info
    protected Property(int rentNoHouse, int rentAllOwned, int rentOneHouse, int rentTwoHouse,
                       int rentThreeHouse, int rentFourHouse, int rentHotel, int fieldPrice,
                       int costOfOneHouse, int costOfHotel){

        this.rentNoHouse = rentNoHouse;
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

    public void setAllOwned(boolean allOwned) {
        this.allOwned = allOwned;
    }

    public boolean getAllOwned(){
        return  allOwned;
    }

    public int getRentNoHouse() {
        return rentNoHouse;
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

    public void landOnProperty(Player player, GUI_Player gui_player){
        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && !isOwned){
            optionBuyProperty(player, gui_player);
        }

        if(IntStream.of(propertyFieldNumbers).anyMatch(x -> x == player.getSquare()) && isOwned && player != owner){
            payOwner(player, gui_player);
        }
    }


    public void optionBuyProperty(Player player, GUI_Player gui_player){

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
        }
    }

    public void payOwner(Player player, GUI_Player gui_player){

        gui.getInstance().getUserButtonPressed("Oh oh, " + player.getName() + ", du er landet på " + owner.getName() +
                "'s felt: " + gui.getSpecificField(player.getSquare()).getTitle() +
                ", du skal af med " + currentRentPrice + "DKK", "Betal");

        player.getAccount().setMoney(-currentRentPrice);
        gui_player.setBalance(player.getAccount().getMoney());

        owner.getAccount().setMoney(currentRentPrice);

        guiOwner.setBalance(owner.getAccount().getMoney());
    }
}