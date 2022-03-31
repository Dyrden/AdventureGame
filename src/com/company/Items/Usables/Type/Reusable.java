package com.company.Items.Usables.Type;

import com.company.Items.Usables.Usable;

public abstract class Reusable extends Usable {

    public Reusable(String sName, String lName, int rarity, double weight) {
        super(sName, lName, rarity, weight);
    }

    @Override
    public int use() {
        return 0;
    }
}
