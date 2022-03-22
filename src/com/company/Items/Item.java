package com.company.Items;

public abstract class Item {
    private final String shortName;
    private final String longName;
    private final int rarityModifier;
    private final double weight;

    public Item(String sName, String lName, int rarity, double weight) {
        shortName = sName;
        longName = lName;
        rarityModifier = rarity;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return shortName;
    }

    public String getLongName(){
        return longName;
    }

    public String getShortName(){
        return shortName;
    }



    public abstract int use();
}
