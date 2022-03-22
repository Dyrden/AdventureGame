package com.company;

import com.company.Enemies.Enemy;
import com.company.Items.Item;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private String longRoomDescription;
    private String shortRoomDescription;
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
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }
    public void removeEnemy(int index) {
        this.enemies.remove(index);
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
