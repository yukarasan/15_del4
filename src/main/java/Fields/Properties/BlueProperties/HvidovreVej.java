package Fields.Properties.BlueProperties;

import Fields.Properties.Property;

public class HvidovreVej extends Property{
    private boolean allOwned;

    public HvidovreVej(int rentOneOwned, int rentAllOwned, int rentOneHouse, int rentTwoHouse,
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

    public static void main(String[] args) {

        HvidovreVej hvidovreVej = new HvidovreVej(1,2,3,4,
                5, 6, 7, 8, 9, 10);

        RoedovreVej roedovreVej = new RoedovreVej(12, 13, 14, 15,
                16, 17, 18, 19, 20, 21);

    }

}
