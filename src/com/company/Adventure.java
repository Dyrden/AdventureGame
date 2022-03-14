package com.company;

import java.util.Scanner;

public class Adventure {

    private boolean running = true;
    private Room currentRoom;
    private final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        new Adventure().run();
    }


    public void run() {
        while (running) {
            //initializeGame()
            System.out.println("write command");
            commands(sc.nextLine());
        }
    }

    public void commands(String command) {
        switch (command) {
            case "e" -> go();
            case "w" -> go();
            case "n" -> go();
            case "s" -> go();
            case "look" -> look();
            case "help" -> help();
            case "exit" -> exit();
            default -> {
                System.out.println("No such command, write another");
                commands(sc.nextLine());
            }
        }
    }

    public void look() {
        currentRoom.getRoomDescription();
    }

    public void exit() {
        System.out.println("Thanks for playing");
        running = false;
    }

    public void help() {

    }

    public void go() {



        // 1 'go' method for each of the 4 cardial directions?
        // or 1 'go' method for all of them.
    }

    public void createAndConnectRooms() {
        Room room1 = new Room("Room1", "Room1 desc");
        Room room2 = new Room("Room2", "Room2 desc");
        Room room3 = new Room("Room3", "Room3 desc");
        Room room4 = new Room("Room4", "Room4 desc");
        Room room5 = new Room("Room5", "Room5 desc");
        Room room6 = new Room("Room6", "Room6 desc");
        Room room7 = new Room("Room7", "Room7 desc");
        Room room8 = new Room("Room8", "Room8 desc");
        Room room9 = new Room("Room9", "Room9 desc");
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setNorth(room1);
        room4.setSouth(room7);
        room5.setSouth(room8);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);
        room9.setWest(room8);
        room9.setNorth(room6);
        currentRoom = room1;
    }


}

