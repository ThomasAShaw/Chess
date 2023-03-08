package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];

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









}
