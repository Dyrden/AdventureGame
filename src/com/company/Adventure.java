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
            case "go east","east" -> go("east");
            case "go west" -> go("west");
            case "go north" -> go("north");
            case "go south" -> go("south");
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



    public void go(String direction) {

        switch (direction) {
            case "east" -> {
                if (currentRoom.getEast() != null ) {
                    currentRoom = currentRoom.getEast();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "west" -> {
                if (currentRoom.getWest() != null ) {
                    currentRoom = currentRoom.getWest();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "north" -> {
                if (currentRoom.getNorth() != null ) {
                    currentRoom = currentRoom.getNorth();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "south" -> {
                if (currentRoom.getSouth() != null ) {
                    currentRoom = currentRoom.getSouth();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
        }
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }

    public void createAndConnectRooms() {
        Room room1 = new Room("Hole", "You fell in this hole and lost all your possessions.");
        Room room2 = new Room("Cave", "Dank dark cavern, bats are hanging from the ceiling.");
        Room room3 = new Room("Crawl space", "You are in a tight crawl space. Nothing useful around.");
        Room room4 = new Room("Sewer", "You entered a sewer. Rats are running around.");
        Room room5 = new Room("Treasure Chamber", "You've stumbled upon a treasure like no other. You don't ever have to work again.");
        Room room6 = new Room("Security", "You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar. There is a golden key on the desk.");
        Room room7 = new Room("Sewage filtration", "You've entered a room with a machine filtrating the sewage.");
        Room room8 = new Room("Golden Door", "You find yourself in a room with a locked giant golden door.");
        Room room9 = new Room("Back-alley", "You entered a back-alley, seems to connect important areas of this complex.");
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

