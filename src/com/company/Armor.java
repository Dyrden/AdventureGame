package com.company;

public class Armor extends Equipment {
    private int armorModifier;

    public Armor(String sName, String lName, int modifier, int rarity) {
        super(sName, lName, rarity);
        armorModifier = modifier;
    }

    public int getArmorModifier() {
        return armorModifier;
    }
}
