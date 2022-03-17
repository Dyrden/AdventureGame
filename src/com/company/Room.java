package com.company;

import java.util.ArrayList;

public class Room {
    private final String roomName;
    private String roomDescription;
    private Room northernRoom;
    private Room southernRoom;
    private Room easternRoom;
    private Room westernRoom;
    private boolean isLocked;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private boolean playerVisited;
    private final Player player;

    public Room(String name, String description, boolean locked, Player play) {
        roomName = name;
        roomDescription = description;
        isLocked = locked;
        player = play;
    }
    public String getRoomName() {
        return roomName;
    }
    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String description) {
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
    public Room getNorth() {
        return northernRoom;
    }
    public Room getSouth() {
        return southernRoom;
    }
    public Room getEast() {
        return easternRoom;
    }
    public Room getWest() {
        return westernRoom;
    }
    public boolean getIsLocked() {
        return isLocked;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }
    public void removeEnemy(int index) {
        this.enemies.remove(index);
    }

    public void setIsLocked(boolean locked) {
        this.isLocked = locked;
    }

    public boolean isPlayerVisited() {
        return playerVisited;
    }

    public void setPlayerVisited(boolean playerVisited) {
        this.playerVisited = playerVisited;
    }

    public void updateRoomDescription() {
        switch (player.getCurrentRoom().getRoomName()) {
            case "Cave" -> {
                if (player.getCurrentRoom().getEnemies().size() == 1) {
                    player.getCurrentRoom().setRoomDescription("Dank dark cavern, a bat is hanging from the ceiling. Another one dead on the ground.");
                } else if (player.getCurrentRoom().getEnemies().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("Dank dark cavern, two bats are dead on the ground.");
                }
            }
            case "Crawl space" -> {
                if (player.getCurrentRoom().getItems().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You are in a tight crawl space.");
                }
            }
            case "Sewer" -> {
                if (player.getCurrentRoom().getEnemies().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You entered a sewer. There is a dead rat, its blood is fusing with the sewage.");
                }
            }
            case "Security" -> {
                if (player.getCurrentRoom().getItems().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You entered a room with a bunch of displays, showing live CCTV footage. The locations seem familiar.");
                }
            }
            case "Sewage filtration" -> {
                if (player.getCurrentRoom().getItems().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You've entered a room with a machine filtrating the sewage.");
                }
            }
            case "Golden Door" -> {
                if (player.getCurrentRoom().getEnemies().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You find yourself in a room with a locked giant golden door.");
                }
            }
            case "Back-alley" -> {
                if (player.getCurrentRoom().getItems().size() == 0) {
                    player.getCurrentRoom().setRoomDescription("You entered a back-alley, seems to connect important areas of this complex.");
                }
            }
        }
    }
}
