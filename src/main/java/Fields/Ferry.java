package Fields;

import Main.Player;
import gui_fields.GUI_Player;

public class Ferry extends OwnableField {
    private int rentPrice, buyPrice;
    private boolean isOwned;
    private Player owner;
    private GUI_Player guiOwner;

    public void setOwner(Player owner, GUI_Player gui_player) {
        this.owner = owner;
        this.guiOwner = gui_player;

        owner.getAccount().setMoney(-4000);
        gui_player.setBalance(owner.getAccount().getMoney());

        owner.setFerriesOwned(1);

        switch (owner.getFerriesOwned()) {
            case 1 -> rentPrice = 500;
            case 2 -> rentPrice = 1000;
            case 3 -> rentPrice = 2000;
            case 4 -> rentPrice = 4000;
        }
        isOwned = true;
    }

    public boolean getIsFerryOwned() {
        return isOwned;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public GUI_Player getGuiOwner() {
        return guiOwner;
    }
}
