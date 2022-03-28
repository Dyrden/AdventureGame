package com.company.Items.Equipables.Type.Weapons;

import com.company.Items.Equipables.Type.Weapon;

public class RangedWeapon extends Weapon {
    private int ammunition;
    public RangedWeapon(String sName, String lName, int rarity, double weight, int durability, int weaponModifier, int ammo) {
        super(sName, lName, rarity, weight, durability, weaponModifier);
        ammunition = ammo;
    }

    public int getAmmunition() {
        return ammunition;
    }
    public void setAmmunition(int ammo) {
        ammunition = ammo;
    }
}
