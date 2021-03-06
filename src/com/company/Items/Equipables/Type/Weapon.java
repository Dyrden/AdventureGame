package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public abstract class Weapon extends Equipment {
    private int weaponValue;

    public Weapon(String sName, String lName, int attackValue) {
        super(sName, lName);
        this.weaponValue = attackValue;
    }

    public int getWeaponValue() {
        return weaponValue;
    }

    @Override
    public String toString() {
        return super.toString() + " - Attack Value: " + weaponValue;
    }

    public abstract boolean canBeUsed();
}
