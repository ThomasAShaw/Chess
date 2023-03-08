package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];

    List<Piece> listOfDeadPieces = new ArrayList<>();


    public Board(){

        for(int i = 0; i < 8; i++){
            Piece blackPawn = new Piece(false, PieceType.PAWN);
            board[1][i] = blackPawn;
        }

        for(int i = 0; i < 8; i++){
            for(int j = 2; j <= 5; j++ ){
                Piece NullPiece = new Piece(false, PieceType.PAWN);
                board[j][i] = NullPiece;
            }
        }



    }









}
