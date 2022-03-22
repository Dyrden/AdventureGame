package com.company.Items.Equipables;

import com.company.Items.Item;

public class Equipment extends Item {

    public Equipment(String sName, String lName, int rarity, double weight) {
        super(sName, lName, rarity, weight);
    }


    @Override
    public String toString() {
        return super.toString() ;
    }


    // equip method? idk
}