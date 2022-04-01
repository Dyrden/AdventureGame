package com.company;

import com.company.DungeonGenerator.DungeonGenerator;
import com.company.DungeonGenerator.DungeonSize;
import com.company.Enemies.Mob;
import com.company.Items.Equipables.Type.Weapons.MeleeWeapon;
import com.company.Items.Equipables.Type.Weapons.RangedWeapon;
import com.company.Items.Item;
import com.company.Items.Usables.Type.Perishables.Antidote;
import com.company.Items.Usables.Type.Perishables.Food;
import com.company.Items.Usables.Type.Reusables.Key;

import java.util.HashMap;
import java.util.Scanner;

public class Adventure {

    private final Scanner playerInput = new Scanner(System.in);
    private final AdventureUI UI = new AdventureUI();
    private boolean gameRunning = true;

    private final int amountOfRooms = 9;
    private Room[] rooms = new Room[amountOfRooms];
    private Room winRoom;
    public Player player = new Player(rooms[0]);
    HashMap<int[], Room> dungeon = new DungeonGenerator(DungeonSize.SMALL).createAndGetDungeon();

    public void run() {
        UI.displayGameIntro();
        createAndConnectRooms();
        UI.displayTurnShift();
        while (gameRunning) {
            UI.displayTurnStartText();
            commands(input());
            UI.displayTurnShift();
        }
    }

