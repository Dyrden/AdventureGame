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

    public Item(ItemType type, int modifier) {
        itemType = type;
        itemModifier = modifier;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public int getItemModifier() {
        return itemModifier;
    }
}
