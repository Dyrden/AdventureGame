package com.company;

import com.company.Items.Item;

import java.util.ArrayList;

public class Player {
    private final AdventureUI ui = new AdventureUI();
    private Room currentRoom;

    private int currentHealth; // set at start of the game
    private int maxHealth = 100; // might wanna add items to boost max health, so this may not be final

    private int currentDamage = 1; // technically its baseDamage
    private Item weaponEquip = null;

    private boolean isPoisoned = false;

    public ArrayList<Item> inventory = new ArrayList<>();
    private final int baseDamage = 1;

    public Player(Room currentRoom) {
        this.currentHealth = maxHealth;
        this.currentRoom = currentRoom;
        this.currentDamage = baseDamage;
    }

    public int getHealth() {
        return currentHealth;
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

    // toString is showStatus
    @Override
    public String toString() {
        return
            "Current room = " + currentRoom +
                "Health/MaxHealth = " + currentHealth + "/" + maxHealth +
                "Damage = " + currentDamage +
                "Weapon equipped = " + weaponEquip +
                "Poisoned = " + isPoisoned;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void use(String itemName) {
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getShortName()));
                item.use();
        }
    }



    public int go(String direction) {
        switch (direction) {
            case "north" -> {
                return checkNorth();
            }
            case "south" -> {
                return checkSouth();
            }
            case "east" -> {
                return checkEast();
            }
            case "west" -> {
                return checkWest();
            }
        }
        return 0;
    }

    private int checkNorth() {
        int canGo = 0;
        if (getCurrentRoom().getNorth() != null) {
            if (checkNorthIsLocked()) {
                canGo = 2;
            }
            else {
                setCurrentRoom(getCurrentRoom().getNorth());
                canGo = 1;
            }
        }
        return canGo;
    }
    private int checkSouth() {
        int canGo = 0;
        if (getCurrentRoom().getSouth() != null) {
            if (checkSouthIsLocked()) {
                canGo = 2;
            }
            else {
                setCurrentRoom(getCurrentRoom().getSouth());
                canGo = 1;
            }
        }
        return canGo;
    }
    private int checkEast() {
        int canGo = 0;
        if (getCurrentRoom().getEast() != null) {
            if (checkEastIsLocked()) {
                canGo = 2;
            }
            else {
                setCurrentRoom(getCurrentRoom().getEast());
                canGo = 1;
            }
        }
        return canGo;
    }
    private int checkWest() {
        int canGo = 0;
        if (getCurrentRoom().getWest() != null) {
            if (checkWestIsLocked()) {
                canGo = 2;
            }
            else {
                setCurrentRoom(getCurrentRoom().getWest());
                canGo = 1;
            }
        }
        return canGo;
    }

    private boolean checkNorthIsLocked() {
        return getCurrentRoom().getNorth().getIsLocked();
    }

    private boolean checkSouthIsLocked() {
        return getCurrentRoom().getSouth().getIsLocked();
    }

    private boolean checkEastIsLocked() {
        return getCurrentRoom().getEast().getIsLocked();
    }

    private boolean checkWestIsLocked() {
        return getCurrentRoom().getWest().getIsLocked();
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
