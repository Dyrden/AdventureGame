package com.company;

import java.util.Scanner;

public class GameMenu {

    private boolean gameRunning = true;
    private final AdventureUI UI = new AdventureUI();
    private final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new GameMenu().run();
    }

    public void run() {
        UI.displayGameMenuIntro();
        UI.displayPlayOrExit();
        while (gameRunning) menu(keyboard.nextLine());
    }

    public void menu(String input) {
        switch (input) {
            case "play" -> new Adventure().run();
            case "exit" -> gameRunning = false;
            default -> UI.displayNotAnOption();
        }
    }
}
