package com.company;

import com.company.Item.ItemType;

import java.util.ArrayList;

public class Player {
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

    private void removeItemFromInventory2(Item item) {
        inventory.remove(item);
    }


    public void showStatus() {
        System.out.println("Status:");
        System.out.println("Health: " + currentHealth);
        if (isPoisoned) {
            System.out.println("Poisoned. Use antidote to cure.");
        }
        if (weaponEquip == null) {
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

    public boolean go(String direction) {
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
        return false;
    }

    private boolean checkNorth() {
        boolean canGo = false;
        if (getCurrentRoom().getNorth() != null) {
            setCurrentRoom(getCurrentRoom().getNorth());
            canGo = true;
        }
        return canGo;
    }
    private boolean checkSouth() {
        boolean canGo = false;
        if (getCurrentRoom().getSouth() != null) {
            setCurrentRoom(getCurrentRoom().getSouth());
            canGo = true;
        }
        return canGo;
    }
    private boolean checkEast() {
        boolean canGo = false;
        if (getCurrentRoom().getEast() != null) {
            setCurrentRoom(getCurrentRoom().getEast());
            canGo = true;
        }
        return canGo;
    }
    private boolean checkWest() {
        boolean canGo = false;
        if (getCurrentRoom().getWest() != null) {
            setCurrentRoom(getCurrentRoom().getWest());
            canGo = true;
        }
        return canGo;
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
