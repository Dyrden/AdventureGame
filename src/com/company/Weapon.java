package com.company;

public class Weapon extends Equipment {
    private int weaponModifier;

    public Weapon(String sName, String lName, int modifier, int rarity) {
        super(sName, lName, rarity);
        weaponModifier = modifier;
    }

    public int getWeaponModifier() {
        return weaponModifier;
    }
}
