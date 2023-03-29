import com.chess.board.BoardFactory;
import com.chess.board.Board;
import com.chess.game.Game;

public class Main {

    public static void main(String[] args) {

        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFen("8/4pp1P/4pkp1/4ppp1/8/8/PPPPPPP1/RNBQKBNR w KQ - 0 1");
        Game game = new Game(board);
        game.gameLoop();

        int a = 123;
    }
}
