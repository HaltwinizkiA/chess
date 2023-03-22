import com.chess.entity.Board;
import com.chess.entity.BoardConsoleRenderer;

public class Main {

    public static void main(String[] args){

        Board board=new Board();
        board.setupDefaultPeacesPositions();
        BoardConsoleRenderer boardConsoleRenderer=new BoardConsoleRenderer();
        boardConsoleRenderer.render(board);

        int a=123;
    }
}
