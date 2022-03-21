package com.company;

public class Enemy {
    public enum EnemyType {
        rat,
        bat,
        snake
    }
    private final EnemyType enemyType;
    private final String enemyName;
    private final int damage;
    private final boolean isPoisonous;
    private int currentHealth;

    public Enemy(EnemyType type, String enemyName, int health, int dmg, boolean poisonous) {
        enemyType = type;
        this.enemyName = enemyName;
        currentHealth = health;
        damage = dmg;
        isPoisonous = poisonous;
    }
    public EnemyType getEnemyType() {
        return enemyType;
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

    @Override
    public String toString() {
        return enemyType.name() + " Health: "+  getCurrentHealth();
    }
}
