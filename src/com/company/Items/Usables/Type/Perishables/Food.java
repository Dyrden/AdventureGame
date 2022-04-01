package com.company.Items.Usables.Type.Perishables;

import com.company.Items.Usables.Type.Perishable;


public class Food extends Perishable {
    private int healAmount;

    public Food(String sName, String lName, int healXAmount) {
        super(sName, lName);
        healAmount = healXAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }
}
