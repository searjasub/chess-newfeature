package Chess;

import Chess.Pieces.*;

import java.util.*;

public class ChessBoard {

    //BLACK LINE
    private List<ChessPiece> blackPieces = Arrays.asList(new Rook(PieceColor.BLACK), new Bishop(PieceColor.BLACK),
            new Knight(PieceColor.BLACK), new Queen(PieceColor.BLACK),
            new King(PieceColor.BLACK), new Knight(PieceColor.BLACK),
            new Bishop(PieceColor.BLACK), new Rook(PieceColor.BLACK));

    //WHITE LINE
    private List<ChessPiece> whitePieces = Arrays.asList(new Rook(PieceColor.WHITE), new Bishop(PieceColor.WHITE),
            new Knight(PieceColor.WHITE), new Queen(PieceColor.WHITE),
            new King(PieceColor.WHITE), new Knight(PieceColor.WHITE),
            new Bishop(PieceColor.WHITE), new Rook(PieceColor.WHITE));

    private final Tile[][] board;

    public ChessBoard(String gameVariant) {
        board = new Tile[8][8];
        initializeBoard();
        fillBoard(gameVariant);
    }

    public Tile[][] getBoardArray() {
        return board;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new Tile(TileColor.BLACK);
                else board[i][j] = new Tile(TileColor.WHITE);
            }
        }
    }

    //Will break on boards with no Kings of 'color'. Should never happen.
    public Tuple getKingLocation(PieceColor color) {
        Tuple location = new Tuple(-1, -1);
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (!board[y][x].isEmpty()) {
                    ChessPiece piece = board[y][x].getPiece();
                    if (piece.getColor() == color && piece instanceof King) {
                        location = new Tuple(x, y);
                    }
                }
            }
        }
        return location;
    }

    public Tuple[] getAllPiecesLocationForColor(PieceColor color) {
        ArrayList<Tuple> locations = new ArrayList<>();
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (!board[y][x].isEmpty() && board[y][x].getPiece().getColor() == color)
                    locations.add(new Tuple(x, y));
            }
        }
        return locations.toArray(new Tuple[0]);//allocate new array automatically.
    }

    public Tile getTileFromTuple(Tuple tuple) {
        return board[tuple.Y()][tuple.X()];
    }

    /*
    Initial filler of board
     */
    private void fillBoard(String gameVariant) {

        //pawns are the same on both game variants
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(PieceColor.BLACK));
            board[6][i].setPiece(new Pawn(PieceColor.WHITE));
        }

        if (gameVariant.equals("Normal")) {
            fillBoardNormalWay();
        } else {
            fillBoard960Way();
        }

    }

    /*
    Fill board randomly
     */
    private void fillBoard960Way() {

        do {
            Collections.shuffle(getBlackPieces());
        } while (checkPositions(getBlackPieces().toString().replaceAll("[^\\p{Upper}]", "")));

        System.out.println(getBlackPieces());

        do {
            Collections.shuffle(getWhitePieces());
        } while (checkPositions(getWhitePieces().toString().replaceAll("[^\\p{Upper}]", "")));

        System.out.println(getWhitePieces().toString());


        for (int i = 0; i < 8; i++) {
            board[0][i].setPiece(blackPieces.get(i));
        }

        for (int i = 0; i < 8; i++) {
            board[7][i].setPiece(blackPieces.get(i));
        }
    }

    private boolean checkPositions(String rank) {
        //king between rooks
        if (!rank.matches(".*R.*K.*R.*")) return true;

        //all possible ways bishops can be placed
        return !rank.matches(".*B(..|....|......|)B.*");
    }

    /*
    Helper method to get a random position
     */
    private int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(8);
    }

    /*
    Fill board the classic way
     */
    private void fillBoardNormalWay() {

        //rooks
        board[0][0].setPiece(new Rook(PieceColor.BLACK));
        board[0][7].setPiece(new Rook(PieceColor.BLACK));
        board[7][0].setPiece(new Rook(PieceColor.WHITE));
        board[7][7].setPiece(new Rook(PieceColor.WHITE));

        //knight
        board[0][1].setPiece(new Knight(PieceColor.BLACK));
        board[0][6].setPiece(new Knight(PieceColor.BLACK));
        board[7][1].setPiece(new Knight(PieceColor.WHITE));
        board[7][6].setPiece(new Knight(PieceColor.WHITE));

        //bishop
        board[0][2].setPiece(new Bishop(PieceColor.BLACK));
        board[0][5].setPiece(new Bishop(PieceColor.BLACK));
        board[7][2].setPiece(new Bishop(PieceColor.WHITE));
        board[7][5].setPiece(new Bishop(PieceColor.WHITE));

        //queens
        board[0][3].setPiece(new Queen(PieceColor.BLACK));
        board[7][3].setPiece(new Queen(PieceColor.WHITE));

        //kings
        board[0][4].setPiece(new King(PieceColor.BLACK));
        board[7][4].setPiece(new King(PieceColor.WHITE));
    }
}
