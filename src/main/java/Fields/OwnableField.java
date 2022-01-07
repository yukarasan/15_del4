package Fields;

import GUI_Controllor.GUI_Controller;
import Main.Player;
import gui_fields.*;

import java.awt.*;
import java.util.stream.IntStream;

public class OwnableField extends Field{
    protected Player owner;
    private Ferry[] ferries = new Ferry[4];
    private int[] ferryFields = {5, 15, 25, 35};
    private int intHelper;



    //Below is the rent on various occasions of ownable fields such as properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned
    private int rentNoHouse, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
    rentHotel, fieldPrice, costOfOneHouse, costOfHotel;

    public void setFields(GUI_Field[] fields, int fieldNumber, int price, Color color, String name) {
        GUI_Street field = new GUI_Street();
        field.setDescription(" ");
        field.setRent(String.valueOf(price));
        field.setSubText("Pris: " + price);
        field.setBackGroundColor(color);
        field.setTitle(name);
        fields[fieldNumber] = field;
    }

    public void setFerryFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName) {
        field[fieldNumber] = new GUI_Shipping();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setSubText("");
    }

    public void setBrewerFields(GUI_Field[] field, int fieldNumber, Color color, String name, String subName) {
        field[fieldNumber] = new GUI_Brewery();
        field[fieldNumber].setBackGroundColor(color);
        field[fieldNumber].setTitle(name);
        field[fieldNumber].setDescription(subName);
        field[fieldNumber].setSubText("");
    }

    public void checkIfLandedFerryField(Player player){
        intHelper = 0;
        switch (player.getSquare()) {
            case 5 -> intHelper = 0;
            case 15 -> intHelper = 1;
            case 25 -> intHelper = 2;
            case 35 -> intHelper = 3;
        }
}

    public void initializeFerries(){
        for(int i=0; i<ferries.length; i++){
            ferries[i] = new Ferry();
        }
    }

    public void buyFerry(Player player, GUI_Player gui_player) {

       checkIfLandedFerryField(player);

        if(IntStream.of(ferryFields).anyMatch(x -> x == player.getSquare()) && !ferries[intHelper].getIsFerryOwned()){

            gui.getInstance().getUserButtonPressed(player.getName() + ", du er landet på " + gui.getSpecificField(player.getSquare()).getTitle() +
                    ", vil du købe den for 4000 DKK?", "Ja", "Nej");

            gui.getSpecificField(player.getSquare()).setSubText(player.getName());


            switch (player.getSquare()) {
                case 5:
                    ferries[0].setOwner(player, gui_player);
                    break;
                case 15:
                    ferries[1].setOwner(player, gui_player);
                    break;
                case 25:
                    ferries[2].setOwner(player, gui_player);
                    break;
                case 35:
                    ferries[3].setOwner(player, gui_player);
                    break;
            }
        }
    }

    public void payOwnerOfFerry(Player player, GUI_Player gui_player) {

        checkIfLandedFerryField(player);

        if (true) {

            gui.getInstance().getUserButtonPressed("Og oh.. " + player.getName() + ", du har landet på " +
                    ferries[intHelper].owner.getName() + "'s færge: " + gui.getSpecificField(player.getSquare()).getTitle() +
            ". Du skal betale " + ferries[intHelper].getRentPrice() + " DKK");

            player.getAccount().setMoney(-ferries[intHelper].getRentPrice());
            ferries[intHelper].getOwner().getAccount().setMoney(ferries[intHelper].getRentPrice());

        }
    }

    public void setUpOwnableField(int rentNoHouse, int rentAllOwned, int rentOneHouse, int rentTwoHouse, int rentThreeHouse,
                                  int rentFourHouse, int rentHotel, int fieldPrice, int fieldNumber,
                                  int costOfOneHouse, int costOfHotel, GUI_Controller gui){
        this.costOfHotel = costOfHotel;
        this.costOfOneHouse = costOfOneHouse;
        gui.getSpecificField(fieldNumber);
    }


    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
