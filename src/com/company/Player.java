package com.company;

import com.company.Items.Item;
import com.company.Items.Weapon;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;

    private int currentHealth;
    private int maxHealth = 100;
    private boolean isPoisoned = false;

    private int currentDamage;
    private final int baseDamage = 1;
    private Item weaponEquip = null;

    public ArrayList<Item> inventory = new ArrayList<>();


    public Player(Room currentRoom) {
        this.currentHealth = maxHealth;
        this.currentRoom = currentRoom;
        this.currentDamage = baseDamage;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public Item getWeaponEquip() {
        return weaponEquip;
    }

    public void setWeaponEquip(Weapon weaponEquip) {
        this.weaponEquip = weaponEquip;
        if (weaponEquip != null) {
            this.currentDamage = weaponEquip.getWeaponModifier();
        } else {
            this.currentDamage = baseDamage;
        }
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

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
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                item.use();
            }
        }
    }

    public int go(String direction) {
        int canGo;
        switch (direction) {
            case "north" -> canGo = checkNorth();
            case "south" -> canGo = checkSouth();
            case "east" -> canGo = checkEast();
            case "west" -> canGo = checkWest();
            default -> canGo = 0;
        }
        return canGo;
    }

    private int checkNorth() {
        int canGo = 0;
        if (getCurrentRoom().getNorth() != null) {
            if (checkNorthIsLocked()) {
                canGo = 2;
            } else {
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
            } else {
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
            } else {
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
            } else {
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


}
