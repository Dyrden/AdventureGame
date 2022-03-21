package com.company;

import com.company.Item.ItemType;

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

    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public boolean playerDealDamage(int i) {
        ui.displayPlayerDealDamage(currentRoom, currentDamage, i);
        boolean retaliate = false;
        if (currentRoom.getEnemies().get(i).getCurrentHealth() - currentDamage > 0) {
            currentRoom.getEnemies().get(i).setCurrentHealth(currentRoom.getEnemies().get(i).getCurrentHealth() - currentDamage);
            ui.displayEnemyTookDamage(currentRoom, i);
            retaliate = true;
        } else {
            ui.displayEnemyDied(currentRoom, i);
            currentRoom.getEnemies().get(i).setCurrentHealth(0);
            currentRoom.removeEnemy(i);
            currentRoom.updateRoomDescription();
        }
        return retaliate;
    }

    public void playerTakeDamage(int i) {
        if ((currentRoom.getEnemies().get(i) != null) && (currentHealth - currentRoom.getEnemies().get(i).getDamage() > 0)) {
            currentHealth -= currentRoom.getEnemies().get(i).getDamage();
            ui.displayRetaliate(currentRoom, currentHealth, i);
            if (currentRoom.getEnemies().get(i).getIsPoisonous()) {
                isPoisoned = true;
                applyPoisonDamage(5);
            }
        } else {
            currentHealth = 0;
            ui.displayRetaliateDeath(currentRoom, i);
        }
    }

    public void applyPoisonDamage(int poisonDmg) {
        if (isPoisoned) {
            if (currentHealth - poisonDmg > 0) {
                currentHealth -= poisonDmg;
                ui.displayTakePoisonDamage(poisonDmg, currentHealth);
            } else {
                currentHealth = 0;
                ui.displayTakePoisonDamageDeath(poisonDmg);
            }
        }
    }

    public void use(String useParameter) {
        switch (useParameter) {
            case "key" -> use(ItemType.KEY);
            case "antidote" -> use(ItemType.ANTIDOTE);
            case "knife" -> use(ItemType.KNIFE);
            case "food" -> use(ItemType.FOOD);
            default -> ui.displayCanOnlyUseInventoryItems();
        }
    }

    public void use(Item.ItemType itemType) {
        boolean hasItem = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == itemType) {
                hasItem = true;
                switch (itemType) {
                    case KEY -> inventory.get(i).useKey(currentRoom);
                    case FOOD -> inventory.get(i).useFood(i);
                    case ANTIDOTE -> inventory.get(i).useAntidote(i);
                    case KNIFE -> inventory.get(i).useWeapon(inventory.get(i).getItemType().toString(), i);
                }
            }
        }
        if (!hasItem) {
            ui.displayDontHaveItem(itemType);
        }
    }

    public void take(String useParameter) {
        switch (useParameter) {
            case "key" -> take(ItemType.KEY);
            case "antidote" -> take(ItemType.ANTIDOTE);
            case "knife" -> take(ItemType.KNIFE);
            case "food" -> take(ItemType.FOOD);
            default -> ui.displayCantTake(useParameter);
        }
    }

    public void take(ItemType itemType) {
        if (getCurrentRoom().getItems().size() > 0) {
            boolean isAvailable = false;
            for (int i = 0; i < getCurrentRoom().getItems().size(); i++) {
                if (getCurrentRoom().getItems().get(i).getItemType() == itemType) {
                    ui.displayTookItem(this, i);
                    inventory.add(getCurrentRoom().getItems().get(i));
                    getCurrentRoom().getItems().remove(i);
                    getCurrentRoom().updateRoomDescription();
                    isAvailable = true;
                    break;
                }
            }
            if (!isAvailable) {
                ui.displayItemNotHere(itemType);
            }
        } else {
            ui.displayNoItemHere();
        }
    }

    public void drop(String useParameter) {
        switch (useParameter) {
            case "key" -> drop(ItemType.KEY);
            case "antidote" -> drop(ItemType.ANTIDOTE);
            case "knife" -> drop(ItemType.KNIFE);
            case "food" -> drop(ItemType.FOOD);
            default -> ui.displayCantDrop(useParameter);
        }
    }
    public void drop(ItemType itemType) {
        if (inventory.size() > 0) {
            boolean isAvailable = false;
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getItemType() == itemType) {
                    ui.displayDropMessage(itemType);
                    getCurrentRoom().getItems().add(inventory.get(i));
                    inventory.remove(i);
                    getCurrentRoom().updateRoomDescription();
                    isAvailable = true;
                    break;
                }
            }
            if (!isAvailable) {
                ui.displayDontHaveItem(itemType);
            }
        } else {
            ui.displayNoSuchItemInInventory();
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
