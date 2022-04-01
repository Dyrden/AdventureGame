package com.company.Items.Equipables;

import com.company.Items.Item;

public abstract class Equipment extends Item {

    private final int durability;

    public Equipment(String sName, String lName, int durability) {
        super(sName, lName);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }
}