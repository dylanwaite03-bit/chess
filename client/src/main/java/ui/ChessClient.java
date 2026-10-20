package ui;

import client.ServerFacade;

import java.util.Scanner;

public class ChessClient {

    private final Scanner scanner=new Scanner(System.in);
    private final ServerFacade server=new ServerFacade();


    public void main(){
        while (true) {
            if (server.authenticated()) {
                mainscreen();
            }
            else {
                System.out.println("1: Login");
                System.out.println("2: Create account");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    login();
                }
                if (choice.equals("2")) {
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
        String response=scanner.nextLine();
        try{
            System.out.println(response);
        }catch(Exception e){
            System.out.println("login failed" + e.getMessage());
        }
    }

}
