package com.company;

public class Room {
    String roomName;
    String roomDescription;
    Room northernRoom;
    Room southernRoom;
    Room easternRoom;
    Room westernRoom;

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
    public Room getNorth(Room room) {
        return northernRoom;
    }
    public Room getSouth(Room room) {
        return southernRoom;
    }
    public Room getEast(Room room) {
        return easternRoom;
    }
    public Room getWest(Room room) {
        return westernRoom;
    }
}
