package com.company.Items.Equipables;

import com.company.Items.Item;

public abstract class Equipment extends Item {
    private final int durability;
    public Equipment(String sName, String lName, int rarity, double weight, int durability) {
        super(sName, lName, rarity, weight);
        this.durability = durability;
    }


    @Override
    public String toString() {
        return super.toString() ;
    }

    @Override
    public int use() {
        return 0;
    }

    public int getDurability() {
        return durability;
    }

    // equip method? idk
}