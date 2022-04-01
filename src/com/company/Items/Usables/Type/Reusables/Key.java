package com.company.Items.Usables.Type.Reusables;

import com.company.Items.Usables.Type.Reusable;

public class Key extends Reusable {
    protected final String keyId;
    public Key(String sName, String lName, String id) {
        super(sName, lName);
        keyId = id;
    }

    public String getKeyId() {
        return keyId;
    }
}
