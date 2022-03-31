package com.company.DungeonGenerator;


import com.company.Room;

import java.util.*;

public class DungeonGenerator {

    private Random random = new Random();
    private DungeonSize size = DungeonSize.MEDIUM;
    private ArrayList<Room> listOfUnusedRooms = new CreateRooms().generateRooms(size); // get list from CreateRooms();


    public HashMap<int[], Room> getDungeon() {
        return map;
    }

    private HashMap<int[], Room> map = new HashMap<>();


    private int[] findPositionByRoom(Room room) {
        int[] coordinate = new int[2];
        for (int[] key : map.keySet()) {
            if (map.get(key) == room) {
                coordinate = key;
            }
        }
        return coordinate;
    }

    private void initializeFirstRoom() {
        Room starterRoom = getRoom();
        int[] startPosition = {0, 0};
        map.put(startPosition, starterRoom);
    }

    public static void main(String[] args) {
        new DungeonGenerator().run();
    }

    public void run() {
        initializeFirstRoom();

        int amountOfRooms; // to run at least once
        do {
            ArrayList<Room> listOfMappedRooms = new ArrayList<>(map.values());

            Room randomRoomInMap = listOfMappedRooms.get(random.nextInt(listOfMappedRooms.size()));
            int[] keyPosition = findPositionByRoom(randomRoomInMap);
            CardinalDirections[] cd = CardinalDirections.values();

            ArrayList<Integer> possibleDirectionsToMap = possibleDirectionsToMap(randomRoomInMap);

            for (int direction : possibleDirectionsToMap) {
                Room room = getRoom();
                attachRoom(randomRoomInMap, direction, room);
                int[] newCoordinate = calculateCoordinate(keyPosition, cd[direction]);
                map.put(newCoordinate, room);

            }
            amountOfRooms = listOfUnusedRooms.size();
        }
        while (amountOfRooms > 1);
        printMap();
    }

    private void printMap() {
        ArrayList<int[]> coordinates = new ArrayList<>();
        for (int[] coordinate : map.keySet()) {
            if (map.get(coordinate) != null) {
                coordinates.add(coordinate);
            }
        }

        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                for (int[] coordinate : coordinates) {
                    if (Arrays.equals(coordinate, new int[]{i, j})) {
                        System.out.print("o");
                    }
                }
                    System.out.print("-");
            }
            System.out.println();
        }
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


    private Room getRoom() {
        Room room = listOfUnusedRooms.get(0);
        listOfUnusedRooms.remove(room);
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
