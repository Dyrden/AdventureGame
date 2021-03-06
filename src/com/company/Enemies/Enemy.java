package com.company.Enemies;

import com.company.Player;

public abstract class Enemy {
    private final String enemyName;
    private final int damage;
    private final boolean isPoisonous;
    private int currentHealth;

    public Enemy(String name, int health, int dmg, boolean poisonous) {
        enemyName = name;
        currentHealth = health;
        damage = dmg;
        isPoisonous = poisonous;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    public boolean getIsPoisonous() {
        return isPoisonous;
    }

    public String getEnemyName(){
        return enemyName;
    }

    @Override
    public String toString() {
        return "Name: " + enemyName + " Health: " + getCurrentHealth();
    }

    public void attack(Player player) {
        if (player.getCurrentHealth() - damage > 0) {
            player.setCurrentHealth(player.getCurrentHealth() - damage);
            if (isPoisonous) {
                player.setIsPoisoned(true);
            }
        }
        else {
            player.setCurrentHealth(0);
        }
    }
}
