import com.chess.BoardFactory;
import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.Game;
import com.chess.enums.Horizontal;
import com.chess.piece.Coordinates;
import com.chess.piece.King;
import com.chess.piece.Piece;

import java.util.Set;

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
        Board board = boardFactory.fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        BoardConsoleRenderer boardConsoleRenderer = new BoardConsoleRenderer();
//      boardConsoleRenderer.render(board);
        Game game = new Game(board);
        game.gameLoop();

        int a = 123;
    }
}
