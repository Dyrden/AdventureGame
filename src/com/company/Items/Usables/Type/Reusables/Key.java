package com.company.Items.Usables.Type.Reusables;

import com.company.AdventureUI;
import com.company.Items.Usables.Type.Reusable;
import com.company.Room;

public class Key extends Reusable {

    private final AdventureUI ui = new AdventureUI();
    protected final String keyId;

    public Key(String sName, String lName, int rarity, String id) {
        super(sName, lName, rarity);
        keyId = id;
    }

    public String getKeyId() {
        return keyId;
    }

    public int use() {

        return 0;
    }
}
