package com.company;

import com.company.Item.ItemType;
import java.util.ArrayList;
import java.util.Scanner;


public class Adventure {

    private boolean running = true;
    private final Scanner sc = new Scanner(System.in);
    private final int amountOfRooms = 9;
    private Room[] rooms = new Room[amountOfRooms];
    private Room currentRoom;
    private int currentHealth = 100;
    private int currentDamage = 1;
    private boolean isPoisoned = false;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    //private File soundFile = new File("C:\\Users\\Markd\\IdeaProjects\\AdventureGame\\soundClip.wav");
/*
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
* */

    public static void main(String[] args) {
        new Adventure().run();
    }


    public void run() {
        initializeGame();
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
        createAndConnectRooms();
    }

    private void help() {
        for (Command command : Command.values()) {
            System.out.println(command.getCommandDescription());
        }
    }

    private void commands(String[] command) {
        switch (command[0]) {
            case "go" -> go(command[1]);
            case "look" -> look();
            case "status" -> showStatus();
            case "inventory" -> showInventory();
            case "take" -> take(command[1]);
            case "use" -> use(command[1]);
            case "attack" -> attack();
            case "help" -> help();
            case "exit", "quit" -> exit();
            default -> {
                System.out.println("No such command, try again.");
                commands(input());
            }
        }
    }

    private void showStatus() {
        System.out.println("Status:");
        System.out.println("Health: " + currentHealth);
        if (isPoisoned) {
            System.out.println("Poisoned. Use antidote to cure.");
        }
        if (currentDamage == 1) {
            System.out.println("No weapon equipped.");
        } else {
            System.out.println("Has knife equipped.");
        }
    }

    private void use(String useParameter) {
        switch (useParameter) {
            case "key" -> use(ItemType.key);
            case "antidote" -> use(ItemType.antidote);
            case "knife" -> use(ItemType.knife);
            case "food" -> use(ItemType.food);
            default -> System.out.println("You can only use items you have in your inventory.");
        }
    }

