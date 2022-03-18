package com.company;

import java.util.ArrayList;

public class AdventureUI {

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


    public void displayExitGame() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
    }

    public void displayWinGame() {

    }

    public void displayLoseGame() {

    }

    public void displayTurnStartText(){
        System.out.println("What would you like to do? (type 'help' to view commands)");
    }

    public void displayCommandOptions(){
        for (Command command : Command.values()) {
            System.out.println(command.getCommandDescription());
        }
    }

    public void displayDropMessage(Item.ItemType itemType){
        System.out.println("Dropped " + itemType.toString() + ".");
    }

    public void displayDontHaveItem(Item.ItemType itemType) {
        System.out.println("Don't have " + itemType.toString() + ".");
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

    public void displayAteFood(Player player, int i) {
        System.out.println("Ate food and recovered " + player.inventory.get(i).getItemModifier() + " health.");
    }
    public void displayUseAntidoteToCure(){
        System.out.println("Used antidote to cure poison status effect.");
    }
    public void displayUseAntidote(){
        System.out.println("Used antidote.");
    }

    public void displayEquipKnife() {
        System.out.println("Equipping knife in right hand.");
    }

    public void displayUnequipKnife() {
        System.out.println("Unequipping knife.");
    }

    public void displayTookItem(Player player, int i) {
        System.out.println("Took " + player.getCurrentRoom().getItems().get(i).getItemType().toString() + ".");
    }
    public void displayItemNotHere(Item.ItemType itemType) {
        System.out.println("No " + itemType.toString() + " here.");
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
        System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage. " + currentHealth + " health remaining.");
    }

    public void displayRetaliateDeath(Room currentRoom, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has retaliated for " + currentRoom.getEnemies().get(i).getDamage() + " damage and killed you.");
    }

    public void displayEnemyDied(Room currentRoom, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has died.");
    }

    public void displayEnemyTookDamage(Room currentRoom, int i) {
        System.out.println(currentRoom.getEnemies().get(i).getEnemyType().toString() + " has " + currentRoom.getEnemies().get(i).getCurrentHealth() + " health remaining.");
    }

    public void displayPlayerDealDamage(Room currentRoom, int currentDamage, int i) {
        System.out.println("Attacking " + currentRoom.getEnemies().get(i).getEnemyType().toString() + " for " + currentDamage + " damage.");
    }

    public void displayInventory(ArrayList<Item> items) {
        System.out.println("Inventory contains:");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + " - " + items.get(i).toString());
        }
    }

    public void displayPlayerStatus(String status){
        System.out.println(status);
    }
    public void displayIsEmpty() {
        System.out.println("Is empty.");
    }

    public void displayPlayerMove(boolean moved, String direction){
        if (moved)
            System.out.println("You moved to the " + direction);

        else
            System.out.println("You couldn't move " + direction);
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

    public void displayEnemyInTheWay(Room currentRoom) {
        System.out.println("You try running past the " + currentRoom.getEnemies().get(0).getEnemyType().toString() + " but the door is locked.");
    }

    public void displayWalkIntoLockedDoor() {
        System.out.println("You try walking through the locked door, you hit your head.");
    }

    public void displayCurrentRoomDescription(Room currentRoom) {
        System.out.println(currentRoom.getRoomDescription());
    }
    public void displayCurrentRoomDescription(boolean moved, Room currentRoom) {
        if (moved) {
            System.out.println(currentRoom.getRoomDescription());
        }
    }

    public void displayNoSuchDirection(String direction) {
        System.out.println("You tried going " + direction + " but a wall is in the way");
    }
}


