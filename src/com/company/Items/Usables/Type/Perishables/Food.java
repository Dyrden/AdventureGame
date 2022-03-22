package com.company.Items.Usables.Type.Perishables;

import com.company.Items.Usables.Type.Perishable;


public class Food extends Perishable {
    private int healAmount;
    private HealthType type;

    public Food(String sName, String lName, int rarity,double weight, int healXAmount, HealthType type) {
        super(sName, lName, rarity, weight);
        healAmount = healXAmount;
        this.type = type;
    }

    public int getHealAmount() {
        return healAmount;
    }


    public HealthType getType() {
        return type;
    }
}
