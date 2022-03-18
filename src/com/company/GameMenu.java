package com.company;

import java.util.Scanner;

public class GameMenu {

    private boolean gameRunning;
    private GameMenuUI UI = new GameMenuUI();
    private final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new GameMenu().run();
    }

    public void run() {
        while (gameRunning) menu(keyboard.nextLine());
    }

    public void menu(String input) {
        UI.displayGameMenuIntro();
        UI.displayPlayOrExit();
        switch (input) {
            case "play" -> new Adventure().run();
            case "exit" -> gameRunning = false;
            default -> UI.displayNotAnOption();
        }
    }
}
