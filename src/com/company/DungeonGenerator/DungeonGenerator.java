package com.company.DungeonGenerator;

import com.company.Room;

import java.util.ArrayList;

public class DungeonGenerator {


    public ArrayList<Room> generateDungeon(DungeonSize size) {
        ArrayList<Room> dungeon;

        dungeon = new CreateRooms().generateRooms(size);


        // link dungeon
        // add name description
        // go through the dungeon with a recursive loop on for each cardinal direction:
        // if (null) add name + description; Else (Room) call itself on that Room
        return dungeon;
    }


}
