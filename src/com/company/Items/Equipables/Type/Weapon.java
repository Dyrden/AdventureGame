package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public class Weapon extends Equipment {
    protected int weaponValue;

    public Weapon(String sName, String lName, int rarity, double weight, int weaponModifier) {
        super(sName, lName, rarity, weight);
        this.weaponValue = weaponModifier;
    }

    public int getWeaponValue() {
        return weaponValue;
    }
}
