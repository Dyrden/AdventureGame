package com.company;

public class Enemy {
    public enum EnemyType {
        rat,
        bat,
        snake
    }
    private final EnemyType enemyType;
    private final int damage;
    private final boolean isPoisonous;
    private int currentHealth;

    public Enemy(EnemyType type, int health, int dmg, boolean poisonous) {
        enemyType = type;
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
}
