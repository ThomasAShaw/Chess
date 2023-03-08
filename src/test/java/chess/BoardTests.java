package chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    public static Board board;
    @BeforeAll
    public static void setup(){
        board = new Board();
    }


    @Test
    public void testPiece() {
        Piece myPiece = new Piece(true, PieceType.KNIGHT);
        assertTrue(myPiece.isWhite());
        assertEquals(myPiece.getType(), PieceType.KNIGHT);
    }

    @Test
    public void testBoardConstructor() {
        Board myBoard = new Board();

        assertEquals(myBoard.getPieceAtPosition(0,0), new Piece(false, PieceType.ROOK));
        assertEquals(myBoard.getPieceAtPosition(1,0), new Piece(false, PieceType.KNIGHT));
        assertEquals(myBoard.getPieceAtPosition(2,0), new Piece(false, PieceType.BISHOP));
        assertEquals(myBoard.getPieceAtPosition(3,0), new Piece(false, PieceType.QUEEN));
        assertEquals(myBoard.getPieceAtPosition(4,0), new Piece(false, PieceType.KING));
        assertEquals(myBoard.getPieceAtPosition(5,0), new Piece(false, PieceType.BISHOP));
        assertEquals(myBoard.getPieceAtPosition(6,0), new Piece(false, PieceType.KNIGHT));
        assertEquals(myBoard.getPieceAtPosition(7,0), new Piece(false, PieceType.ROOK));

        for (int i = 0; i < 8; i++) {
            assertEquals(myBoard.getPieceAtPosition(i,1), new Piece(false, PieceType.PAWN));
        }

        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 8; x++) {
                assertEquals(myBoard.getPieceAtPosition(x, y).getType(), PieceType.NOTHING);
            }
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(myBoard.getPieceAtPosition(i,6), new Piece(true, PieceType.PAWN));
        }

        assertEquals(myBoard.getPieceAtPosition(0,7), new Piece(true, PieceType.ROOK));
        assertEquals(myBoard.getPieceAtPosition(1,7), new Piece(true, PieceType.KNIGHT));
        assertEquals(myBoard.getPieceAtPosition(2,7), new Piece(true, PieceType.BISHOP));
        assertEquals(myBoard.getPieceAtPosition(3,7), new Piece(true, PieceType.QUEEN));
        assertEquals(myBoard.getPieceAtPosition(4,7), new Piece(true, PieceType.KING));
        assertEquals(myBoard.getPieceAtPosition(5,7), new Piece(true, PieceType.BISHOP));
        assertEquals(myBoard.getPieceAtPosition(6,7), new Piece(true, PieceType.KNIGHT));
        assertEquals(myBoard.getPieceAtPosition(7,7), new Piece(true, PieceType.ROOK));
    }

    @Test
    public void testInvalidPosition() {
        Board myBoard = new Board();
        assertThrows(RuntimeException.class, () -> myBoard.getPieceAtPosition(0, -1));
        assertThrows(RuntimeException.class, () -> myBoard.getPieceAtPosition(9, 0));
    }

    @Test
    public void testIsValidOutOfBounds() {
        Board myBoard = new Board();

        assertFalse (myBoard.isValid(0, -1, 0, 5));
        assertFalse (myBoard.isValid(Integer.MIN_VALUE, 3, 1, 3));
        assertFalse (myBoard.isValid(7, 6, 7, -55));
        assertFalse (myBoard.isValid(-19234, -11, -98, -55));

        assertFalse(myBoard.isValid(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertFalse(myBoard.isValid(8, 8, 8, 8));
        assertFalse(myBoard.isValid(0, 2, 6, 8));
        assertFalse(myBoard.isValid(1, 1, 1, -1));

    }

    @Test
    @Order(1)
    public void testValidFirstPawnMove() {

        assertTrue(board.isValid(0,6, 0, 4));
        assertFalse(board.isValid(0,6, 1, 4));
        board.movePiece(0,6, 0, 4);

    }

    @Test
    @Order(2)
    public void testInValidKingMove(){
        // move king randomly
        assertFalse(board.isValid(4,7, 5, 5));
    }


    @Test
    @Order(3)
    public void testKnightMove(){
        // move knight to
        assertFalse(board.isValid(6,7, 1, 1));

        // move knight to Valid position
        assertTrue(board.isValid(6,7, 5, 5));
        board.movePiece(6,7, 5, 5);
        assertEquals(board.getPieceAtPosition(5, 5).getType(), PieceType.KNIGHT );

    }

    @Test
    @Order(4)
    public void testQueenMoveWithPwnInWay(){
        // move queen with pawn in the way
        assertFalse(board.isValid(3,7, 3, 4));
        assertTrue(board.movePiece(3,6, 3, 4));
        assertFalse(board.isValid(3,7, 3, 4));
    }
    @Test
    @Order(5)
    public void testQueenMoveValid(){

        board.getPieceAtPosition(3, 5);
        assertTrue(board.isValid(3,7, 3, 5));

        assertTrue(board.movePiece(3,7, 3, 5));
        // queen moved from here
        assertEquals(board.getPieceAtPosition(3, 7).getType(), PieceType.NOTHING );

        assertEquals(board.getPieceAtPosition(3, 5).getType(), PieceType.QUEEN );
    }
}
