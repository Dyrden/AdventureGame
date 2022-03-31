package com.company.DungeonGenerator;

import com.company.Room;

import java.util.ArrayList;

public class CreateRooms {
    
    private ArrayList<Room> rooms = new ArrayList<>();
    
    public ArrayList<Room> generateRooms(DungeonSize size){
        int grid = size.getDungeonSize();
        for (int i = 0; i < grid*grid; i++) {
            rooms.add(new Room(null,"Rum" + i,false,null));
        }
        return rooms;
    }
    
    
    



}
