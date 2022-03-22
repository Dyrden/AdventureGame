package com.company.Items.Usables.Type.Perishables;

import com.company.Items.Usables.Type.Perishable;

public class Food extends Perishable {
    protected int healAmount;
    public Food(String sName, String lName, int rarity,double weight, int healXAmount) {
        super(sName, lName, rarity, weight);
        healAmount = healXAmount;
    }
    public int getHealAmount() {
        return healAmount;
    }
}
