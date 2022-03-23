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
        itemRoomDescription = " You see the following items: ";
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                itemRoomDescription += item.getLongName();
            } else {
                itemRoomDescription += ", " + item.getLongName();
            }
        }
        if (!itemRoomDescription.equals("")) {
            itemRoomDescription += ".";
        }
        longRoomDescription = shortRoomDescription + itemRoomDescription + enemyRoomDescription;
    }
    public void removeItem(Item item) {
        this.items.remove(item);
        if (items.size() == 0) { itemRoomDescription = ""; }
        else { itemRoomDescription = " You see the following items: "; }
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                itemRoomDescription += item.getLongName();
            } else {
                itemRoomDescription += ", " + item.getLongName();
            }
        }
        if (!itemRoomDescription.equals("")) {
            itemRoomDescription += ".";
        }
        longRoomDescription = shortRoomDescription + itemRoomDescription + enemyRoomDescription;
    }
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
        enemyRoomDescription = " You see the following enemies: ";
        for (int i = 0; i < enemies.size(); i++) {
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
    }
    public void removeEnemy(int index) {
        Enemy enemy = enemies.get(index);
        this.enemies.remove(index);
        if (enemies.size() == 0) { enemyRoomDescription = ""; }
        else { enemyRoomDescription = " You see the following enemies: "; }
        for (int i = 0; i < enemies.size(); i++) {
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

    public Room[] getDirections(){
        return new Room[]{northernRoom,southernRoom,easternRoom,westernRoom};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Enemy enemy : enemies) {
            sb.append(enemy.toString() + ", ");
        }
        if (playerVisited) {
            for (Item item : items) {
                sb.append(item.toString() + ", ");
            }
            return getLongRoomDescription() + sb ;
        } else {
            return getShortRoomDescription() + sb;
        }
    }



    public String getShortRoomDescription() {
        return shortRoomDescription;
    }

    public void setShortRoomDescription(String shortRoomDescription) {
        this.shortRoomDescription = shortRoomDescription;
    }
}
