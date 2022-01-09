package Fields.Properties;

public class PropertyField extends Property{
    private boolean allOwned;

    public PropertyField(int rentOneOwned, int rentAllOwned, int rentOneHouse, int rentTwoHouse,
                         int rentThreeHouse, int rentFourHouse, int rentHotel, int fieldPrice,
                         int costOfOneHouse, int costOfHotel){

        super(rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse,
                rentFourHouse, rentHotel, fieldPrice, costOfOneHouse, costOfHotel);

        super.rentOneOwned = rentOneOwned;
        super.rentAllOwned = rentAllOwned;
        super.rentOneHouse = rentOneHouse;
        super.rentTwoHouse = rentTwoHouse;
        super.rentThreeHouse = rentThreeHouse;
        super.rentFourHouse = rentFourHouse;
        super.rentHotel = rentHotel;
        super.fieldPrice = fieldPrice;
        super.costOfOneHouse = costOfOneHouse;
        super.costOfHotel = costOfHotel;
    }
}