import com.chess.board.BoardFactory;
import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.game.Game;

public class Main {

    public static void main(String[] args) {

        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Game game = new Game(board);
        game.gameLoop();

        int a = 123;
    }
}
