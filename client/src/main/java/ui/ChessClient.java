package ui;

import java.util.Scanner;

public class ChessClient {

    public static void prelogin(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("1: Login");
        System.out.println("2: Create account");
        String choice=scanner.nextLine();
    }
}