    private String[] input() {
        String[] split = playerInput.nextLine().toLowerCase().split(" ", 2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }

    private void help() {
        UI.displayCommandOptions();
    }

    private void commands(String[] command) {
        switch (command[0]) {
            case "go" -> go(command[1]);
            case "look" -> UI.displayRoomStatus(player.getCurrentRoom());
            case "status" -> UI.displayPlayerStatus(player.toString());
            case "inventory" -> UI.displayInventory(player.getInventory());
            case "take" -> UI.displayPlayerTakeItem(player.take(command[1]));
            case "use" -> use(command[1]);
            case "drop" -> player.drop(command[1]);
            case "attack" -> attack(command[1]);
            case "equip" -> UI.displayEquippedItem(player.equip(command[1]));
            case "help" -> help();
            case "exit", "quit" -> exit();
            default -> UI.displayNoSuchCommand();
        }
    }

    private void go(String command1) {
        GoToSuccess success = player.go(command1);
        UI.displayPlayerMove(success, command1);
        UI.displayCurrentRoomDescription(success, player.getCurrentRoom());
        if (success == GoToSuccess.SUCCESS) {
            if (player.getCurrentRoom() == winRoom) {
                gameWin();
            }
            else {
                poisonTick();
            }
        }
    }

    private void poisonTick() {
        int poisonDmg = player.poisonTick();
        if (poisonDmg > 0) {
            if (player.getCurrentHealth() > 0) {
                UI.displayTakePoisonDamage(poisonDmg, player.getCurrentHealth());
            } else {
                UI.displayTakePoisonDamageDeath(poisonDmg);
                gameLose();
            }
        }
    }

    private void use(String command1) {
        Item item = player.findItemInInventory(command1);
        if (item instanceof Food food) {
            UI.displayPlayerEat(food.getLongName(), player.getCurrentHealth());
            player.inventory.remove(food);
        } else if (item instanceof Antidote antidote) {
            UI.displayPlayerUseItem(player.use(command1));
            player.inventory.remove(antidote);
        } else if (item instanceof Key) {
            UI.displayPlayerUseItem(player.use(command1));
        }
    }

    private void gameWin() {
        UI.displayWinGame();
        exit();
    }

    private void gameLose() {
        UI.displayLoseGame();
        exit();
    }

    private void exit() {
        UI.displayExitGame();
        gameRunning = false;
        UI.displayPlayOrExit();
    }

    private void attack(String enemy) {
        if (enemy.equals("")) {
            UI.displaySpecifyAnEnemy();
        }
        else {
            if (player.getWeaponEquip() != null) {
                if (player.getWeaponEquip().canBeUsed()) {
                    checkForEnemyToAttack(enemy);
                }
                else {
                    UI.displayCantUseWeapon(player.getWeaponEquip());
                }
            }
            else {
                checkForEnemyToAttack(enemy);
            }
        }
    }

    private void checkForEnemyToAttack(String enemy) {
        boolean foundEnemy = false;
        if (player.getCurrentRoom().getEnemies().size() > 0) {
            for (int i = 0; i < player.getCurrentRoom().getEnemies().size(); i++) {
                if (enemy.equals(player.getCurrentRoom().getEnemies().get(i).getEnemyName())) {
                    foundEnemy = true;
                    if (player.attack(player.getCurrentRoom().getEnemies().get(i))) {
                        UI.displayEnemyDied(player.getCurrentRoom(), i);
                        player.getCurrentRoom().getEnemies().remove(i);
                    } else {
                        UI.displayPlayerDealDamage(player.getCurrentRoom(), player.getCurrentDamage(), i);
                        player.getCurrentRoom().getEnemies().get(i).attack(player);
                        if (player.getCurrentHealth() < 1) {
                            UI.displayRetaliateDeath(player.getCurrentRoom(), i);
                            gameLose();
                        } else {
                            UI.displayRetaliate(player.getCurrentRoom(), player.getCurrentHealth(), i);
                        }
                    }
                    break;
                }
            }
        }
        if (!foundEnemy) {
            UI.displayNoSuchEnemy(enemy);
        }
    }

    private void createAndConnectRooms() {
        createRooms();
        connectRooms();
        createItems();
        createEnemies();
        player.setCurrentRoom(rooms[0]);
    }

    private void createRooms() {
        rooms[0] = new Room("Hole", "You fell in this hole and your possessions now seperated from you.", false, "");
        rooms[1] = new Room("Cave", "Dank dark cavern.", false, "");
        rooms[2] = new Room("Crawl space", "You are in a tight crawl space.", false, "");
        rooms[3] = new Room("Sewer", "You entered a sewer.", false, "");
        rooms[4] = new Room("Treasure Chamber", "You've stumbled upon a treasure like no other. You won't ever have to work again.", true, "gold");
        winRoom = rooms[4];
        rooms[5] = new Room("Security", "You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar.", false, "");
        rooms[6] = new Room("Sewage filtration", "You've entered a room with a machine filtrating the sewage.", false, "");
        rooms[7] = new Room("Golden Door", "You find yourself in a room with a locked giant golden door.", false, "");
        rooms[8] = new Room("Back-alley", "You entered a back-alley, seems to connect important areas of this complex.", false, "");
    }

    private void connectRooms() {
        rooms[0].setEast(rooms[1]);
        rooms[0].setSouth(rooms[3]);

        rooms[1].setEast(rooms[2]);

        rooms[2].setSouth(rooms[5]);

        rooms[3].setSouth(rooms[6]);

        rooms[4].setSouth(rooms[7]);

        rooms[5].setSouth(rooms[8]);

        rooms[6].setEast(rooms[7]);

        rooms[7].setWest(rooms[6]);

        rooms[7].setEast(rooms[8]);
    }

    private void createEnemies() {
        rooms[1].addEnemy(new Mob("bat", 10, 9, true));
        rooms[1].addEnemy(new Mob("bat", 10, 9, true));
        rooms[3].addEnemy(new Mob("rat", 5, 5, false));
        rooms[6].addEnemy(new Mob("rat", 5, 5, false));
        rooms[7].addEnemy(new Mob("snake", 50, 10, true));
    }

    private void createItems() {
        rooms[2].addItem(new Antidote("antidote", "an abandoned antidote"));
        rooms[5].addItem(new Key("key", "a golden key", "gold"));
        rooms[6].addItem(new Food("food", "a plate of food", 20));
        rooms[8].addItem(new MeleeWeapon("knife", "a knife", 10, 25));
        rooms[1].addItem(new RangedWeapon("gun", "a gun", 20, 20, 1));
    }
}
