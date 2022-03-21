package com.company;

public class Item {
    public void use() {
        switch (itemType) {
            case KEY -> {}
            case WEAPON-> {}
            case CONSUMABLE -> {}

        }

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
    private final ItemType itemType;
    private final int itemModifier;
    private final int rarityModifier;
    private final AdventureUI ui = new AdventureUI();

    public Item(String sName, String lName, ItemType type, int modifier, int rarity) {
        shortName = sName;
        longName = lName;
        itemType = type;
        itemModifier = modifier;
        rarityModifier = rarity;
    }
    @Override
    public String toString() {
        return longName;
    }

    public ItemType getItemType() {
        return itemType;
    }
    public int getItemModifier() {
        return itemModifier;
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
