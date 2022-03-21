package com.company;

import com.company.Item.ItemType;

import java.util.Scanner;

public class Adventure {


    private final Scanner playerInput = new Scanner(System.in);
    private final AdventureUI UI = new AdventureUI();
    private boolean gameRunning = true;


    private final int amountOfRooms = 9;
    private Room[] rooms = new Room[amountOfRooms];
    public Player player = new Player(rooms[0]);


    public void run() {
        UI.displayGameIntro();

        createAndConnectRooms();

        while (gameRunning) {
            UI.displayTurnStartText();
            commands(input());
        }

        //maybe
        // if (gameIsOver)
    }

    private String[] input() {
        String[] split = playerInput.nextLine().toLowerCase().split(" ", 2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }


    private void help() {
        UI.displayCommandOptions();
    }

    private void commands(String[] command) {
        switch (command[0]) {
            case "go" -> {
                boolean playerMoved = player.go(command[1]);
                UI.displayPlayerMove(playerMoved, command[1]);
                UI.displayCurrentRoomDescription(playerMoved, player.getCurrentRoom());
            }
            case "look" -> UI.displayRoomStatus(player.getCurrentRoom());
            case "status" -> UI.displayPlayerStatus(player.toString());
            case "inventory" -> UI.displayInventory(player.getInventory());
            case "take" -> player.take(command[1]);
            case "use" -> player.use(command[1]);
            case "drop" -> player.drop(command[1]);
            case "attack" -> player.attack();
            case "help" -> help();
            case "exit", "quit" -> exit();
            default -> UI.displayNoSuchCommand();
        }
    }

    public void gameWin() {
        // if player room == win room
        UI.displayWinGame();
    }

    public void gameLose() {
        // if player health == 0
        UI.displayLoseGame();
    }

    public void exit() {
        UI.displayExitGame();
        gameRunning = false;
    }


    //THIS METHOD WILL EVENTUALLY BE REPLACED BY A DungeonGenerator METHOD
    private void createAndConnectRooms() {
        createRooms();
        connectRooms();
        createItems();
        createEnemies();
        player.setCurrentRoom(rooms[0]);
    }

    //THIS METHOD WILL EVENTUALLY BE REPLACED BY A DungeonGenerator METHOD
    private void createRooms() {
        rooms[0] = new Room("Hole", "You fell in this hole and your possessions now seperated from you.", false);
        rooms[1] = new Room("Cave", "Dank dark cavern, bats are hanging from the ceiling.", false);
        rooms[2] = new Room("Crawl space", "You are in a tight crawl space. There's an abandoned antidote on the ground.", false);
        rooms[3] = new Room("Sewer", "You entered a sewer. There is a rat running around.", false);
        rooms[4] = new Room("Treasure Chamber", "You've stumbled upon a treasure like no other. You won't ever have to work again.", true);
        rooms[5] = new Room("Security", "You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar. There is a golden KEY on the desk.", false);
        rooms[6] = new Room("Sewage filtration", "You've entered a room with a machine filtrating the sewage. Someone left a plate of FOOD here, probably lost their appetite.", false);
        rooms[7] = new Room("Golden Door", "You find yourself in a room with a locked giant golden door. A giant snake guards the door.", false);
        rooms[8] = new Room("Back-alley", "You entered a back-alley, seems to connect important areas of this complex. There is a KNIFE on the ground.", false);
    }

    //THIS METHOD WILL EVENTUALLY BE REPLACED BY A DungeonGenerator METHOD
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


    //THIS METHOD WILL EVENTUALLY BE REPLACED BY A DungeonGenerator METHOD
    private void createEnemies() {
        rooms[1].addEnemy(new Enemy(Enemy.EnemyType.bat, 10, 9, true));
        rooms[1].addEnemy(new Enemy(Enemy.EnemyType.bat, 10, 9, true));
        rooms[3].addEnemy(new Enemy(Enemy.EnemyType.rat, 5, 5, false));
        rooms[7].addEnemy(new Enemy(Enemy.EnemyType.snake, 50, 10, true));
    }

    //THIS METHOD WILL EVENTUALLY BE REPLACED BY A DungeonGenerator METHOD
    private void createItems() {
        rooms[2].addItem(new Item("an abandoned ANTIDOTE", ItemType.ANTIDOTE, 1, 20, player));
        rooms[5].addItem(new Item("a golden KEY", ItemType.KEY, 1, 1, player));
        rooms[6].addItem(new Item("a plate of FOOD", ItemType.FOOD, 30, 40, player));
        rooms[8].addItem(new Item("a KNIFE", ItemType.KNIFE, 10, 1, player));
    }

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
}
