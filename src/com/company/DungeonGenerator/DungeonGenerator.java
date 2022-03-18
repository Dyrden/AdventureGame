package com.company.DungeonGenerator;

import com.company.Room;

import java.util.ArrayList;

public class DungeonGenerator {

    private ArrayList<Room> dungeon;

    public void generateDungeon(DungeonSize size) {
        ArrayList<Room> dungeon;
        dungeon = new CreateRooms().generateRooms(size);
        this.dungeon = dungeon;
    }


    public ArrayList<Room> getDungeon() {
        return dungeon;
    }
}
