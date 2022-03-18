package com.company;

import com.company.Item.ItemType;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private final int maxHealth = 100;
    private int currentHealth;
    private int currentDamage = 1;
    private boolean isPoisoned = false;
    public ArrayList<Item> inventory = new ArrayList<>();
    private int baseDamage = 1;

    public Player(Room currentRoom) {
        this.currentHealth = maxHealth;
        this.currentRoom = currentRoom;
    }

    public void healHealth(int health) {
        if (this.currentHealth + health > maxHealth)
            this.currentHealth = maxHealth;
        else
            this.currentHealth += health;
    }
    public void damageHealth(int health) {
        this.currentHealth -= health;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void showStatus() {
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

    public void showInventory() {
        System.out.println("Inventory:");
        if (inventory.size() > 0) {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(inventory.get(i).getItemType().toString());
            }
        } else {
            System.out.println("Is empty.");
        }
    }

    private void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public boolean playerDealDamage(int i) {
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
            currentRoom.updateRoomDescription();
        }
        return retaliate;
    }

    public void playerTakeDamage(int i) {
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
            Adventure.exit();
        }
    }

    public void applyPoisonDamage(int poisonDmg) {
        if (isPoisoned) {
            if (currentHealth - poisonDmg > 0) {
                currentHealth -= poisonDmg;
                System.out.println("You take " + poisonDmg + " poison damage. " + currentHealth + " health remaining.");
            } else {
                currentHealth = 0;
                System.out.println("You took " + poisonDmg + " poison damage and died from it.");
                Adventure.exit();
            }
        }
    }

    public void use(String useParameter) {
        switch (useParameter) {
            case "key" -> inventory.get(0).use(ItemType.key);
            case "antidote" -> inventory.get(0).use(ItemType.antidote);
            case "knife" -> inventory.get(0).use(ItemType.knife);
            case "food" -> inventory.get(0).use(ItemType.food);
            default -> System.out.println("You can only use items you have in your inventory.");
        }
    }

    public void take(String useParameter) {
        switch (useParameter) {
            case "key" -> inventory.get(0).take(ItemType.key);
            case "antidote" -> inventory.get(0).take(ItemType.antidote);
            case "knife" -> inventory.get(0).take(ItemType.knife);
            case "food" -> inventory.get(0).take(ItemType.food);
            default -> System.out.println("You can't take " + useParameter);
        }
    }

    public void attack() {
        if (currentRoom.getEnemies().size() > 0) {
            for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
                if (playerDealDamage(i)) {
                    playerTakeDamage(i);
                }
                break;
            }
        } else {
            System.out.println("Nothing to attack here.");
        }
    }

    public void look() {
        System.out.println(getCurrentRoom().getRoomDescription());
    }

    public void go(String direction) {
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
        if (getCurrentRoom().getNorth() != null) {
            if (getCurrentRoom().getNorth().getRoomName().equals("Treasure Chamber") && (getCurrentRoom().getNorth().getIsLocked()) && (getCurrentRoom().getEnemies().size() == 1)) {
                System.out.println("You try running past the " + getCurrentRoom().getEnemies().get(0).getEnemyType().toString() + " but the door is locked.");
                playerTakeDamage(0);
            }
            else if (getCurrentRoom().getNorth().getRoomName().equals("Treasure Chamber") && (getCurrentRoom().getNorth().getIsLocked()) && (getCurrentRoom().getEnemies().size() == 0)) {
                System.out.println("You try walking through the locked door, you hit your head.");
            }
            else {
                setCurrentRoom(getCurrentRoom().getNorth());
                displayCurrentRoomChangeDescription();
                if (getCurrentRoom().getRoomName().equals("Treasure Chamber")) {
                    Adventure.exit();
                } else {
                    applyPoisonDamage(5);
                }
            }
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkSouth(String direction) {
        if (getCurrentRoom().getSouth() != null) {
            setCurrentRoom(getCurrentRoom().getSouth());
            displayCurrentRoomChangeDescription();
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkEast(String direction) {
        if (getCurrentRoom().getEast() != null) {
            setCurrentRoom(getCurrentRoom().getEast());
            displayCurrentRoomChangeDescription();
            applyPoisonDamage(5);
        } else {
            displayNoSuchDirection(direction);
        }
    }

    private void checkWest(String direction) {
        if (getCurrentRoom().getWest() != null) {
            setCurrentRoom(getCurrentRoom().getWest());
            displayCurrentRoomChangeDescription();
            applyPoisonDamage(5);
        } else {
            displayNoSuchDirection(direction);
        }
    }

    public void displayCurrentRoomChangeDescription() {
        System.out.println(getCurrentRoom().getRoomDescription());
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }
    public void setCurrentHealth(int newHealth) {
        currentHealth = newHealth;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setIsPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }
    public boolean getIsPoisoned() {
        return isPoisoned;
    }
    public int getCurrentDamage() {
        return currentDamage;
    }
    public void setCurrentDamage(int newDamage) {
        currentDamage = newDamage;
    }
    public int getBaseDamage() {
        return baseDamage;
    }
}
