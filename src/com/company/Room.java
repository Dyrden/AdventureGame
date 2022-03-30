package com.company;

import com.company.Enemies.Enemy;
import com.company.Items.Item;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private String longRoomDescription;
    private String shortRoomDescription;
    private String itemRoomDescription = "";
    private String enemyRoomDescription = "";
    private boolean isLocked;
    private String lockId;
    private Room northernRoom;
    private Room southernRoom;
    private Room easternRoom;
    private Room westernRoom;
    private boolean playerVisited;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Room(String name, String description, boolean locked, String id) {
        roomName = name;
        longRoomDescription = description;
        shortRoomDescription = description;
        isLocked = locked;
        lockId = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getLongRoomDescription() {
        if (items.size() == 0) { itemRoomDescription = ""; }
        else { itemRoomDescription = " You see the following items: "; }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (i == 0) {
                itemRoomDescription += item.getLongName();
            } else {
                itemRoomDescription += ", " + item.getLongName();
            }
        }
        if (!itemRoomDescription.equals("")) {
            itemRoomDescription += ".";
        }

        if (enemies.size() == 0) { enemyRoomDescription = ""; }
        else { enemyRoomDescription = " You see the following enemies: "; }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (i == 0) {
                enemyRoomDescription += enemy.getEnemyName();
            } else {
                enemyRoomDescription += ", " + enemy.getEnemyName();
            }
        }
        if (!enemyRoomDescription.equals("")) {
            enemyRoomDescription += ".";
        }
        longRoomDescription = shortRoomDescription + itemRoomDescription + enemyRoomDescription;
        return longRoomDescription;
    }

    public void setLongRoomDescription(String description) {
        longRoomDescription = description;
    }

    public void setNorth(Room room) {
        northernRoom = room;
    }

    public void setSouth(Room room) {
        southernRoom = room;
    }

    public void setEast(Room room) {
        easternRoom = room;
    }

    public void setWest(Room room) {
        westernRoom = room;
    }

    public Room getNorth() {
        return northernRoom;
    }

    public Room getSouth() {
        return southernRoom;
    }

    public Room getEast() {
        return easternRoom;
    }

    public Room getWest() {
        return westernRoom;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public String getLockId() {
        return lockId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public Item findItem(String itemName) {
        Item itemFound = null;
        for (Item item : getItems()) {
            if (itemName.equalsIgnoreCase(item.getShortName())) {
                itemFound = item;
                break;
            }
        }
        return itemFound;
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public void setIsLocked(boolean locked) {
        this.isLocked = locked;
    }

    public boolean isPlayerVisited() {
        return playerVisited;
    }

    public void setPlayerVisited(boolean playerVisited) {
        this.playerVisited = playerVisited;
    }

    public Room[] getDirections() {
        return new Room[]{northernRoom, southernRoom, easternRoom, westernRoom};
    }

    @Override
    public String toString() {
        return getLongRoomDescription();

    }



    public String getShortRoomDescription() {
        return shortRoomDescription;
    }

    public void setShortRoomDescription(String shortRoomDescription) {
        this.shortRoomDescription = shortRoomDescription;
    }
}
