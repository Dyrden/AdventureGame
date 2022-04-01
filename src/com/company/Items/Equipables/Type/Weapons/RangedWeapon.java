package com.company.Items.Equipables.Type.Weapons;

import com.company.Items.Equipables.Type.Weapon;

public class RangedWeapon extends Weapon {
    private int ammunition;
    public RangedWeapon(String sName, String lName,int durability, int weaponModifier, int ammo) {
        super(sName, lName, durability, weaponModifier);
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
        if (getDurability() > 0) {
            if (ammunition > 0) {
                ammunition -= 1;
                return true;
            }
            else return false;
        }
        else return false;
    }
}
