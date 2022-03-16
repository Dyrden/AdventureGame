package com.company;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private final String roomDescription;
    private Room northernRoom;
    private Room southernRoom;
    private Room easternRoom;
    private Room westernRoom;
    private boolean isLocked;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;

    public Room(String name, String description, boolean locked) {
        roomName = name;
        roomDescription = description;
        isLocked = locked;
    }
    public String getRoomName() {
        return roomName;
    }
    public String getRoomDescription() {
        return roomDescription;
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
    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    public void removeEnemy(int index) {
        enemies.remove(index);
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }
}
