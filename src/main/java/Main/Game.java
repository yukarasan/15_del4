package Main;

import GUI_Controllor.GUI_Controller;
import gui_fields.GUI_Player;

import java.awt.*;

public class Game {
    GUI_Controller gui = new GUI_Controller();
    private GUI_Player player[];

    public void startGame() {
        gui.getInstance();
        welcomingPlayer();
        createPlayer();
        round();
    }

    private void welcomingPlayer() {
        gui.getInstance().showMessage("Velkommen til Matador!");
    }

    private void createPlayer() {
        String buttonPressed = gui.getInstance().getUserButtonPressed("Hvor mange spillere ønsker I at være?", "3", "4",
                "5", "6");

        int option = Integer.parseInt(buttonPressed);
        Player[] player = new Player[option];

        GUI_Player[] gui_player = new GUI_Player[option];
        Car[] car = new Car[option];

        boolean blueTaken = false, blackTaken = false, whiteTaken = false, redTaken = false, yellowTaken = false, greenTaken = false;
        boolean chooseColorAgain = true;

        for (int i = 0; i < option; i++) {
            chooseColorAgain = true;

            player[i] = new Player();

            String name = gui.getInstance().getUserString("Spiller " + (i + 1) + ", indtast dit navn: ");

            player[i].setName(name);

            car[i] = new Car(Color.blue);

            while (chooseColorAgain) {
                String color = gui.getInstance().getUserButtonPressed("Hvilken farve bil ønsker du?", "Blå", "Sort", "Hvid", "Gul", "Rød", "Grøn");

                switch (color) {
                    case "Blå":
                        if (blueTaken) {
                            gui.getInstance().showMessage("Blå er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.blue);
                            blueTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Sort":
                        if (blackTaken) {
                            gui.getInstance().showMessage("Sort er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.black);
                            blackTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Hvid":
                        if (whiteTaken) {
                            gui.getInstance().showMessage("Hvid er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.white);
                            whiteTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Gul":
                        if (yellowTaken) {
                            gui.getInstance().showMessage("Gul er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.yellow);
                            yellowTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Rød":
                        if (redTaken) {
                            gui.getInstance().showMessage("Rød er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.RED);
                            redTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                    case "Grøn":
                        if (greenTaken) {
                            gui.getInstance().showMessage("Grøn er allerede taget");
                            chooseColorAgain = true;
                        } else {
                            car[i] = new Car(Color.green);
                            greenTaken = true;
                            chooseColorAgain = false;
                            break;
                        }
                }
            }

            gui_player[i] = new GUI_Player(player[i].getName(),player[i].getAccount().getMoney(), car[i].getCar());

            gui.getInstance().addPlayer(gui_player[i]);

            gui.getSpecificField(0).setCar(gui_player[i], true);

        }


    }

    private void round() {
        DiceCup diceCup = new DiceCup();
        gui.getInstance().setDice(diceCup.die1.rollDice(),diceCup.die2.rollDice());
    }


}
