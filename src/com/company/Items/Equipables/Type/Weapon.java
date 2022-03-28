package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public abstract class Weapon extends Equipment {
    protected int weaponValue;

    public Weapon(String sName, String lName, int rarity, double weight, int durability, int weaponModifier) {
        super(sName, lName, rarity, weight, durability);
        this.weaponValue = weaponModifier;
    }

    public int getWeaponValue() {
        return weaponValue;
    }

    @Override
    public String toString() {
        return super.toString() + " Attack Value: " + weaponValue;
    }
}
