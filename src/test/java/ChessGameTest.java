import Console.*;
import Chess.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ChessGameTest {

    InputHandler handler = new InputHandler();

    @Test
    public void testNewGameIsNotFinished() {
        ChessGame game = new ChessGame("Normal");
        assertFalse("Game shouldn't start finished", game.isFinished());
    }

    @Test
    public void testChess960IsNotFinished() {
        ChessGame game = new ChessGame("960");
        assertFalse("Game shouldn't start finished", game.isFinished());
    }

    @Test
    public void testFoolsMateEndsGame() {
        String[] foolsMate = new String[]{"F2-F3", "E7-E6", "G2-G4", "D8-H4"};
        ChessGame game = new ChessGame("Normal");
        for (String move : foolsMate) {
            Tuple from = handler.getFrom(move);
            Tuple to = handler.getTo(move);

            boolean movePlayed = game.playMove(from, to);
            if (!movePlayed) fail("Should be legal move");
        }
        Console.BoardDisplay.printBoard(game.getBoard());
        assert (game.isFinished());
    }

    @Test
    public void testFirstMovePawn() {
        Tuple location = handler.parse("A2");
        ChessGame game = new ChessGame("Normal");
        assert (game.isFirstMoveForPawn(location, game.getBoard()));
    }

    @Test
    public void testNotFirstMovePawn() {
        String move = "A2-A3";
        Tuple from = handler.getFrom(move);
        Tuple to = handler.getTo(move);
        ChessGame game = new ChessGame("Normal");
        game.playMove(from, to);
        assert (!game.isFirstMoveForPawn(to, game.getBoard()));
    }


    //WIP

//    @Test
//    public void testBishopsAreInDifferentTileColor() {
//        ChessGame game = new ChessGame("960");
//        ChessBoard board = game.getBoard();
//        List<ChessPiece> blackPieces = board.getBlackPieces();
//        assertFalse(blackPieces.toString().matches(".*B(..|....|......|)B.*"));
//    }
//
//    @Test
//    public void blackKingBetweenRooks() {
//        ChessGame game = new ChessGame("960");
//        ChessBoard board = game.getBoard();
//        List<ChessPiece> blackPieces = board.getBlackPieces();
//        assertFalse(blackPieces.toString().matches(".*R.*K.*R.*"));
//    }
}
