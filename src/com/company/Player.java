package com.company;

import com.company.Enemies.Enemy;
import com.company.Items.Equipables.Equipment;
import com.company.Items.Equipables.Type.Armor;
import com.company.Items.Equipables.Type.Weapon;
import com.company.Items.Equipables.Type.Weapons.RangedWeapon;
import com.company.Items.Item;
import com.company.Items.Usables.Type.Perishables.Antidote;
import com.company.Items.Usables.Type.Perishables.Food;
import com.company.Items.Usables.Type.Reusables.Key;

import java.util.ArrayList;

public class Player {

    private Room currentRoom;

    private int currentHealth;
    private int maxHealth;
    private boolean isPoisoned;
    private boolean poisonCleared;


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
        poisonCleared = false;
        this.currentHealth = maxHealth;
        this.currentRoom = currentRoom;
        this.currentDamage = baseDamage;

    }

    @Override
    public String toString() {
        if (weaponEquip instanceof RangedWeapon) {
            return
                    "Current room = " + currentRoom.getShortRoomDescription() + "\n" +
                            "Health/MaxHealth = " + currentHealth + "/" + maxHealth + "\n" +
                            "Damage = " + currentDamage + "\n" +
                            "Weapon equipped = " + weaponEquip + " - Ammunition = " + ((RangedWeapon) weaponEquip).getAmmunition() + "\n" +
                            "Poisoned = " + isPoisoned;
        }
        else {
            return
                    "Current room = " + currentRoom.getShortRoomDescription() + "\n" +
                            "Health/MaxHealth = " + currentHealth + "/" + maxHealth + "\n" +
                            "Damage = " + currentDamage + "\n" +
                            "Weapon equipped = " + weaponEquip + "\n" +
                            "Poisoned = " + isPoisoned;
        }
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

    public boolean getIsPoisoned() {
        return isPoisoned;
    }

    public void setIsPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    public boolean isPoisonCleared() {
        return poisonCleared;
    }

    public void setPoisonCleared(boolean poisonCleared) {
        this.poisonCleared = poisonCleared;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Weapon getWeaponEquip() {
        return weaponEquip;
    }

    public Armor getArmorEquip() {
        return armorEquip;
    }

    public Equipment equip(String itemName) {
        Equipment equip = null;
        if (findItemInInventory(itemName) instanceof Equipment equipment) {
            armorOrWeapon(equipment);
            equip = equipment;
        }
        return equip;
    }

    private void armorOrWeapon(Equipment equipment) {
        if (equipment instanceof Weapon weapon) {
            setWeaponEquip(weapon);
        } else if (equipment instanceof Armor armor) {
            setArmorEquip(armor);
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

    public String use(String itemName) {
        String usedItem = "";
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                usedItem = checkItemType(item);
            }
        }
        return usedItem;
    }


    private String checkItemType(Item item) {
        String str = "";
        if (item instanceof Food food) {
            str = String.valueOf(eat(food));
        } else if (item instanceof Antidote antidote) {
            str = useAntidote(antidote);
        } else if (item instanceof Key key) {
            str = useKey(key);
        }
        return str;
    }

    private String useKey(Key key) {
        String usedKey = "";
        for (Room room : this.currentRoom.getDirections()) {
            if (room != null) {
                if (this.currentRoom.getEnemies().size() == 0) {
                    if (room.getIsLocked()) {
                        if (key.getKeyId().equalsIgnoreCase(room.getLockId())) {
                            usedKey = key + " successfully used on locked room.";
                            room.setIsLocked(false);
                        } else {
                            usedKey = key + " does not fit the locked room.";
                        }
                        break;
                    }
                    usedKey = key + " cannot be used on anything here.";
                }
                usedKey = key + " cannot be used while an enemy is in the way.";
            }
        }
        return usedKey;
    }

    private String useAntidote(Antidote antidote) {
        String usedAntidote;
        if (getIsPoisoned()) {
            setIsPoisoned(false);
            setPoisonCleared(true);
            usedAntidote = "You successfully used " + antidote;
        } else {
            setPoisonCleared(false);
            usedAntidote = "You successfully used " + antidote + " but you weren't poisoned";
        }
        return usedAntidote;
    }

    public int eat(Food food) {
        return food.getHealAmount();
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    public Item findItemInInventory(String itemName) {
        Item currentItem = null;
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                currentItem = item;
            }
        }
        return currentItem;
    }

    public GoToSuccess go(String direction) {
        GoToSuccess success;
        switch (direction) {
            case "north" -> success = checkNorth();
            case "south" -> success = checkSouth();
            case "east" -> success = checkEast();
            case "west" -> success = checkWest();
            default -> success = GoToSuccess.FAILURE;
        }
        return success;
    }

    private GoToSuccess checkNorth() {
        GoToSuccess success = GoToSuccess.FAILURE;
        if (getCurrentRoom().getNorth() != null) {
            if (checkNorthIsLocked()) {
                success = GoToSuccess.LOCKED;
            } else {
                setCurrentRoom(getCurrentRoom().getNorth());
                success = GoToSuccess.SUCCESS;
            }
        }
        return success;
    }

    private GoToSuccess checkSouth() {
        GoToSuccess success = GoToSuccess.FAILURE;
        if (getCurrentRoom().getSouth() != null) {
            if (checkSouthIsLocked()) {
                success = GoToSuccess.LOCKED;
            } else {
                setCurrentRoom(getCurrentRoom().getSouth());
                success = GoToSuccess.SUCCESS;
            }
        }
        return success;
    }

    private GoToSuccess checkEast() {
        GoToSuccess success = GoToSuccess.FAILURE;
        if (getCurrentRoom().getEast() != null) {
            if (checkEastIsLocked()) {
                success = GoToSuccess.LOCKED;
            } else {
                setCurrentRoom(getCurrentRoom().getEast());
                success = GoToSuccess.SUCCESS;
            }
        }
        return success;
    }

    private GoToSuccess checkWest() {
        GoToSuccess success = GoToSuccess.FAILURE;
        if (getCurrentRoom().getWest() != null) {
            if (checkWestIsLocked()) {
                success = GoToSuccess.LOCKED;
            } else {
                setCurrentRoom(getCurrentRoom().getWest());
                success = GoToSuccess.SUCCESS;
            }
        }
        return success;
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

    public Item take(String itemName) {
        Item foundItem = null;
        foundItem = getCurrentRoom().findItem(itemName);
        if (foundItem != null) {
            inventory.add(foundItem);
            getCurrentRoom().removeItem(foundItem);
        }
        return foundItem;
    }

    public boolean attack(Enemy enemy) {
        boolean isDead = false;
        if (enemy.getCurrentHealth() - currentDamage > 0) {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - currentDamage);
        }
        else {
            enemy.setCurrentHealth(0);
            isDead = true;
        }
        return isDead;
    }

    public int poisonTick() {
        int poisonDamage = 0;
        if (isPoisoned) {
            poisonDamage = 5;
            currentHealth -= poisonDamage;
        }
        return poisonDamage;
    }
}
