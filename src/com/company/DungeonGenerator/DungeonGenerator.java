package com.company.DungeonGenerator;


import com.company.Room;

import java.util.*;

public class DungeonGenerator {
    private DungeonSize dungeonSize;
    private CreateRooms createRooms;
    private LinkRooms linkRooms;
    private HashMap<int[], Room> map = new HashMap<>();


    public DungeonGenerator(DungeonSize dungeonSize) {
        this.dungeonSize = dungeonSize;
    }

    public HashMap<int[], Room> createAndGetDungeon() {
        this.createRooms = new CreateRooms();
        this.linkRooms = new LinkRooms();
        ArrayList<Room> unusedRooms = createRooms.generateRooms(dungeonSize);
        this.map = linkRooms.linkRooms(unusedRooms);
        return this.map;
    }


    public static void main(String[] args) {
        DungeonGenerator dg = new DungeonGenerator(DungeonSize.MEDIUM);
        HashMap<int[], Room> map = dg.createAndGetDungeon();
        dg.printMapOfDungeon(map);
        for (Room room : map.values()) {
            System.out.println(room);
        }
    }


    public void printMapOfDungeon(HashMap<int[], Room> map) {
        printDungeonMap(makeDungeonMap(map));
    }

    private void printDungeonMap(HashMap<int[], String> hashmap) {
        for (int i = -dungeonSize.getDungeonSize() / 2; i < dungeonSize.getDungeonSize() / 2; i++) {
            for (int j = -dungeonSize.getDungeonSize() / 2; j < dungeonSize.getDungeonSize() / 2; j++) {
                for (int[] arr : hashmap.keySet()) {
                    if (Arrays.equals(arr, new int[]{i, j})) {
                        System.out.print(hashmap.get(arr));
                    }
                }
            }
            System.out.println();
        }

    }

    private HashMap<int[], String> makeDungeonMap(HashMap<int[], Room> map) {
        HashMap<int[], String> dungeonMap = new HashMap<>();
        for (int i = -dungeonSize.getDungeonSize() / 2; i < dungeonSize.getDungeonSize() / 2; i++) {
            for (int j = -dungeonSize.getDungeonSize() / 2; j < dungeonSize.getDungeonSize() / 2; j++) {
                int[] coordinate = new int[]{i, j};
                dungeonMap.put(coordinate, "  ");
                for (int[] key : map.keySet()) {
                    if (map.get(key) != null) {
                        if (Arrays.equals(key, coordinate)) {
                            dungeonMap.put(coordinate, "o ");
                        }
                    }
                }
            }
        }
        return dungeonMap;
    }

}
