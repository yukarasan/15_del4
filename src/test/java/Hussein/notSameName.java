package Hussein;

import Main.Player;

import java.util.Scanner;

public class notSameName {

    public boolean checkForSameName(String name, int playerNamesYet, Player[] players){

        boolean writeNameAgain = false;

        for(int i=0; i<playerNamesYet; i++) {
            if (!name.equals(players[i].getName())) {
                writeNameAgain = false;
            }
        }
            for (int i = 0; i < playerNamesYet; i++) {
                if (name.equals(players[i].getName())) {
                    writeNameAgain = true;
                }
            }
            return writeNameAgain;
        }

    public static void main(String[] args) {

        Player[] players = new Player[3];

        for(int i=0; i<3; i++){
            players[i] = new Player();
        }
        notSameName notSameName = new notSameName();
        Scanner scan = new Scanner(System.in);

        for(int i=0; i < players.length; i++){
            System.out.print("Player " + (1+i) + ", write in your name: ");
            String name = scan.nextLine();
            players[i].setName(name);

            while(notSameName.checkForSameName(name, i, players)){
                System.out.print("Name is already taken, write in a new one: ");
                name = scan.nextLine();
            }
            players[i].setName(name);
        }

        System.out.print("\n" + "Names: ");
        for(int i=0; i<3; i++){
            System.out.print(players[i].getName() + ", ");
        }
    }
}