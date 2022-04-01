package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public abstract class Armor extends Equipment {
    private int armorValue;

    public Armor(String sName, String lName, int armorValue) {
        super(sName, lName);
        this.armorValue = armorValue;

    }

    public int getArmorModifier() {
        return armorValue;
    }

    @Override
    public String toString() {
        return super.toString() + " Armor Value: " + armorValue;
    }
}
