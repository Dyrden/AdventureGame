package com.company.Items.Usables.Type.Reusables;

import com.company.Items.Usables.Type.Reusable;

public class Key extends Reusable {
    protected final String keyId;
    public Key(String sName, String lName, int rarity,double weight, String id) {
        super(sName, lName, rarity, weight);
        keyId = id;
    }

    public String getKeyId() {
        return keyId;
    }

    public int use() {

        return 0;
    }
}
