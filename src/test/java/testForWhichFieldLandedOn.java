import Fields.GameBoard;

import java.util.stream.IntStream;

public class testForWhichFieldLandedOn {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        System.out.println(IntStream.of(gameBoard.allOwnableFields()).anyMatch(x -> x == 10));
    }
}
