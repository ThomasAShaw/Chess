package chess;

import java.util.Objects;

public class Piece {
    private final boolean isWhite;
    private final PieceType type;

    /**
     * Initialize a new piece object.
     * @param isWhite true if this piece is white, false otherwise.
     * @param type this piece's type.
     */
    public Piece(boolean isWhite, PieceType type) {
        this.isWhite = isWhite;
        this.type = type;
    }

    /**
     * Return colour of piece.
     * @return true if piece is white, false otherwise.
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Return this piece's type.
     * @return type of piece.
     */
    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isWhite == piece.isWhite && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWhite, type);
    }
}
