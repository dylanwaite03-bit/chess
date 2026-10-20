package ui;

import client.ServerFacade;

import java.util.Scanner;

public class ChessClient {

    private final Scanner scanner=new Scanner(System.in);
    private final ServerFacade server=new ServerFacade();

    public void prelogin(){

        System.out.println("1: Login");
        System.out.println("2: Create account");
        String choice=scanner.nextLine();
        if (choice == "2"){

        }
    }
    private void register(){
        System.out.print("username");
        String username=scanner.nextLine();
        System.out.print("password");
        String password=scanner.nextLine();
        System.out.print("email");
        String email=scanner.nextLine();
        try
    }
}
