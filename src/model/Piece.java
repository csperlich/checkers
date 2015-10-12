package model;

public class Piece implements PieceInterface {
    private final PieceColor color;
    boolean king;

    public Piece(PieceColor color) {
        this.color = color;
        this.king = false;
    }

    public Piece(PieceColor color, boolean isKing) {
        this.color = color;
        this.king = isKing;
    }

    public Piece(PieceInterface otherPiece) {
        this.color = otherPiece.getColor();
        this.king = otherPiece.isKing();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Piece other = (Piece) obj;
        if (this.color != other.color) {
            return false;
        }
        if (this.king != other.king) {
            return false;
        }
        return true;
    }

    @Override
    public PieceColor getColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
        result = prime * result + (this.king ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean isBlack() {
        return this.getColor().equals(PieceColor.BLACK);
    }

    @Override
    public boolean isKing() {
        return this.king;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isSameColorAs(PieceInterface otherPiece) {
        return this.color.equals(otherPiece.getColor());
    }

    @Override
    public boolean isWhite() {
        return this.getColor().equals(PieceColor.WHITE);
    }

    @Override
    public void kingMe() {
        this.king = true;
    }

    @Override
    public String toString() {
        return "Piece [color=" + this.color + ", king=" + this.king + "]";
    }

    @Override
    public void unKingMe() {
        this.king = false;

    }
}
