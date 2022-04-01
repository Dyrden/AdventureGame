package com.company;

import java.util.Scanner;

public class GameMenu {

    private boolean gameRunning = true;
    private final AdventureUI UI = new AdventureUI();
    private final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new GameMenu().run();
    }

    private void run() {
        UI.displayGameMenuIntro();
        UI.displayPlayOrExit();
        while (gameRunning) menu(keyboard.nextLine());
    }

    private void menu(String input) {
        switch (input.toLowerCase()) {
            case "play" -> new Adventure().run();
            case "exit" -> gameRunning = false;
            default -> UI.displayNotAnOption();
        }
    }
}
