package com.company.DungeonGenerator;

public enum CardinalDirections {
    NORTH(new int[]{0, 1}),
    SOUTH(new int[]{0, -1}),
    EAST(new int[]{1, 0}),
    WEST(new int[]{-1, 0});

    private int[] coordinateForDirection;

    CardinalDirections(int[] coordinateChange) {
        this.coordinateForDirection = coordinateChange;
    }


    public int[] getCoordinateForDirection() {
        return coordinateForDirection;
    }
}
