package ui;

import client.ServerFacade;
import model.GameData;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChessClient {

    private final Scanner scanner=new Scanner(System.in);
    private final ServerFacade server=new ServerFacade();
    private final Map<Integer,Integer> gameChoices=new HashMap<>();

    public void main(){
        while (true) {
            if (server.authenticated()) {
                mainscreen();
            }
            else {
                System.out.println("1: Login");
                System.out.println("2: Create account");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("login")) {
                    login();
                }
                if (choice.equals("create account")) {
                    register();
                }
            }
        }
    }

    private void register(){
        System.out.print("username");
        String username=scanner.nextLine();
        System.out.print("password");
        String password=scanner.nextLine();
        System.out.print("email");
        String email=scanner.nextLine();
        try{
            String response=server.register(username,password,email);
            System.out.println(response);
        }catch(Exception e){
            System.out.println("register failed" + e.getMessage());
        }
    }

    private void login(){
        System.out.print("username");
        String username=scanner.nextLine();
        System.out.print("password");
        String password=scanner.nextLine();
        try{
            String response=server.login(username,password);
            System.out.println(response);
        }catch(Exception e){
            System.out.println("login failed" + e.getMessage());
        }
    }

    private void mainscreen(){
        System.out.print("what would you like to do? Help, Logout, Create Game, " +
                "List Games, Play Game, Observe Game");
        String response=scanner.nextLine().toLowerCase();
        if (response.equals("logout")){
            logout();
        }
        else if (response.equals("create game")){
            createGame();
        }
        else if (response.equals("list games")){
            listGames();
        }
        else if (response.equals("play game")){
            playGame();
        }
        else if (response.equals("observe game")){
            observeGame();
        }
        else {
            try {
                System.out.println(response);
            } catch (Exception e) {
                System.out.println("failed" + e.getMessage());
            }
        }
    }

    private void logout(){
        try{
            String response=server.logout();
            System.out.println(response);
        }catch(Exception e){
            System.out.println("logout failed" + e.getMessage());
        }
    }

    private void createGame(){
        System.out.print("game name");
        String gameName=scanner.nextLine();
        try{
            String response=server.createGame(gameName);
            System.out.println(response);
        }catch(Exception e){
            System.out.println("create game failed" + e.getMessage());
        }
    }
    private void listGames(){
        try{
            Map<Integer, GameData> response=server.listGames();
            int i=1;
            for (Map.Entry<Integer, GameData> item:response.entrySet()){
                GameData game=item.getValue();
                System.out.println((i)+"."+game.gameName());
                gameChoices.put(i,item.getKey());
                i++;
            }
        }catch(Exception e){
            System.out.println("create game failed" + e.getMessage());
        }
    }

    private void playGame(){
        listGames();
        System.out.print("enter game number");
        String gameNumber=scanner.nextLine();
        System.out.print("WHITE or BLACK?");
        String color=scanner.nextLine().toUpperCase();
        try{
            int gameid=gameChoices.get(Integer.parseInt(gameNumber));
            String response=server.joinGame(gameid,color);
            System.out.println(response);
        }catch(Exception e){
            System.out.println("join game failed" + e.getMessage());
        }
    }

    private void observeGame(){
        listGames();
        System.out.print("enter game number to observe");
        String gameNumber=scanner.nextLine();
        try{
            whitePerspective();
        }catch(Exception e){
            System.out.println("join game failed" + e.getMessage());
        }
    }

    private void whitePerspective(){
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

    }


}