    private void use(ItemType itemType) {
        boolean hasItem = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == itemType) {
                hasItem = true;
                switch (itemType) {
                    case key -> useKey();
                    case food -> useFood();
                    case antidote -> useAntidote();
                    case knife -> useKnife(i);
                }
            }
        }
        if (!hasItem) {
            System.out.println("Don't have " + itemType.toString() + ".");
        }
    }

    private void useKey() {
        if (currentRoom.getEnemies().size() == 0) {
            if ((currentRoom.getNorth() != null) && (currentRoom.getNorth().getIsLocked())) {
                currentRoom.getNorth().setIsLocked(false);
                System.out.println("The door has unlocked.");
            } else {
                System.out.println("No lock to use the key in.");
            }
        } else {
            System.out.println("Can't do that right now. An adversary is in the way.");
        }
    }

    private void useFood() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == ItemType.food) {
                currentHealth += inventory.get(i).getItemModifier();
                System.out.println("Ate food and recovered " + inventory.get(i).getItemModifier() + " health.");
                inventory.remove(i);
                break;
            }
        }
    }

    private void useAntidote() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == ItemType.antidote) {
                if (isPoisoned) {
                    isPoisoned = false;
                    System.out.println("Used antidote to cure poison status effect.");
                } else {
                    System.out.println("Used antidote.");
                }
                inventory.remove(i);
                break;
            }
        }
    }

    private void useKnife(int i) {
        if (currentDamage == 1) {
            System.out.println("Equipping knife in right hand.");
            currentDamage = inventory.get(i).getItemModifier();
        } else {
            System.out.println("Unequipping knife.");
            currentDamage = 1;
        }
    }

    private void showInventory() {
        System.out.println("Inventory:");
        if (inventory.size() > 0) {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(inventory.get(i).getItemType().toString());
            }
        } else {
            System.out.println("Is empty.");
        }
    }

    private void take(String useParameter) {
        switch (useParameter) {
            case "key" -> take(ItemType.key);
            case "antidote" -> take(ItemType.antidote);
            case "knife" -> take(ItemType.knife);
            case "food" -> take(ItemType.food);
            default -> System.out.println("You can't take " + useParameter);
        }
    }

    private void take(ItemType itemType) {
        if (currentRoom.getItems().size() > 0) {
            boolean isAvailable = false;
            for (int i = 0; i < currentRoom.getItems().size(); i++) {
                if (currentRoom.getItems().get(i).getItemType() == itemType) {
                    System.out.println("Took " + currentRoom.getItems().get(i).getItemType().toString() + ".");
                    inventory.add(currentRoom.getItems().get(i));
                    currentRoom.getItems().remove(i);
                    updateRoomDescription();
                    isAvailable = true;
                }
            }
            if (!isAvailable) {
                System.out.println("No " + itemType.toString() + " here.");
            }
        } else {
            System.out.println("Nothing to pick up here.");
        }
    }

    private void updateRoomDescription() {
        switch (currentRoom.getRoomName()) {
            case "Cave" -> {
                if (currentRoom.getEnemies().size() == 1) {
                    currentRoom.setRoomDescription("Dank dark cavern, a bat is hanging from the ceiling. Another one dead on the ground.");
                } else if (currentRoom.getEnemies().size() == 0) {
                    currentRoom.setRoomDescription("Dank dark cavern, two bats are dead on the ground.");
                }
            }
            case "Crawl space" -> {
                if (currentRoom.getItems().size() == 0) {
                    currentRoom.setRoomDescription("You are in a tight crawl space.");
                }
            }
            case "Sewer" -> {
                if (currentRoom.getEnemies().size() == 0) {
                    currentRoom.setRoomDescription("You entered a sewer. There is a dead rat, its blood is fusing with the sewage.");
                }
            }
            case "Security" -> {
                if (currentRoom.getItems().size() == 0) {
                    currentRoom.setRoomDescription("You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar.");
                }
            }
            case "Sewage filtration" -> {
                if (currentRoom.getItems().size() == 0) {
                    currentRoom.setRoomDescription("You've entered a room with a machine filtrating the sewage.");
                }
            }
            case "Golden Door" -> {
                if (currentRoom.getEnemies().size() == 0) {
                    currentRoom.setRoomDescription("You find yourself in a room with a locked giant golden door.");
                }
            }
            case "Back-alley" -> {
                if (currentRoom.getItems().size() == 0) {
                    currentRoom.setRoomDescription("You entered a back-alley, seems to connect important areas of this complex.");
                }
            }
        }
    }

    private void attack() {
        if (currentRoom.getEnemies().size() > 0) {
            for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
                if (enemyTakeDamage(i)) {
                    playerTakeDamage(i);
                }
                break;
            }
        } else {
            System.out.println("Nothing to attack here.");
        }
    }

    private boolean enemyTakeDamage(int i) {
        System.out.println("Attacking " + currentRoom.getEnemies().get(i).getEnemyType().toString() + " for " + currentDamage + " damage.");
        boolean retaliate = false;
        if (currentRoom.getEnemies().get(i).getCurrentHealth() - currentDamage > 0) {
            currentRoom.getEnemies().get(i).setCurrentHealth(currentRoom.getEnemies().get(i).getCurrentHealth() - currentDamage);
            System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has " + currentRoom.getEnemies().get(i).getCurrentHealth() + " health remaining.");
            retaliate = true;
        } else {
            System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has died.");
            currentRoom.getEnemies().get(i).setCurrentHealth(0);
            currentRoom.removeEnemy(i);
            updateRoomDescription();
        }
        return retaliate;
    }

    private void playerTakeDamage(int i) {
        if ((currentRoom.getEnemies().get(i) != null) && (currentHealth - currentRoom.getEnemies().get(i).getDamage() > 0)) {
            currentHealth -= currentRoom.getEnemies().get(i).getDamage();
            System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage. " + currentHealth + " health remaining.");
            if (currentRoom.getEnemies().get(i).getIsPoisonous()) {
                isPoisoned = true;
                applyPoisonDamage(5);
            }
        } else {
            currentHealth = 0;
            System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage and killed you.");
            exit();
        }
    }

    private void applyPoisonDamage(int poisonDmg) {
        if (isPoisoned) {
            if (currentHealth - poisonDmg > 0) {
                currentHealth -= poisonDmg;
                System.out.println("You take " + poisonDmg + " poison damage. " + currentHealth + " health remaining.");
            } else {
                currentHealth = 0;
                System.out.println("You took " + poisonDmg + " poison damage and died from it.");
                exit();
            }
        }
    }

    private void look() {
        System.out.println(currentRoom.getRoomDescription());
    }

    private void exit() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
        running = false;
    }

    private void go(String direction) {
        switch (direction) {
            case "east" -> {
                checkEast(direction);
            }
            case "west" -> {
                checkWest(direction);
            }
            case "north" -> {
                checkNorth(direction);
            }
            case "south" -> {
                checkSouth(direction);
            }
            default -> System.out.println(direction + " is not a cardinal direction, rethink your choices.");

        }
    }

    private void checkNorth(String direction) {
        if (currentRoom.getNorth() != null) {
            if (currentRoom.getNorth().getRoomName().equals("Treasure Chamber") && (currentRoom.getNorth().getIsLocked()) && (currentRoom.getEnemies().size() == 1)) {
                System.out.println("You try running past the " + currentRoom.getEnemies().get(0).getEnemyType().toString() + " but the door is locked.");
                playerTakeDamage(0);
            }
            else if (currentRoom.getNorth().getRoomName().equals("Treasure Chamber") && (currentRoom.getNorth().getIsLocked()) && (currentRoom.getEnemies().size() == 0)) {
                System.out.println("You try walking through the locked door, you hit your head.");
            }
            else {
                currentRoom = currentRoom.getNorth();
                displayCurrentRoomChangeDescription();
                if (currentRoom.getRoomName().equals("Treasure Chamber")) {
                    exit();
                } else {
                    applyPoisonDamage(5);
                }
            }
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkSouth(String direction) {
        if (currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
            displayCurrentRoomChangeDescription();
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkEast(String direction) {
        if (currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
            displayCurrentRoomChangeDescription();
            applyPoisonDamage(5);
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkWest(String direction) {
        if (currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
            displayCurrentRoomChangeDescription();
            applyPoisonDamage(5);
        } else {
            displayNoSuchDirection(direction);
        }
    }

    public void displayCurrentRoomChangeDescription() {
        System.out.println(currentRoom.getRoomDescription());
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }

    private void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    private void createAndConnectRooms() {
        createRooms();
        connectRooms();
        createItems();
        createEnemies();
        currentRoom = rooms[0];
    }

    private void createRooms() {
        rooms[0] = new Room("Hole", "You fell in this hole and your possessions now seperated from you.", false);
        rooms[1] = new Room("Cave", "Dank dark cavern, bats are hanging from the ceiling.", false);
        rooms[2] = new Room("Crawl space", "You are in a tight crawl space. There's an abandoned antidote on the ground.", false);
        rooms[3] = new Room("Sewer", "You entered a sewer. There is a rat running around.", false);
        rooms[4] = new Room("Treasure Chamber", "You've stumbled upon a treasure like no other. You won't ever have to work again.", true);
        rooms[5] = new Room("Security", "You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar. There is a golden key on the desk.", false);
        rooms[6] = new Room("Sewage filtration", "You've entered a room with a machine filtrating the sewage. Someone left a plate of food here, probably lost their appetite.", false);
        rooms[7] = new Room("Golden Door", "You find yourself in a room with a locked giant golden door. A giant snake guards the door.", false);
        rooms[8] = new Room("Back-alley", "You entered a back-alley, seems to connect important areas of this complex. There is a knife on the ground.", false);
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
        rooms[2].addItem(new Item(ItemType.antidote, 1));
        rooms[5].addItem(new Item(ItemType.key, 1));
        rooms[6].addItem(new Item(ItemType.food, 30));
        rooms[8].addItem(new Item(ItemType.knife, 10));
    }
}

