import com.chess.BoardFactory;
import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.Game;

public class Main {

    public static void main(String[] args) {
//
//        =new Board();
//        board.setupDefaultPeacesPositions();
//        BoardConsoleRenderer boardConsoleRenderer=new BoardConsoleRenderer();
//        boardConsoleRenderer.render(board);
//
//        Game game=new Game(board);
//        game.gameLoop();
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFen("8/2PB4/5R2/8/1P1Q2p1/p7/5n2/P2N4 w - - 0 1");
        BoardConsoleRenderer boardConsoleRenderer = new BoardConsoleRenderer();
//        boardConsoleRenderer.render(board);
        Game game = new Game(board);
        game.gameLoop();
        int a = 123;
    }
}
