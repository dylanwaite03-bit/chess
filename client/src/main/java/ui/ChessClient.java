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
    private final GamePlayScreen gamePlayScreen=new GamePlayScreen();

    public void main(){
        while (true) {
            if (server.authenticated()) {
                mainscreen();
            }
            else {
                System.out.println("Login");
                System.out.println("Create account");
                System.out.println("Help");
                System.out.println("Quit");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("login")) {
                    login();
                }
                else if (choice.equals("create account")) {
                    register();
                }
                else if (choice.equals("help")){
                    System.out.println("Type Login, Regiser, or Quit");
                }
                else if (choice.equals("quit")){
                    break;
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
        else if (response.equals("help")){
            System.out.println("Type logout, create game, list games, play game, or observe game");
        }
        else {

            System.out.println("Invalid command");

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
            System.out.println("list game failed" + e.getMessage());
        }
    }

    private void playGame(){
        listGames();
        System.out.print("enter game number");
        String gameNumber=scanner.nextLine();
        System.out.print("WHITE or BLACK?");
        String color=scanner.nextLine().toUpperCase();
        try{
            if (color.equals("WHITE")){
                gamePlayScreen.whitePerspective();
            } else if (color.equals("BLACK")) {
                gamePlayScreen.blackPerspective();
            }
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
            gamePlayScreen.whitePerspective();
        }catch(Exception e){
            System.out.println("join game failed" + e.getMessage());
        }
    }

}
