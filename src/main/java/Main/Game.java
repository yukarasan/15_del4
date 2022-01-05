package Main;

import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;

public class Game {
    private GUI_Controller gui = new GUI_Controller();
    private int numberOfPlayers;
    private Player[] player = new Player[numberOfPlayers];

    public void startGame() {
        gui.getInstance();
        welcomingPlayer();
        createPlayer();
        round();
    }

    private void welcomingPlayer() {
        gui.getInstance().showMessage("Velkommen til Matador!");
    }

    public boolean playerNamesAreSame(String name, int playerNumberYet){
        boolean sameName = false;
        for (int i = 0; i < playerNumberYet; i++) {
            if (!name.equals(player[i].getName())){
                sameName = false;
            }
        }
        for (int i = 0; i < playerNumberYet; i++) {
            if (name.equals(player[i].getName())) {
                sameName = true;
            }
        }
        return sameName;
    }

    private void createPlayer() {
        String buttonPressed = gui.getInstance().getUserButtonPressed("Hvor mange spillere ønsker I at være?", "3", "4",
                "5", "6");

        int option = Integer.parseInt(buttonPressed);
        Player[] player = new Player[option];

        GUI_Player[] gui_player = new GUI_Player[option];
        GUI_Car[] car = new GUI_Car[option];

        boolean blueTaken = false, blackTaken = false, whiteTaken = false, redTaken = false, yellowTaken = false, greenTaken = false;
        boolean chooseColorAgain = true;

        for (int i = 0; i < option; i++) {
            chooseColorAgain = true;

            player[i] = new Player();
            car[i] = new GUI_Car();


            String name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", indtast dit navn: ");

            if(i == 0){
                player[i].setName(name);
            }

            if(i > 0){
                while(playerNamesAreSame(name, i) == true) {
                    gui.getInstance().showMessage("Spiller " + (i+1) + ", navnet er allerede taget, skriv et nyt");
                    String newName = gui.getInstance().getUserString("Spiller " + (i+1) + ", Indtast et navn");
                    player[i].setName(newName);
                }
            }

            while (chooseColorAgain) {

                chooseColorAgain = false;
                String color = gui.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

                switch (color) {

                    case "Blå":
                        if (blueTaken) {
                            gui.getInstance().showMessage("Blå er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.blue);
                            blueTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Sort":
                        if (blackTaken) {
                            gui.getInstance().showMessage("Sort er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.black);
                            blackTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Hvid":
                        if (whiteTaken) {
                            gui.getInstance().showMessage("Hvid er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.white);
                            whiteTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Gul":
                        if (yellowTaken) {
                            gui.getInstance().showMessage("Gul er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.yellow);
                            yellowTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Rød":
                        if (redTaken) {
                            gui.getInstance().showMessage("Rød er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.red);
                            redTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Grøn":
                        if (greenTaken) {
                            gui.getInstance().showMessage("Grøn er allerede taget");
                            chooseColorAgain = true;
                            break;
                        } else {
                            car[i].setPrimaryColor(Color.green);
                            greenTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                }
            }

            gui_player[i] = new GUI_Player(player[i].getName(),player[i].getAccount().getMoney(), car[i]);

            gui.getInstance().addPlayer(gui_player[i]);

            gui.getSpecificField(0).setCar(gui_player[i], true);

        }


    }

    private void round() {
        DiceCup diceCup = new DiceCup();
        gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());
    }


}
