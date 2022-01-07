package Fields;

import Main.Player;

public class Ferry extends OwnableField {
    Ferry ferry = new Ferry();
    int rentPrice, buyPrice;

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;

        owner.setFerriesOwned(1);

        switch (owner.getFerriesOwned()) {
            case 1 -> rentPrice = 500;
            case 2 -> rentPrice = 1000;
            case 3 -> rentPrice = 2000;
            case 4 -> rentPrice = 4000;
        }
    }
}
