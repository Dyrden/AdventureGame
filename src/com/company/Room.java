package com.company;

public class Room {
    String roomName;
    String roomDescription;
    Room northernRoom;
    Room southernRoom;
    Room easternRoom;
    Room westernRoom;

    public Room(String name, String description, Room north, Room south, Room east, Room west) {
        roomName = name;
        roomDescription = description;
        northernRoom = north;
        southernRoom = south;
        easternRoom = east;
        westernRoom = west;
    }
}
