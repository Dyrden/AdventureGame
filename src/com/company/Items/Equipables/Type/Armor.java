package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public class Armor extends Equipment {
    private int armorValue;

    public Armor(String sName, String lName, int rarity, double weight, int durability, int armorValue) {
        super(sName, lName, rarity, weight, durability);
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
