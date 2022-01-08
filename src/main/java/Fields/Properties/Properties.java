package Fields.Properties;

public class Properties {

    //Below is the rent on various occasions of ownable fields that'll extend Properties.
    //Also below is cost of eventual upgrades and the rent if a set of one color are owned by one player

    protected int rentNoHouse, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse, rentFourHouse,
            rentHotel, fieldPrice, costOfOneHouse, costOfHotel;

    //For each time a
    Properties(int rentNoHouse, int rentAllOwned, int  rentOneHouse, int  rentTwoHouse,
               int  rentThreeHouse, int  rentFourHouse, int rentHotel, int fieldPrice,
               int costOfOneHouse, int  costOfHotel){

        this.rentNoHouse = rentNoHouse;

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
}
