package com.company;

public class Room {
    private final String roomName;
    private final String roomDescription;
    private Room northernRoom;
    private Room southernRoom;
    private Room easternRoom;
    private Room westernRoom;
    private boolean playerVisited;

    public Room(String name, String description) {
        roomName = name;
        roomDescription = description;
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

    public boolean isPlayerVisited() {
        return playerVisited;
    }

    public void setPlayerVisited(boolean playerVisited) {
        this.playerVisited = playerVisited;
    }
}
