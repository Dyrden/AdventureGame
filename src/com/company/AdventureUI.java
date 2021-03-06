package com.company;

import com.company.Items.Equipables.Equipment;
import com.company.Items.Equipables.Type.Weapon;
import com.company.Items.Equipables.Type.Weapons.RangedWeapon;
import com.company.Items.Item;

import java.util.ArrayList;

public class AdventureUI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void displayGameIntro() {
        System.out.println("\nWelcome to the Adventure of your lifetime.");
        System.out.println("\nYou are walking around in the forest near your town.");
        System.out.println("After wandering for hours, you decided to take a break, laying down your backpack.");
        System.out.println("After a short break, you decide to set up camp and go to look for firewood.");
        System.out.println("All of a sudden, the feeling of the ground pushing against your feet fails you.");
        System.out.println("The sensation of falling is the last thing you remember\n");
    }

    public void displayNoSuchCommand() {
        System.out.println("No such command is available, try again.");
    }

    public void displayRoomStatus(Room room) {
        System.out.println(room);
    }

    public void displayExitGame() {
        System.out.println("Thanks for playing.");
    }

    public void displayWinGame() {
        System.out.println("You have won the game!");
    }

    public void displayLoseGame() {
        System.out.println("You have lost the game!");
    }

    public void displayTurnStartText() {
        System.out.println("What would you like to do? (type 'help' to view commands)");
    }

    public void displayPlayerUseItemFoodCurrent(Item item) {
        System.out.println("You used " + item.getShortName() + ", and changed your current health.");
    }

    public void displayPlayerUseItem(String item) {
        System.out.println(item);
    }
    public void displayPlayerUseItemFoodMax(Item item) {
        System.out.println("You used " + item.getShortName() + ", and changed your max health.");
    }

    public void displayPlayerUseItemAntidoteClear(Item item) {
        System.out.println("You used " + item.getShortName() + ", and cleansed your poisoned status.");
    }

    public void displayPlayerUseItemAntidote(Item item) {
        System.out.println("You used " + item.getShortName() + ", but you weren't poisoned to begin with.");
    }

    public void displayPlayerUseItemKeyUnlocked(Item item) {
        System.out.println("You used " + item.getShortName() + ", and it fits the locked room, unlocking it.");
    }

    public void displayPlayerUseItemKeyAlreadyUnlocked(Item item) {
        System.out.println("You used " + item.getShortName() + ", but the room it fits is already unlocked.");
    }

    public void displayPlayerUseItemKeyNoMatch(Item item) {
        System.out.println("You used " + item.getShortName() + ", but the key doesnt fit.");
    }

    public void displayPlayerNoSuchItem() {
        System.out.println("You have no such item on your person.");
    }

    public void displayCommandOptions() {
        for (Command command : Command.values()) {
            System.out.println(command.getCommandDescription());
        }
    }

    public void displayPlayerTakeItem(Item item) {
        if (item != null) {
            System.out.println("You took " + item.getLongName() + " from the room.");
        } else {
            System.out.println("You dont see any such thing in the room");
        }
    }

    public void displayDropMessage(Item item) {
        System.out.println("Dropped " + item.toString().toLowerCase() + ".");
    }

    public void displayDontHaveItem(Item item) {
        System.out.println("Don't have " + item.toString().toLowerCase() + ".");
    }

    public void displayDoorHasUnlocked() {
        System.out.println("The door has unlocked.");
    }

    public void displayNoLockedDoor() {
        System.out.println("No lock to use the key in.");
    }

    public void displayCantUnlockDoorBecauseOfEnemy() {
        System.out.println("Can't do that right now. An adversary is in the way.");
    }

    public void displayEquipWeapon(String weapon) {
        System.out.println("Equipping " + weapon.toLowerCase() + " in right hand.");
    }

    public void displayUnequipWeapon(String weapon) {
        System.out.println("Unequipping " + weapon.toLowerCase() + ".");
    }

    public void displayItemNotHere(Item item) {
        System.out.println("No " + item.toString().toLowerCase() + " here.");
    }

    public void displayEquippedItem(Equipment equip) {
        if (equip != null) {
            System.out.println("You equipped : " + equip);
        } else {
            System.out.println("You've got no such equipment");
        }
    }

    public void displayTurnShift() {
        System.out.println(ANSI_YELLOW + "-----------------------------------------------" + ANSI_RESET);
    }

    public void displayNoItemHere() {
        System.out.println("Nothing to pick up here.");
    }

    public void displayNoSuchItemInInventory() {
        System.out.println("No such item in inventory.");
    }

    public void displayCanOnlyUseInventoryItems() {
        System.out.println("You can only use items you have in your inventory.");
    }

    public void displayCantTake(String useParameter) {
        System.out.println("You can't take " + useParameter + ".");
    }

    public void displayCantDrop(String useParameter) {
        System.out.println("You can't drop " + useParameter + ".");
    }

    public void displayTakePoisonDamage(int poisonDmg, int currentHealth) {
        System.out.println("You take " + poisonDmg + " poison damage. " + currentHealth + " health remaining.");
    }

    public void displayTakePoisonDamageDeath(int poisonDmg) {
        System.out.println("You took " + poisonDmg + " poison damage and died from it.");
    }

    public void displayRetaliate(Room currentRoom, int currentHealth, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyName() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage. " + currentHealth + " health remaining.");
    }

    public void displayRetaliateDeath(Room currentRoom, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyName() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage and killed you.");
    }

    public void displayEnemyDied(Room currentRoom, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyName() + " has died.");
    }

    public void displayPlayerDealDamage(Room currentRoom, int currentDamage, int i) {
        System.out.println("Attacking " + currentRoom.getEnemies().get(i).getEnemyName() + " for " + currentDamage + " damage. " + currentRoom.getEnemies().get(i).getEnemyName() + " has " + currentRoom.getEnemies().get(i).getCurrentHealth() + " health remaining.");
    }

    public void displayNoSuchEnemy(String enemy) {
        System.out.println("Could not find any " + enemy + ".");
    }

    public void displaySpecifyAnEnemy() {
        System.out.println("Please specify an enemy.");
    }

    public void displayInventory(ArrayList<Item> items) {
        System.out.println("Inventory contains:");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + " - " + items.get(i).toString());
        }
    }

    public void displayPlayerStatus(String status) {
        System.out.println(status);
    }

    public void displayIsEmpty() {
        System.out.println("Is empty.");
    }

    public void displayPlayerMove(GoToSuccess success, String direction) {
        if (success == GoToSuccess.SUCCESS) {
            System.out.println("You moved to the " + direction + ".");
        } else if (success == GoToSuccess.LOCKED) {
            System.out.println("You couldn't move " + direction + ". A door stands in your way and it is locked.");
        } else {
            System.out.println("You couldn't move " + direction + ".");
        }
    }

    public void displayWeaponEquipped(String weapon) {
        System.out.println("Has " + weapon + " equipped.");
    }

    public void displayNoWeaponEquipped() {
        System.out.println("No weapon equipped.");
    }

    public void displayPoisoned() {
        System.out.println("Poisoned. Use antidote to cure.");
    }

    public void displayHealth(int currentHealth) {
        System.out.println("Health: " + currentHealth);
    }

    public void displayNothingToAttack() {
        System.out.println("Nothing to attack here.");
    }

    public void displayNotValidDirection(String direction) {
        System.out.println(direction + " is not a cardinal direction, rethink your choices.");
    }

    public void displayWalkIntoLockedDoor() {
        System.out.println("You try walking through the locked door, you hit your head.");
    }

    public void displayCurrentRoomDescription(Room currentRoom) {
        System.out.println(currentRoom.getLongRoomDescription());
    }

    public void displayCurrentRoomDescription(GoToSuccess success, Room currentRoom) {
        if (success == GoToSuccess.SUCCESS) {
            System.out.println(currentRoom.getLongRoomDescription());
        }
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }

    public void displayPlayerEat(String eating, int currentHealth) {
        System.out.println("You ate " + eating + ", changing your health to " + currentHealth);
    }

    public void displayCantUseWeapon(Weapon weapon) {
        System.out.print("Can't use " + weapon.getShortName() + " to attack. ");
        if (weapon instanceof RangedWeapon) {
            System.out.println("You have no ammunition.");
        }
    }

    public void displayGameMenuIntro(){
        System.out.println("""
            Welcome to Adventure!
                Glory awaits
            """);
    }

    public void displayPlayOrExit(){
        System.out.println("Type 'play' to start a game or 'exit' to quit the program");
    }

    public void displayNotAnOption(){
        System.out.println("Not an option - type 'play' or 'exit'");
    }
}


