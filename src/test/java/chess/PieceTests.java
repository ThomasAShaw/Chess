package chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTests {
    @Test
    public void testPieceConstructor() {
        Piece myPiece = new Piece(true, PieceType.KNIGHT);
        assertTrue(myPiece.isWhite());
        assertEquals(myPiece.getType(), PieceType.KNIGHT);
    }
}
