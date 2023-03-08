package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];

    boolean whiteInCheck;
    boolean blackInCheck;
    List<Piece> listOfDeadPieces = new ArrayList<>();


    public Board(){

        // black Rooks initialize
        board[0][0] = new Piece(false, PieceType.ROOK);
        board[0][7] = new Piece(false, PieceType.ROOK);

        // black Knights initialize
        board[0][1] = new Piece(false, PieceType.KNIGHT);
        board[0][6] = new Piece(false, PieceType.KNIGHT);

        // black Bishops initialize
        board[0][2] = new Piece(false, PieceType.BISHOP);
        board[0][5] = new Piece(false, PieceType.BISHOP);

        // Black King and Queen initialized
        board[0][3] = new Piece(false, PieceType.QUEEN);
        board[0][4] = new Piece(false, PieceType.KING);

        // Black pwns initialized
        for(int i = 0; i < 8; i++){
            Piece blackPawn = new Piece(false, PieceType.PAWN);
            board[1][i] = blackPawn;
        }
        // Empty board initialize
        for(int i = 0; i < 8; i++){
            for(int j = 2; j <= 5; j++ ){
                Piece NullPiece = new Piece(false, PieceType.NOTHING);
                board[j][i] = NullPiece;
            }
        }
        // White pwns initialized
        for(int i = 0; i < 8; i++){
            Piece whitePawn = new Piece(true, PieceType.PAWN);
            board[6][i] = whitePawn;
        }

        // white Rooks initialize
        board[7][7] = new Piece(true, PieceType.ROOK);
        board[7][0] = new Piece(true, PieceType.ROOK);

        // white Knights initialize
        board[7][1] = new Piece(true, PieceType.KNIGHT);
        board[7][6] = new Piece(true, PieceType.KNIGHT);

        // white Bishops initialize
        board[7][2] = new Piece(true, PieceType.BISHOP);
        board[7][5] = new Piece(true, PieceType.BISHOP);

        // white King and Queen initialized
        board[7][7] = new Piece(true, PieceType.QUEEN);
        board[7][3] = new Piece(true, PieceType.KING);

    }


    public Piece getPieceAtPosition(int y, int x){
        return new Piece(board[y][x].isWhite(), board[y][x].getType());
    }



    public boolean movePiece(int oldY, int oldX, int newY, int newX){

        if(isValid(oldX, oldY, newX, newY)){

            if(board[newY][newX].getType()!= PieceType.NOTHING){
                listOfDeadPieces.add(board[newY][newX]);
            }

            board[newY][newX] = board[oldY][oldX];
            board[oldY][oldX] = new Piece(false, PieceType.NOTHING);
            return true;
        }
        return false;
    }

    /**
     * Check if a move is valid for the piece at (startX, startY) to (finishX, finishY).
     * @param startX x-coordinate of starting position.
     * @param startY y-coordinate of starting position.
     * @param finishX x-coordinate of finishing position.
     * @param finishY y-coordinate of finishing position.
     * @return true if is a valid move, false otherwise.
     */
    public boolean isValid(int startX, int startY, int finishX, int finishY) {
        /* Check passed positions are on the board. */
        if (startX > 7 || startX < 0 || startY > 7 || startY < 0  ||
                finishX > 7 || finishX < 0 || finishY > 7 || finishY < 0) {
            return false;
        }

        Piece movingPiece = board[startY][startX];

        /* Check movingPiece does not end on piece of same colour. */
        if (board[finishY][finishX].isWhite() == movingPiece.isWhite()
                && board[finishY][finishX].getType() != PieceType.NOTHING) {
            return false;
        }

        switch(movingPiece.getType()) {
            case KING:
                /* Can move 1 space in all directions. */
                return getDistance(startX, startY, finishX, finishY) == 1;
            case QUEEN:
                /* Can move in all directions, unlimited spaces. */
                break;
            case BISHOP:
                /* Can move only diagonally, unlimited spaces. */
                break;
            case ROOK:
                break;
            case KNIGHT:
                break;
            case PAWN:
                /* Can only move one or two spaces (if at starting position) in specified direction (depending on colour). */
                if (movingPiece.isWhite()) { // Is a white piece.
                    /* Can only move diagonally one space if taking. */
                    if (movingPiece.isWhite() != board[finishY][finishX].isWhite() && board[finishY][finishX].getType() != PieceType.NOTHING) {
                        return ((finishX == startX + 1) || (finishX == startX - 1)) && (finishY == startY - 1);
                    }

                    /* Otherwise, check if at starting position (can move 1-2 spaces vertically). */
                    return (startY == 6 ? (getDistance(startX, startY, finishX, finishY) == 1
                            || getDistance(startX, startY, finishX, finishY) == 2)
                            : getDistance(startX, startY, finishX, finishY) == 1)
                            && (startY < finishY && startX == finishX);
                } else { // Is a black piece.
                    /* Can only move diagonally one space if taking. */
                    if (movingPiece.isWhite() != board[finishY][finishX].isWhite() && board[finishY][finishX].getType() != PieceType.NOTHING) {
                        return ((finishX == startX + 1) || (finishX == startX - 1)) && (finishY == startY + 1);
                    }

                    /* Otherwise, check if at starting position (can move 1-2 spaces vertically). */
                    return (startY == 6 ? (getDistance(startX, startY, finishX, finishY) == 1
                            || getDistance(startX, startY, finishX, finishY) == 2)
                            : getDistance(startX, startY, finishX, finishY) == 1)
                            && (startY > finishY && startX == finishX);
                }
        }
        return false;
    }

    /**
     * Return distance between pair coordinates.
     * @param xOne x-coordinate of first position.
     * @param yOne y-coordinate of first position.
     * @param xTwo x-coordinate of second position.
     * @param yTwo y-coordinate of second position.
     * @return directional distance in terms of squares to get from (xOne, yOne) to (xTwo, yTwo);
     *         returns 0 if both coordinates are the same, or not a possible move.
     */
    private int getDistance(int xOne, int yOne, int xTwo, int yTwo) {
        if (xOne == xTwo) {
            return yOne - yTwo;
        } else if (yOne == yTwo) {
            return xOne - xTwo;
        } else {
            /* Check if they are diagonal coordinates. */
            if (Math.abs(xOne - xTwo) == Math.abs(yOne - yTwo)) {
                return Math.abs(xOne - xTwo);
            }

            return 0;
        }
    }

    private boolean noCollision(int xOne, int yOne, int xTwo, int yTwo) {
        return false;
    }
}
