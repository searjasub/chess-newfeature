package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;
import Chess.PieceColor;
import Chess.PieceType;

public class Bishop extends ChessPiece {

    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color, validMoves(), true);
    }


    private static Move[] validMoves() {
        return new Move[]{new Move(1, 1, false, false), new Move(1, -1, false, false),
                new Move(-1, 1, false, false), new Move(-1, -1, false, false)};
    }

    @Override
    public String toString() {
        return "B";
    }
}
