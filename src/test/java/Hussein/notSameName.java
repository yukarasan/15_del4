package Hussein;

import Main.Player;

import java.util.Scanner;

public class notSameName {

    //This method takes in the name itself, how many players are in yet (in the for loop), and the players'
    //Array
    public boolean checkForSameName(String name, int playerNamesYet, Player[] players){

        boolean writeNameAgain = false;
            for (int i = 0; i < playerNamesYet; i++) {
                if (name.equals(players[i].getName())) {
                    writeNameAgain = true;
                }
            }
            return writeNameAgain;
        }

    public static void main(String[] args) {

        Player[] players = new Player[3];

        //Initiating the players
        for(int i=0; i<3; i++){
            players[i] = new Player();
        }
        notSameName notSameName = new notSameName();
        Scanner scan = new Scanner(System.in);


        //Here is the forloop I talked about at the top
        for(int i=0; i < players.length; i++){
            System.out.print("Player " + (1+i) + ", write in your name: ");
            String name = scan.nextLine();
            players[i].setName(name);

            //While that same name boolean from the method above this main
            // while it is true, then take another input from that player
            while(notSameName.checkForSameName(name, i, players)){
                System.out.print("Name is already taken, write in a new one: ");
                name = scan.nextLine();
            }
            players[i].setName(name);
        }

        //here we write out the names
        System.out.print("\n" + "Names: ");
        for(int i=0; i<3; i++){
            System.out.print(players[i].getName() + ", ");
        }
    }
}