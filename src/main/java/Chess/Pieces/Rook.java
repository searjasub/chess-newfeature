package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;
import Chess.PieceColor;
import Chess.PieceType;

public class Rook extends ChessPiece {

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color, validMoves(), true);
    }


    private static Move[] validMoves() {
        return new Move[]{new Move(1, 0, false, false), new Move(0, 1, false, false),
                new Move(-1, 0, false, false), new Move(0, -1, false, false)};
    }

    @Override
    public String toString() {
        return "R";
    }
}
