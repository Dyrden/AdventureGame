package com.company;

import com.company.Items.Equipables.Equipment;
import com.company.Items.Equipables.Type.Armor;
import com.company.Items.Equipables.Type.Weapon;
import com.company.Items.Item;
import com.company.Items.Usables.Type.Perishables.Antidote;
import com.company.Items.Usables.Type.Perishables.Food;
import com.company.Items.Usables.Type.Perishables.HealthType;
import com.company.Items.Usables.Type.Reusables.Key;

import java.util.ArrayList;

public class Player {

    private Room currentRoom;

    private int currentHealth;
    private int maxHealth;
    private boolean isPoisoned;

    private int currentDamage;
    private final int baseDamage = 1;
    private Weapon weaponEquip;
    private Armor armorEquip;

    public ArrayList<Item> inventory = new ArrayList<>();


    public Player(Room currentRoom) {
        maxHealth = 100;
        isPoisoned = false;
        weaponEquip = null;
        armorEquip = null;
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


    public Equipment equip(String itemName) {
        Equipment equip = null;
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName) && item instanceof Equipment) {
                equip = (Equipment) item;
                armorOrWeapon(equip);
            }
        }
        return equip;
    }

    private void armorOrWeapon(Equipment equipment) {
        if (equipment instanceof Weapon) {
            setWeaponEquip((Weapon) equipment);
        } else if (equipment instanceof Armor) {
            setArmorEquip((Armor) equipment);
        }
    }

    private void setArmorEquip(Armor armorEquip) {
        this.armorEquip = armorEquip;
    }

    private void setWeaponEquip(Weapon weaponEquip) {
        this.weaponEquip = weaponEquip;
        if (weaponEquip != null) {
            setCurrentDamage(weaponEquip.getWeaponValue());
        } else {
            setCurrentDamage(baseDamage);
        }
    }


    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }


    @Override
    public String toString() {
        return
            "Current room = " + currentRoom.getShortRoomDescription() + "\n" +
                "Health/MaxHealth = " + currentHealth + "/" + maxHealth + "\n" +
                "Damage = " + currentDamage + "\n" +
                "Weapon equipped = " + weaponEquip + "\n" +
                "Poisoned = " + isPoisoned;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item use(String itemName) {
        Item usedItem = null;
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                usedItem = item;
                checkItemType(item);
                removeFromInventory(item);
            }
        }
        return usedItem;
    }

    private void checkItemType(Item item) {
        if (item instanceof Food) {
            useFood((Food) item);
        } else if (item instanceof Antidote) {
            useAntidote();
        } else if (item instanceof Key) {
            useKey((Key) item);
        }
    }

    private void useKey(Key key) {
        for (Room room : this.currentRoom.getDirections()) {
            if (room.getIsLocked()) {
                if (key.getKeyId().equalsIgnoreCase(room.getLockId())) {
                    room.setIsLocked(false);
                }
            }
        }
    }

    private void useAntidote() {
        setPoisoned(false);
    }


    private void useFood(Food food) {
        if (food.getType() == HealthType.CURRENT)
            setCurrentHealth(getCurrentHealth() + food.use());
        if (food.getType() == HealthType.MAX)
            setMaxHealth(getMaxHealth() + food.use());
    }

    private void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    private Item findItemInInventory(String itemName) {
        Item currentItem = null;
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                currentItem = item;
            }
        }
        return currentItem;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
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


    public Item drop(String input) {
        Item item = findItemInInventory(input);
        if (item != null) {
            removeFromInventory(item);
            currentRoom.addItem(item);
        }
        return item;
    }
}
