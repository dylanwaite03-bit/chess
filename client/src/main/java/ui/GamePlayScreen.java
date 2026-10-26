package ui;

public class GamePlayScreen {

    public void whitePerspective(){
        whitePerspectiveLetters();
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_ROOK);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_QUEEN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_KING);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_ROOK);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();

        blankRow();

        blankRow();

        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.WHITE_ROOK);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.WHITE_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.WHITE_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.WHITE_QUEEN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.WHITE_KING);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.WHITE_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.WHITE_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.WHITE_ROOK);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        whitePerspectiveLetters();
    }

    public void blackPerspective(){
        blackPerspectiveLetters();
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_ROOK);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_QUEEN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_KING);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_ROOK);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.WHITE_PAWN);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();

        blankRow();
        blankRow();

        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.BLACK_PAWN);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.BLACK_ROOK);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.BLACK_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.BLACK_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.BLACK_QUEEN);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.BLACK_KING);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.BLACK_BISHOP);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK + EscapeSequences.BLACK_KNIGHT);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE + EscapeSequences.BLACK_ROOK);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        blackPerspectiveLetters();
    }

    public void blankRow(){
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_BLACK+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE+EscapeSequences.EMPTY);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
    }

    public void whitePerspectiveLetters(){
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW);
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" a ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" b ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" c ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" d ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" e ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" f ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" g ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" h ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
    }
    public void blackPerspectiveLetters(){
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW);
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" h ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" g ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" f ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" e ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" d ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" c ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" b ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW+" a ");
        System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW);
        System.out.print(EscapeSequences.RESET_BG_COLOR);
        System.out.println();
    }
}
