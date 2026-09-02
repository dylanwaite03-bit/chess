package chess;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
        if (this.type==PieceType.KING){
            ChessPosition direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn());
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn());
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()+1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()-1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-1);
            if (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                }
            }
        }
        //end king
        if (this.type== PieceType.QUEEN){
            ChessPosition direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn());
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn());
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn());
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn());
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow(),direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow(),direction.getColumn()-1);
            }

            direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn()-1);
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn()-1);
            }
        }
        //queen

        //rook
        if (this.type==PieceType.ROOK){
            ChessPosition direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn());
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn());
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn());
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn());
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow(),direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow(),myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow(),direction.getColumn()-1);
            }
        }
        //end rook
        //bishop
        if(this.type==PieceType.BISHOP){
            ChessPosition direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn()+1);
            }

            direction=new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()+1,direction.getColumn()-1);
            }

            direction=new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-1);
            while (1<=direction.getRow() && 8>=direction.getRow() && 1<=direction.getColumn() && 8>=direction.getColumn()){
                if(board.getPiece(direction)==null){
                    moves.add(new ChessMove(myPosition,direction,null));}
                else if(board.getPiece(direction).getTeamColor()!=this.getTeamColor()){
                    moves.add(new ChessMove(myPosition,direction,null));
                    break;
                }
                else{
                    break;
                }
                direction=new ChessPosition(direction.getRow()-1,direction.getColumn()-1);
            }
        }
        //end bishop

        return moves;

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
