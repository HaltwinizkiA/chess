import com.chess.BoardFactory;
import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.entity.Coordinates;
import com.chess.entity.Game;
import com.chess.enums.Horizontal;
import com.chess.piece.Piece;

import java.util.Set;

public class Main {

    public static void main(String[] args){
//
//        =new Board();
//        board.setupDefaultPeacesPositions();
//        BoardConsoleRenderer boardConsoleRenderer=new BoardConsoleRenderer();
//        boardConsoleRenderer.render(board);
//
//        Game game=new Game(board);
//        game.gameLoop();
        BoardFactory boardFactory=new BoardFactory();
        Board board=boardFactory.fromFen("8/8/1p3P2/8/3B4/8/8/8 w - - 0 1");
        BoardConsoleRenderer boardConsoleRenderer=new BoardConsoleRenderer();
//        boardConsoleRenderer.render(board);
        Game game=new Game(board);
        game.gameLoop();
        int a=123;
    }
}
