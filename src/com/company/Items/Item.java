package com.company.Items;

public abstract class Item {
    private final String shortName;
    private final String longName;

    public Item(String sName, String lName) {
        shortName = sName;
        longName = lName;
    }
    @Override
    public String toString() {
        return longName;
    }

    public String getLongName(){
        return longName;
    }

    public String getShortName(){
        return shortName;
    }
}
