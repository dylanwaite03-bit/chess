package chess;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor=pieceColor;
        this.type=type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves= new ArrayList<>();
        //king
        if (this.type == PieceType.KING) {
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1), moves);
        }
        //queen
        if (this.type == PieceType.QUEEN) {
            addSlidingMoves(board, myPosition, moves, 1, 0);
            addSlidingMoves(board, myPosition, moves, -1, 0);
            addSlidingMoves(board, myPosition, moves, 0, 1);
            addSlidingMoves(board, myPosition, moves, 0, -1);
            addSlidingMoves(board, myPosition, moves, 1, 1);
            addSlidingMoves(board, myPosition, moves, 1, -1);
            addSlidingMoves(board, myPosition, moves, -1, 1);
            addSlidingMoves(board, myPosition, moves, -1, -1);
        }
        //rook
        if (this.type == PieceType.ROOK) {
            addSlidingMoves(board, myPosition, moves, 1, 0);
            addSlidingMoves(board, myPosition, moves, -1, 0);
            addSlidingMoves(board, myPosition, moves, 0, 1);
            addSlidingMoves(board, myPosition, moves, 0, -1);
        }
        //bishop
        if (this.type == PieceType.BISHOP) {
            addSlidingMoves(board, myPosition, moves, 1, 1);
            addSlidingMoves(board, myPosition, moves, 1, -1);
            addSlidingMoves(board, myPosition, moves, -1, 1);
            addSlidingMoves(board, myPosition, moves, -1, -1);
        }
        //knight
        if (this.type == PieceType.KNIGHT) {
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1), moves);
            addMoveIfValid(board, myPosition,
                    new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1), moves);
        }
        //pawn
        if (this.type == PieceType.PAWN) {
            int direction = this.getTeamColor() == ChessGame.TeamColor.WHITE ? 1 : -1;
            int startRow = this.getTeamColor() == ChessGame.TeamColor.WHITE ? 2 : 7;
            int promotionRow = this.getTeamColor() == ChessGame.TeamColor.WHITE ? 7 : 2;
            ChessPosition forward = new ChessPosition(
                    myPosition.getRow() + direction,
                    myPosition.getColumn());
            if (board.getPiece(forward) == null) {
                addPawnMoves(moves, myPosition, forward,
                        myPosition.getRow() == promotionRow);
                if (myPosition.getRow() == startRow) {
                    ChessPosition doubleForward = new ChessPosition(
                            myPosition.getRow() + (direction * 2),
                            myPosition.getColumn());
                    if (board.getPiece(doubleForward) == null) {
                        moves.add(new ChessMove(myPosition, doubleForward, null));
                    }
                }
            }
            addPawnCapture(board, moves, myPosition,
                    myPosition.getRow() + direction,
                    myPosition.getColumn() + 1,
                    myPosition.getRow() == promotionRow);
            addPawnCapture(board, moves, myPosition,
                    myPosition.getRow() + direction,
                    myPosition.getColumn() - 1,
                    myPosition.getRow() == promotionRow);
        }
        return moves;
    }

//pawn helpers
    private void addPawnMoves(Collection<ChessMove> moves,
                              ChessPosition start,
                              ChessPosition end,
                              boolean promotion) {

        if (promotion) {
            moves.add(new ChessMove(start, end, PieceType.ROOK));
            moves.add(new ChessMove(start, end, PieceType.KNIGHT));
            moves.add(new ChessMove(start, end, PieceType.BISHOP));
            moves.add(new ChessMove(start, end, PieceType.QUEEN));
        }
        else {
            moves.add(new ChessMove(start, end, null));
        }
    }

    private void addPawnCapture(ChessBoard board,
                                Collection<ChessMove> moves,
                                ChessPosition start,
                                int row,
                                int column,
                                boolean promotion) {

        if (row >= 1 && row <= 8 && column >= 1 && column <= 8) {
            ChessPosition end = new ChessPosition(row, column);
            ChessPiece piece = board.getPiece(end);

            if (piece != null && piece.getTeamColor() != this.getTeamColor()) {
                addPawnMoves(moves, start, end, promotion);
            }
        }
    }
//other move helpers
    private void addMoveIfValid(ChessBoard board, ChessPosition myPosition,
                                ChessPosition direction, Collection<ChessMove> moves) {
        if (1 <= direction.getRow() && direction.getRow() <= 8
                && 1 <= direction.getColumn() && direction.getColumn() <= 8) {

            ChessPiece piece = board.getPiece(direction);

            if (piece == null || piece.getTeamColor() != this.getTeamColor()) {
                moves.add(new ChessMove(myPosition, direction, null));
            }
        }
    }

    private void addSlidingMoves(ChessBoard board, ChessPosition myPosition,
                                 Collection<ChessMove> moves, int rowChange, int columnChange) {
        ChessPosition direction = new ChessPosition(
                myPosition.getRow() + rowChange,
                myPosition.getColumn() + columnChange);

        while (1 <= direction.getRow() && direction.getRow() <= 8
                && 1 <= direction.getColumn() && direction.getColumn() <= 8) {

            ChessPiece piece = board.getPiece(direction);

            if (piece == null) {
                moves.add(new ChessMove(myPosition, direction, null));
            }
            else {
                if (piece.getTeamColor() != this.getTeamColor()) {
                    moves.add(new ChessMove(myPosition, direction, null));
                }
                break;
            }

            direction = new ChessPosition(
                    direction.getRow() + rowChange,
                    direction.getColumn() + columnChange);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
