package com.company;

public class Item {
    public enum ItemType {
        key,
        food,
        antidote,
        knife
    }
    private final ItemType itemType;
    private final int itemModifier;
    private int rarityModifier;
    private Player player;

    public Item(ItemType type, int modifier, int rarity, Player play) {
        itemType = type;
        itemModifier = modifier;
        rarityModifier = rarity;
        player = play;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public int getItemModifier() {
        return itemModifier;
    }

    public void use(Item.ItemType itemType) {
        boolean hasItem = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getItemType() == itemType) {
                hasItem = true;
                switch (itemType) {
                    case key -> useKey();
                    case food -> useFood();
                    case antidote -> useAntidote();
                    case knife -> useKnife(i);
                }
            }
        }
        if (!hasItem) {
            System.out.println("Don't have " + itemType.toString() + ".");
        }
    }

    private void useKey() {
        if (player.getCurrentRoom().getEnemies().size() == 0) {
            if ((player.getCurrentRoom().getNorth() != null) && (player.getCurrentRoom().getNorth().getIsLocked())) {
                player.getCurrentRoom().getNorth().setIsLocked(false);
                System.out.println("The door has unlocked.");
            } else {
                System.out.println("No lock to use the key in.");
            }
        } else {
            System.out.println("Can't do that right now. An adversary is in the way.");
        }
    }

    private void useFood() {
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getItemType() == Item.ItemType.food) {
                player.setCurrentHealth(player.getCurrentHealth() + player.inventory.get(i).getItemModifier());
                System.out.println("Ate food and recovered " + player.inventory.get(i).getItemModifier() + " health.");
                player.inventory.remove(i);
                break;
            }
        }
    }

    private void useAntidote() {
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getItemType() == Item.ItemType.antidote) {
                if (player.getIsPoisoned()) {
                    player.setIsPoisoned(false);
                    System.out.println("Used antidote to cure poison status effect.");
                } else {
                    System.out.println("Used antidote.");
                }
                player.inventory.remove(i);
                break;
            }
        }
    }

    private void useKnife(int i) {
        if (player.getCurrentDamage() == player.getBaseDamage()) {
            System.out.println("Equipping knife in right hand.");
            player.setCurrentDamage(player.inventory.get(i).getItemModifier());
        } else {
            System.out.println("Unequipping knife.");
            player.setCurrentDamage(player.getBaseDamage());
        }
    }

    public void take(Item.ItemType itemType) {
        if (player.getCurrentRoom().getItems().size() > 0) {
            boolean isAvailable = false;
            for (int i = 0; i < player.getCurrentRoom().getItems().size(); i++) {
                if (player.getCurrentRoom().getItems().get(i).getItemType() == itemType) {
                    System.out.println("Took " + player.getCurrentRoom().getItems().get(i).getItemType().toString() + ".");
                    player.inventory.add(player.getCurrentRoom().getItems().get(i));
                    player.getCurrentRoom().getItems().remove(i);
                    player.getCurrentRoom().updateRoomDescription();
                    isAvailable = true;
                    break;
                }
            }
            if (!isAvailable) {
                System.out.println("No " + itemType.toString() + " here.");
            }
        } else {
            System.out.println("Nothing to pick up here.");
        }
    }
}
