package com.company;

public class Adventure {

    private boolean running = true;
    private Room currentRoom;
    /*
        e
        w
        s
        n
        exit
        look
        help

        * */

    public static void main(String[] args) {
        new Adventure().run();
    }


    public void run() {
        while (running) {

        }
    }

    public void commands(String command) {
        switch (command) {
            case "e" -> System.out.println("go east");
            case "w" -> System.out.println("go west");
            case "n" -> System.out.println("go north");
            case "s" -> System.out.println("go south");
            case "look" -> System.out.println("look");
            case "help" -> System.out.println("help");
            case "exit" -> System.out.println("exit");
            default -> System.out.println("invalid command");
        }


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
    }


}

