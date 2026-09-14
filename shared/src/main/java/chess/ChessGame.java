package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard board;
    private TeamColor teamTurn;
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;
    private boolean whiteLeftRookMoved = false;
    private boolean whiteRightRookMoved = false;
    private boolean blackLeftRookMoved = false;
    private boolean blackRightRookMoved = false;
    private ChessMove lastmove =null;

    public ChessGame() {
        this.board=new ChessBoard();
        board.resetBoard();
        this.teamTurn=TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        teamTurn=team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> validmoves= new ArrayList<>();
        ChessPiece piece=board.getPiece(startPosition);
        if (piece==null){
            return null;
        }
        Collection<ChessMove> pieceMoves = piece.pieceMoves(board, startPosition);

        if (piece.getPieceType() == ChessPiece.PieceType.KING) {
            int row = startPosition.getRow();

            if (!isInCheck(piece.getTeamColor())) {
                if (board.getPiece(new ChessPosition(row, 6)) == null &&
                        board.getPiece(new ChessPosition(row, 7)) == null) {

                    ChessPiece rook = board.getPiece(new ChessPosition(row, 8));

                    if (rook != null &&
                            rook.getPieceType() == ChessPiece.PieceType.ROOK &&
                            rook.getTeamColor() == piece.getTeamColor() &&
                            ((piece.getTeamColor() == TeamColor.WHITE && !whiteKingMoved && !whiteRightRookMoved) ||
                                    (piece.getTeamColor() == TeamColor.BLACK && !blackKingMoved && !blackRightRookMoved))) {

                        board.removePiece(startPosition);
                        board.addPiece(new ChessPosition(row, 6), piece);

                        boolean check = isInCheck(piece.getTeamColor());

                        board.removePiece(new ChessPosition(row, 6));
                        board.addPiece(new ChessPosition(row, 7), piece);

                        boolean finalCheck = isInCheck(piece.getTeamColor());

                        board.removePiece(new ChessPosition(row, 7));
                        board.addPiece(startPosition, piece);

                        if (!check && !finalCheck) {
                            validmoves.add(new ChessMove(startPosition,
                                    new ChessPosition(row, 7), null));
                        }
                    }
                }
                if (board.getPiece(new ChessPosition(row, 2)) == null &&
                        board.getPiece(new ChessPosition(row, 3)) == null &&
                        board.getPiece(new ChessPosition(row, 4)) == null) {

                    ChessPiece rook = board.getPiece(new ChessPosition(row, 1));

                    if (rook != null &&
                            rook.getPieceType() == ChessPiece.PieceType.ROOK &&
                            rook.getTeamColor() == piece.getTeamColor() &&
                            ((piece.getTeamColor() == TeamColor.WHITE && !whiteKingMoved && !whiteLeftRookMoved) ||
                                    (piece.getTeamColor() == TeamColor.BLACK && !blackKingMoved && !blackLeftRookMoved))) {

                        board.removePiece(startPosition);
                        board.addPiece(new ChessPosition(row, 4), piece);

                        boolean check = isInCheck(piece.getTeamColor());

                        board.removePiece(new ChessPosition(row, 4));
                        board.addPiece(new ChessPosition(row, 3), piece);

                        boolean finalCheck = isInCheck(piece.getTeamColor());

                        board.removePiece(new ChessPosition(row, 3));
                        board.addPiece(startPosition, piece);

                        if (!check && !finalCheck) {
                            validmoves.add(new ChessMove(startPosition,
                                    new ChessPosition(row, 3), null));
                        }
                    }
                }
            }
        }

        if(piece.getPieceType()== ChessPiece.PieceType.PAWN && lastmove!=null){
            ChessPosition laststart=lastmove.getStartPosition();
            ChessPosition lastend=lastmove.getEndPosition();
            ChessPiece lastpiece=board.getPiece(lastend);
            if (lastpiece != null &&
                    lastpiece.getPieceType() == ChessPiece.PieceType.PAWN &&
                    (laststart.getRow() - lastend.getRow() == 2 ||
                            lastend.getRow() - laststart.getRow() == 2) &&
                    lastend.getRow() == startPosition.getRow() &&
                    (lastend.getColumn() - startPosition.getColumn() == 1 ||
                            startPosition.getColumn() - lastend.getColumn() == 1)){

                int direction=0;
                if (piece.getTeamColor()==TeamColor.WHITE){
                    direction=1;
                }
                if (piece.getTeamColor()==TeamColor.BLACK){
                    direction=-1;
                }
                ChessPosition endposition=new ChessPosition(startPosition.getRow()+direction, lastend.getColumn());
                validmoves.add(new ChessMove(startPosition,endposition,null));
            }
        }

        for (ChessMove move : pieceMoves) {

            ChessPiece capturedPiece = board.getPiece(move.getEndPosition());

            board.removePiece(startPosition);
            board.removePiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), piece);

            if (!isInCheck(piece.getTeamColor())) {
                validmoves.add(move);
            }

            board.removePiece(move.getEndPosition());
            board.addPiece(startPosition, piece);

            if (capturedPiece != null) {
                board.addPiece(move.getEndPosition(), capturedPiece);
            }
        }
        return validmoves;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startposition=move.getStartPosition();
        ChessPosition endposition=move.getEndPosition();

        ChessPiece piece=board.getPiece(startposition);

        if (piece == null) {
            throw new InvalidMoveException();
        }

        if (piece.getTeamColor() != teamTurn) {
            throw new InvalidMoveException();
        }

        Collection<ChessMove> validmoves= validMoves(startposition);

        if (!validmoves.contains(move)) {
            throw new InvalidMoveException();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.KING) {
            if (piece.getTeamColor() == TeamColor.WHITE) {
                whiteKingMoved = true;
            }
            else {
                blackKingMoved = true;
            }
        }

        if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
            if (piece.getTeamColor() == TeamColor.WHITE) {
                if (startposition.getColumn() == 1) {
                    whiteLeftRookMoved = true;
                }
                else if (startposition.getColumn() == 8) {
                    whiteRightRookMoved = true;
                }
            }
            else {
                if (startposition.getColumn() == 1) {
                    blackLeftRookMoved = true;
                }
                else if (startposition.getColumn() == 8) {
                    blackRightRookMoved = true;
                }
            }
        }

        if (piece.getPieceType()== ChessPiece.PieceType.PAWN && startposition.getColumn()!=endposition.getColumn() &&
        board.getPiece(endposition)==null){
            board.removePiece(new ChessPosition(startposition.getRow(),endposition.getColumn()));

        }

        board.removePiece(startposition);
        board.removePiece(endposition);
        if (piece.getPieceType() == ChessPiece.PieceType.KING &&
                endposition.getColumn() == 7) {

            ChessPiece rook = board.getPiece(
                    new ChessPosition(startposition.getRow(), 8));

            board.removePiece(
                    new ChessPosition(startposition.getRow(), 8));

            board.addPiece(
                    new ChessPosition(startposition.getRow(), 6), rook);
        }

        if (piece.getPieceType() == ChessPiece.PieceType.KING &&
                endposition.getColumn() == 3) {

            ChessPiece rook = board.getPiece(
                    new ChessPosition(startposition.getRow(), 1));

            board.removePiece(
                    new ChessPosition(startposition.getRow(), 1));

            board.addPiece(
                    new ChessPosition(startposition.getRow(), 4), rook);
        }

        if (move.getPromotionPiece()!=null) {
            board.addPiece(endposition, new ChessPiece(piece.getTeamColor(), move.getPromotionPiece()));
        }
        else {
            board.addPiece(endposition, piece);
        }

        if (teamTurn == TeamColor.WHITE) {
            teamTurn = TeamColor.BLACK;
        }
        else {
            teamTurn = TeamColor.WHITE;
        }
        lastmove=move;

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ArrayList<ChessPosition> positions= new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                positions.add(new ChessPosition(row, column));
            }
        }

        ArrayList<ChessMove> moves=new ArrayList<>();
        ChessPosition kingposition= new ChessPosition(0,0);
        for(ChessPosition position : positions){
            ChessPiece piece=board.getPiece(position);
            if (piece!=null){
                if (piece.getTeamColor()!=teamColor){
                    Collection<ChessMove> piecemoves=piece.pieceMoves(board,position);
                    moves.addAll(piecemoves);
                }
                else if(piece.getPieceType()== ChessPiece.PieceType.KING && piece.getTeamColor()==teamColor){
                    kingposition=position;
                }
            }
        }

        for (ChessMove move : moves) {
            if (move.getEndPosition().equals(kingposition)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
       if (!isInCheck(teamColor)){
           return false;
       }

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition position=new ChessPosition(row,column);
                ChessPiece piece=board.getPiece(position);
                if (piece!=null && piece.getTeamColor()==teamColor){
                    Collection<ChessMove> validmoves= validMoves(position);
                    if(validmoves!=null && !validmoves.isEmpty()){
                        return false;
                    }
                }

            }
        }

        return true;
    }
    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition position=new ChessPosition(row,column);
                ChessPiece piece=board.getPiece(position);
                if (piece!=null && piece.getTeamColor()==teamColor){
                    Collection<ChessMove> validmoves= validMoves(position);
                    if(validmoves!=null && !validmoves.isEmpty()){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {

        this.board=board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, teamTurn);
    }
}
