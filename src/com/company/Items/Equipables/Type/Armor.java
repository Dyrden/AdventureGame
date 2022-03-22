package com.company.Items.Equipables.Type;

import com.company.Items.Equipables.Equipment;

public class Armor extends Equipment {
    protected int armorModifier;

    public Armor(String sName, String lName, int modifier, int rarity) {
        super(sName, lName, rarity);
        armorModifier = modifier;
    }

    public int getArmorModifier() {
        return armorModifier;
    }
}
