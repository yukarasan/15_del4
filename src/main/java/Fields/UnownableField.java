package Fields;

import Main.Player;

import gui_fields.*;

public class UnownableField extends Field {

    public void startField(GUI_Field[] fields) {
        fields[0] = new GUI_Start();
        fields[0].setTitle("Start");
        fields[0].setSubText("Modtag 4000");
        fields[0].setDescription("Dette er startfeltet");
    }

    public void jailField(GUI_Field[] fields) {
        fields[10] = new GUI_Jail();
        fields[30] = new GUI_Jail();
    }

    public void freeParking(GUI_Field[] fields) {
        fields[20] = new GUI_Refuge();
    }

    public void chanceField(GUI_Field[] fields) {
        fields[2] = new GUI_Chance();
        fields[7] = new GUI_Chance();
        fields[16] = new GUI_Chance();
        fields[22] = new GUI_Chance();
        fields[33] = new GUI_Chance();
        fields[36] = new GUI_Chance();
    }

    public void card1(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Betal 200 DKK af levering af 2 kasser øl");
        player.getAccount().setMoney(-200);
    }

    public void card2(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Betal din bilforsikring for 1000 DKK");
        player.getAccount().setMoney(-1000);
    }

    public void card3(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Du modtager din aktieudbytte.Modtag 1000 DKK af banken");
        player.getAccount().setMoney(1000);
    }

    public void card4(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Du har solgt nogle gamle møbler.Modtag 1000 ");
        player.getAccount().setMoney(1000);
    }

    public void card5(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Ryk frem til Frederiksberg Allé.Hvis du passere start så modtag 4000 DKK");
        gui.getSpecificField(11);
        if (player.getSquare() == 0) {
            player.getAccount().setMoney(4000);
        }
    }

    public void card6(GUI_Player gui_player, Player player) {
        gui.getInstance().showMessage("Gå i fængsel.Du modtager ikke 4000 for at passere start");
        gui.getSpecificField(30);
        if (player.getSquare() ==0) {
            player.getAccount().setMoney(0);
        }
    }


}

