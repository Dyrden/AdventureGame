package com.company.Items;

import com.company.AdventureUI;
import com.company.Room;

public abstract class Item {
    private final String shortName;
    private final String longName;
    private final int rarityModifier;

    public Item(String sName, String lName, int rarity) {
        shortName = sName;
        longName = lName;
        rarityModifier = rarity;
    }
    @Override
    public String toString() {
        return shortName;
    }

    public String getLongName(){
        return longName;
    }

    public abstract int use();
}
