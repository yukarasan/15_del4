import Fields.GameBoard;

public class Game {

    public void startGame() {
        GameBoard gameBoard = new GameBoard();

        // We place the board
        gameBoard.placeBoard();
        gameBoard.allField();

    }

}
