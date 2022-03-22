package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public class Armor extends Equipment {
    private int armorValue;

    public Armor(String sName, String lName, int rarity, double weight, int armorValue) {
        super(sName, lName, rarity, weight);
        this.armorValue = armorValue;

    }

    public int getArmorModifier() {
        return armorValue;
    }
}
