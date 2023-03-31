import com.chess.board.BoardFactory;
import com.chess.board.Board;
import com.chess.game.Game;

public class Main {

    public static void main(String[] args) {

        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPP1P/RNBQKBPR w KQkq - 0 1");
        Game game = new Game(board);
        game.gameLoop();

    }
}
