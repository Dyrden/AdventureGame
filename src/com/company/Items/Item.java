package com.company.Items;

import com.company.AdventureUI;
import com.company.Room;

public class Item {
    public void use() {

    }

    public String getShortName() {
        return null;
    }

    public enum ItemType {
        KEY,
        CONSUMABLE,
        WEAPON
    }
    private final String shortName;
    private final String longName;
    private final int rarityModifier;
    private final AdventureUI ui = new AdventureUI();

    public Item(String sName, String lName, int rarity) {
        shortName = sName;
        longName = lName;
        rarityModifier = rarity;
    }
    @Override
    public String toString() {
        return shortName;
    }

    public void useKey(Room currentRoom) {
        if (currentRoom.getEnemies().size() == 0) {
            if ((currentRoom.getNorth() != null) && (currentRoom.getNorth().getIsLocked())) {
                currentRoom.getNorth().setIsLocked(false);
                ui.displayDoorHasUnlocked();
            } else {
                ui.displayNoLockedDoor();
            }
        } else {
            ui.displayCantUnlockDoorBecauseOfEnemy();
        }
    }



    public String getLongName(){
        return longName;
    }

}
