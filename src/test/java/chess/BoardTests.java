package chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {



    @Test
    public void testValidFirstPawnMove() {
        Board board = new Board();

        assertTrue(board.isValid(0,6, 0, 4));
        assertFalse(board.isValid(0,6, 1, 4));
        board.movePiece(0,6, 0, 4);

        // move king randomly
        assertFalse(board.isValid(4,7, 5, 5));

        // move knight to
        assertFalse(board.isValid(6,7, 1, 1));

        // move knight to Valid position
        assertTrue(board.isValid(6,7, 5, 5));
        board.movePiece(6,7, 5, 5);
        assertEquals(board.getPieceAtPosition(5, 5).getType(), PieceType.KNIGHT );

        // move queen with pawn in the way
        assertFalse(board.isValid(3,7, 3, 4));
        assertTrue(board.movePiece(3,6, 3, 4));
        assertFalse(board.isValid(3,7, 3, 4));


        assertTrue(board.isValid(3,7, 3, 5));
        assertTrue(board.movePiece(3,7, 3, 5));
        // queen moved from here
        assertEquals(board.getPieceAtPosition(3, 7).getType(), PieceType.NOTHING );

        assertEquals(board.getPieceAtPosition(3, 5).getType(), PieceType.QUEEN );

    }







}
