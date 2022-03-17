package com.company;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private final int maxHealth = 100;
    private int currentHealth;
    private int currentDamage = 1;
    private boolean isPoisoned = false;
    private ArrayList<Item> inventory = new ArrayList<Item>();
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

    public void use(Item.ItemType itemType) {
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
            if (inventory.get(i).getItemType() == Item.ItemType.food) {
                currentHealth += inventory.get(i).getItemModifier();
                System.out.println("Ate food and recovered " + inventory.get(i).getItemModifier() + " health.");
                inventory.remove(i);
                break;
            }
        }
    }

    private void useAntidote() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == Item.ItemType.antidote) {
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
        if (currentDamage == baseDamage) {
            System.out.println("Equipping knife in right hand.");
            currentDamage = inventory.get(i).getItemModifier();
        } else {
            System.out.println("Unequipping knife.");
            currentDamage = baseDamage;
        }
    }

    public void take(Item.ItemType itemType) {
        if (currentRoom.getItems().size() > 0) {
            boolean isAvailable = false;
            for (int i = 0; i < currentRoom.getItems().size(); i++) {
                if (currentRoom.getItems().get(i).getItemType() == itemType) {
                    System.out.println("Took " + currentRoom.getItems().get(i).getItemType().toString() + ".");
                    inventory.add(currentRoom.getItems().get(i));
                    currentRoom.getItems().remove(i);
                    currentRoom.updateRoomDescription();
                    isAvailable = true;
                    break;
                }
            }
            if (!isAvailable) {
                System.out.println("No " + itemType.toString() + " here.");
            }
        } else {
            System.out.println("Nothing to pick up here.");
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
}
