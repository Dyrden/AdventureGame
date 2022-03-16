package com.company;

import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class Adventure {

    private boolean running = true;
    private Room currentRoom;
    private Player player;
    private final Scanner sc = new Scanner(System.in);
    private File soundFile = new File("C:\\Users\\Markd\\IdeaProjects\\AdventureGame\\soundClip.wav");

    private static void playClip(File clipFile) throws IOException,
        UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } finally {
                clip.close();
            }
        } finally {
            audioInputStream.close();
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        new Adventure().run();
    }


    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        initializeGame();
        while (running) {
            System.out.println("What would you like to do?");
            System.out.println("(type 'help' to view commands)");
            commands(sc.nextLine().toLowerCase());
        }
    }

    private void initializeGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        //playClip(soundFile);
        player = new Player(sc.nextLine(),100,null);
        System.out.println("\nWelcome to the Adventure of your lifetime.");
        System.out.println("\nYou are walking around in the forest near your town.");
        System.out.println("After wandering for hours, you decided to take a break, laying down your backpack.");
        System.out.println("After a short break, you decide to set up camp.");
        System.out.println("You decide to go look for firewood.");
        System.out.println("All of a sudden, the feeling of ground pushing against your feet fails you.");
        System.out.println("The sensation of falling is the last thing you remember\n");
        createAndConnectRooms();
    }

    public void commands(String command) {
        switch (command) {
            case "go east","east","e" -> go("east");
            case "go west","west","w" -> go("west");
            case "go north","north","n" -> go("north");
            case "go south","south","s" -> go("south");
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
        System.out.println(currentRoom.getRoomDescription());
    }

    public void exit() {
        System.out.println("Thanks for playing");
        running = false;
    }

    public void help() {
        System.out.println("Commands:");
        System.out.println("'go north' - attempt going north");
        System.out.println("'go south' - attempt going south");
        System.out.println("'go east' - attempt going east");
        System.out.println("'go west' - attempt going west");
        System.out.println("'look' - look around the current room");
        System.out.println("'help' - see commands");
        System.out.println("'exit' - exit game");
    }



    public void go(String direction) {

        switch (direction) {
            case "east" -> {
                if (currentRoom.getEast() != null ) {
                    currentRoom = currentRoom.getEast();
                    displayCurrentRoomChangeDescription();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "west" -> {
                if (currentRoom.getWest() != null ) {
                    currentRoom = currentRoom.getWest();
                    displayCurrentRoomChangeDescription();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "north" -> {
                if (currentRoom.getNorth() != null ) {
                    currentRoom = currentRoom.getNorth();
                    displayCurrentRoomChangeDescription();
                } else {
                    displayNoSuchDirection(direction);
                }
            }
            case "south" -> {
                if (currentRoom.getSouth() != null ) {
                    currentRoom = currentRoom.getSouth();
                    displayCurrentRoomChangeDescription();
                } else {
                    displayNoSuchDirection(direction);
                }
            }

        }
    }

    public void displayCurrentRoomChangeDescription() {
        System.out.println(currentRoom.getRoomDescription());
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }

    public void createAndConnectRooms() {
        Room room1 = new Room("Hole", "You fell in this hole and your possessions now seperated from you.");
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
        player.setCurrentRoom(room1);
    }


}

