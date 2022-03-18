package com.company;

public class GameMenuUI {



    public void displayGameMenuIntro(){
        System.out.println("""
            Welcome to Adventure!
                Glory awaits
            """);
    }


    public void displayPlayOrExit(){
        System.out.println("Type 'play' to start a game or 'exit' to quit the program");
    }
    public void displayNotAnOption(){
        System.out.println("Not an option - type 'play' or 'exit'");
    }
}
