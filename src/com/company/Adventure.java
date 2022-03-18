package com.company;

import com.company.Item.ItemType;

import java.util.Scanner;

public class Adventure {

    private static boolean running = true;
    private final Scanner sc = new Scanner(System.in);
    private final int amountOfRooms = 9;
    private Room[] rooms = new Room[amountOfRooms];
    public Player player = new Player(rooms[0]);
    /*
    //private File soundFile = new File("C:\\Users\\Markd\\IdeaProjects\\AdventureGame\\soundClip.wav");

    private static void playClip(File clipFile) throws IOException,
        UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;

            @Override
            public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }

            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) {
                    wait();
                }
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
   */

    public static void main(String[] args) {
        new Adventure().run();
    }


    public void run() {
        initializeGame();
        createAndConnectRooms();
        while (running) {
            System.out.println("What would you like to do? (type 'help' to view commands)");
            commands(input());

        }
    }

    private String[] input() {
        String[] split = sc.nextLine().toLowerCase().split(" ",2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }

    private void initializeGame() {
        //playClip(soundFile);
        System.out.println("\nWelcome to the Adventure of your lifetime.");
        System.out.println("\nYou are walking around in the forest near your town.");
        System.out.println("After wandering for hours, you decided to take a break, laying down your backpack.");
        System.out.println("After a short break, you decide to set up camp and go to look for firewood.");
        System.out.println("All of a sudden, the feeling of the ground pushing against your feet fails you.");
        System.out.println("The sensation of falling is the last thing you remember\n");
    }

    private void help() {
        for (Command command : Command.values()) {
            System.out.println(command.getCommandDescription());
        }
    }

    private void commands(String[] command) {
        switch (command[0]) {
            case "go" -> player.go(command[1]);
            case "look" -> player.look();
            case "status" -> player.showStatus();
            case "inventory" -> player.showInventory();
            case "take" -> player.take(command[1]);
            case "use" -> player.use(command[1]);
            case "attack" -> player.attack();
            case "help" -> help();
            case "exit", "quit" -> exit();
            default -> {
                System.out.println("No such command, try again.");
            }
        }
    }

    public static void exit() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
        running = false;
    }

    private void createAndConnectRooms() {
        createRooms();
        connectRooms();
        createItems();
        createEnemies();
        player.setCurrentRoom(rooms[0]);
    }

    private void createRooms() {
        rooms[0] = new Room("Hole", "You fell in this hole and your possessions now seperated from you.", false, player);
        rooms[1] = new Room("Cave", "Dank dark cavern, bats are hanging from the ceiling.", false, player);
        rooms[2] = new Room("Crawl space", "You are in a tight crawl space. There's an abandoned antidote on the ground.", false, player);
        rooms[3] = new Room("Sewer", "You entered a sewer. There is a rat running around.", false, player);
        rooms[4] = new Room("Treasure Chamber", "You've stumbled upon a treasure like no other. You won't ever have to work again.", true, player);
        rooms[5] = new Room("Security", "You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar. There is a golden key on the desk.", false, player);
        rooms[6] = new Room("Sewage filtration", "You've entered a room with a machine filtrating the sewage. Someone left a plate of food here, probably lost their appetite.", false, player);
        rooms[7] = new Room("Golden Door", "You find yourself in a room with a locked giant golden door. A giant snake guards the door.", false, player);
        rooms[8] = new Room("Back-alley", "You entered a back-alley, seems to connect important areas of this complex. There is a knife on the ground.", false, player);
    }

    private void connectRooms() {
        rooms[0].setEast(rooms[1]);
        rooms[0].setSouth(rooms[3]);

        rooms[1].setWest(rooms[0]);
        rooms[1].setEast(rooms[2]);

        rooms[2].setWest(rooms[1]);
        rooms[2].setSouth(rooms[5]);

        rooms[3].setNorth(rooms[0]);
        rooms[3].setSouth(rooms[6]);

        rooms[4].setSouth(rooms[7]);

        rooms[5].setNorth(rooms[2]);
        rooms[5].setSouth(rooms[8]);

        rooms[6].setNorth(rooms[3]);
        rooms[6].setEast(rooms[7]);

        rooms[7].setWest(rooms[6]);
        rooms[7].setNorth(rooms[4]);
        rooms[7].setEast(rooms[8]);

        rooms[8].setWest(rooms[7]);
        rooms[8].setNorth(rooms[5]);
    }

    private void createEnemies() {
        rooms[1].addEnemy(new Enemy(Enemy.EnemyType.bat, 10, 9, true));
        rooms[1].addEnemy(new Enemy(Enemy.EnemyType.bat, 10, 9, true));
        rooms[3].addEnemy(new Enemy(Enemy.EnemyType.rat, 5, 5, false));
        rooms[7].addEnemy(new Enemy(Enemy.EnemyType.snake, 50, 10, true));
    }

    private void createItems() {
        rooms[2].addItem(new Item(ItemType.antidote, 1, 20, player));
        rooms[5].addItem(new Item(ItemType.key, 1, 1, player));
        rooms[6].addItem(new Item(ItemType.food, 30, 40, player));
        rooms[8].addItem(new Item(ItemType.knife, 10, 1, player));
    }
}