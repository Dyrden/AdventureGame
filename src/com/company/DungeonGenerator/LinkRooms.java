package com.company.DungeonGenerator;

import com.company.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class LinkRooms {

    private Random random = new Random();
    private HashMap<int[], Room> map = new HashMap<>();




    public HashMap<int[],Room> linkRooms(ArrayList<Room> rooms) {
        Room starterRoom = getRoom(rooms);
        int[] startPosition = {0, 0};

        map.put(startPosition, starterRoom);

        int amountOfRooms;
        do {
            ArrayList<Room> listOfMappedRooms = new ArrayList<>(map.values());

            Room randomRoomInMap = listOfMappedRooms.get(random.nextInt(listOfMappedRooms.size()));
            int[] keyPosition = findPositionByRoom(randomRoomInMap);
            CardinalDirections[] cd = CardinalDirections.values();

            ArrayList<Integer> possibleDirectionsToMap = possibleDirectionsToMap(randomRoomInMap);

            for (int direction : possibleDirectionsToMap) {
                Room room = getRoom(rooms);
                attachRoom(randomRoomInMap, direction, room);
                int[] newCoordinate = calculateCoordinate(keyPosition, cd[direction]);
                map.put(newCoordinate, room);
            }
            amountOfRooms = rooms.size();
        }
        while (amountOfRooms > 3); //kind of a magic number, but we dont want to run the loop again
        // with, lets say 1 room left and then try to add 3 rooms to the random room we found

        return map;
    }


    private int[] findPositionByRoom(Room room) {
        int[] coordinate = new int[2];
        for (int[] key : map.keySet()) {
            if (map.get(key) == room) {
                coordinate = key;
            }
        }
        return coordinate;
    }


    private ArrayList<Integer> possibleDirectionsToMap(Room room) {
        ArrayList<Integer> possibleDirectionsToMap = new ArrayList<>();
        for (int i = 0; i < room.getDirections().length; i++) {
            if (room.getDirections()[i] == null) {
                possibleDirectionsToMap.add(i);
            }
        }
        return possibleDirectionsToMap;
    }

    private int[] calculateCoordinate(int[] currentCoordinate, CardinalDirections direction) {
        int[] newCoordinate = new int[2];
        for (int i = 0; i < newCoordinate.length; i++) {
            newCoordinate[i] = currentCoordinate[i] + direction.getCoordinateForDirection()[i];
        }
        return newCoordinate;
    }


    private Room getRoom(ArrayList<Room> rooms) {
        Room room = rooms.get(0);
        rooms.remove(room);
        return room;
    }

    private void attachRoom(Room room, int direction, Room roomToAttach) {
        switch (direction) {
            case 0 -> room.setNorth(roomToAttach);
            case 1 -> room.setSouth(roomToAttach);
            case 2 -> room.setEast(roomToAttach);
            case 3 -> room.setWest(roomToAttach);
            default -> attachRoom(room, random.nextInt(4), roomToAttach);
        }
    }
}

