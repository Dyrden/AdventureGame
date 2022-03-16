package com.company;

public class Player {

    private String name;
    private int health;
    private int maxHealth;
    private boolean alive;
    private Room currentRoom;


    private int baseDamage = 5;

    public Player(String name, int health, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.currentRoom = currentRoom;
        this.alive = true;
    }

    public void healHealth(int health) {
        if (this.health + health > maxHealth)
            this.health = maxHealth;
        else
            this.health += health;
    }
    public void damageHealth(int health) {
        this.health -= health;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
