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
        return itemType.toString().toLowerCase();
    }

    public ItemType getItemType() {
        return itemType;
    }
    public int getItemModifier() {
        return itemModifier;
    }

    public void useKey() {
        if (player.getCurrentRoom().getEnemies().size() == 0) {
            if ((player.getCurrentRoom().getNorth() != null) && (player.getCurrentRoom().getNorth().getIsLocked())) {
                player.getCurrentRoom().getNorth().setIsLocked(false);
                ui.displayDoorHasUnlocked();
            } else {
                ui.displayNoLockedDoor();
            }
        } else {
            ui.displayCantUnlockDoorBecauseOfEnemy();
        }
    }

    public void useFood() {
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getItemType() == Item.ItemType.FOOD) {
                player.setCurrentHealth(player.getCurrentHealth() + player.inventory.get(i).getItemModifier());
                ui.displayAteFood(player, i);
                player.inventory.remove(i);
                break;
            }
        }
    }

    public void useAntidote() {
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getItemType() == Item.ItemType.ANTIDOTE) {
                if (player.getIsPoisoned()) {
                    player.setIsPoisoned(false);
                    ui.displayUseAntidoteToCure();
                } else {
                    ui.displayUseAntidote();
                }
                player.inventory.remove(i);
                break;
            }
        }
    }

    public void useKnife(int i) {
        if (player.getCurrentDamage() == player.getBaseDamage()) {
            ui.displayEquipKnife();
            player.setCurrentDamage(player.inventory.get(i).getItemModifier());
        } else {
            ui.displayUnequipKnife();
            player.setCurrentDamage(player.getBaseDamage());
        }
    }

}
