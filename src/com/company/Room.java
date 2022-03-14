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
}
