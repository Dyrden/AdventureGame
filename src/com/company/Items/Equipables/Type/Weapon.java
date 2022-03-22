package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public class Weapon extends Equipment {
    protected int weaponModifier;

    public Weapon(String sName, String lName, int modifier, int rarity) {
        super(sName, lName, rarity);
        weaponModifier = modifier;
    }

    public int getWeaponModifier() {
        return weaponModifier;
    }
}
