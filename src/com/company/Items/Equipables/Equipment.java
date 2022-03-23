package com.company.Items.Equipables;

import com.company.Items.Item;

public abstract class Equipment extends Item {

    public Equipment(String sName, String lName, int rarity, double weight) {
        super(sName, lName, rarity, weight);
    }


    @Override
    public String toString() {
        return super.toString() ;
    }

    @Override
    public int use() {
        return 0;
    }


    // equip method? idk
}