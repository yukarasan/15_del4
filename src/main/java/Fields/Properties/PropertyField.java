package Fields.Properties;

public class PropertyField extends Property{

    /**This field is only made to keep it developer friendly when creating new properties,
     * it only implements the constructor by its parent; Property*/

    public PropertyField(int rentOneOwned, int rentAllOwned, int rentOneHouse, int rentTwoHouse,
                         int rentThreeHouse, int rentFourHouse, int rentHotel, int fieldPrice,
                         int costOfOneHouse, int costOfHotel){

        super(rentOneOwned, rentAllOwned, rentOneHouse, rentTwoHouse, rentThreeHouse,
                rentFourHouse, rentHotel, fieldPrice, costOfOneHouse, costOfHotel);
    }
}