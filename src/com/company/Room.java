package com.company;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private String roomDescription;
    private Room northernRoom;
    private Room southernRoom;
    private Room easternRoom;
    private Room westernRoom;
    private boolean isLocked;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private boolean playerVisited;

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
    public void setRoomDescription(String description) {
        roomDescription = description;
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
}
