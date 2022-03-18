package com.company;

public class Item {
    public enum ItemType {
        KEY,
        FOOD,
        ANTIDOTE,
        KNIFE
    }
    private final String longName;
    private final ItemType itemType;
    private final int itemModifier;
    private final int rarityModifier;
    private final Player player;
    private final AdventureUI ui = new AdventureUI();

    public Item(String name, ItemType type, int modifier, int rarity, Player play) {
        longName = name;
        itemType = type;
        itemModifier = modifier;
        rarityModifier = rarity;
        player = play;
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

    public void useFood(int i) {
        if (this.itemType == ItemType.FOOD) {
            ui.displayAteFood(player, i);
            player.inventory.remove(i);
            player.healHealth(player.inventory.get(i).getItemModifier());
        }
    }

    public void useAntidote(int i) {
        if (this.itemType == ItemType.ANTIDOTE) {
            if (player.getIsPoisoned()) {
                player.setIsPoisoned(false);
                ui.displayUseAntidoteToCure();
            } else {
                ui.displayUseAntidote();
            }
            player.inventory.remove(i);
        }
    }

    public void useWeapon(String weapon, int i) {
        if (player.getCurrentDamage() == player.getBaseDamage()) {
            ui.displayEquipWeapon(weapon);
            player.setCurrentDamage(player.inventory.get(i).getItemModifier());
        } else {
            ui.displayUnequipWeapon(weapon);
            player.setCurrentDamage(player.getBaseDamage());
        }
    }

}
