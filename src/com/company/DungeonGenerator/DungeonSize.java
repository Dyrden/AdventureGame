package com.company.DungeonGenerator;

public enum DungeonSize {
    SMALL (10),
    MEDIUM (25),
    LARGE (40);

    private int dungeonSize;

    DungeonSize(int size) {
        dungeonSize = size;
    }

    public int getDungeonSize() {
        return dungeonSize;
    }
}
