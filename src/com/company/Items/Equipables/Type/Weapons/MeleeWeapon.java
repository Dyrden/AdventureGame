package com.company.Items.Equipables.Type.Weapons;

import com.company.Items.Equipables.Type.Weapon;

public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String sName, String lName, int durability, int weaponModifier) {
        super(sName, lName, durability, weaponModifier);
    }
    @Override
    public boolean canBeUsed() {
        return true;
    }
}
