package com.company.Items.Equipables.Type.Weapons;

import com.company.Items.Equipables.Type.Weapon;

public class RangedWeapon extends Weapon {
    private int ammunition;
    public RangedWeapon(String sName, String lName, int weaponModifier, int ammo) {
        super(sName, lName, weaponModifier);
        ammunition = ammo;
    }

    public int getAmmunition() {
        return ammunition;
    }
    public void setAmmunition(int ammo) {
        ammunition = ammo;
    }
    @Override
    public boolean canBeUsed() {
        if (ammunition > 0) {
            ammunition -= 1;
            return true;
        }
        else return false;
    }
}
