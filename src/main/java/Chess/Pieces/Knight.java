package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;
import Chess.PieceColor;
import Chess.PieceType;

public class Knight extends ChessPiece {

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color, validMoves(), false);
    }


    private static Move[] validMoves() {
        return new Move[]{new Move(2, 1, false, false), new Move(1, 2, false, false),
                new Move(2, -1, false, false), new Move(-1, 2, false, false),
                new Move(2, -1, false, false), new Move(-1, 2, false, false),
                new Move(-2, 1, false, false), new Move(1, -2, false, false),
                new Move(-2, -1, false, false), new Move(-1, -2, false, false),
                new Move(-2, -1, false, false), new Move(-1, -2, false, false)};
    }
}
