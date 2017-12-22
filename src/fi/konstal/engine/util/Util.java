package fi.konstal.engine.util;

import fi.konstal.engine.assetmanager.AssetManager;

public class Util {
    public static int getUniqueID() {
        boolean isUnique = false;
        int rand = 0;
        while(!isUnique) {
            rand = (int) (Math.random() * 100_000_000);
            isUnique = !(AssetManager.containsAsset(rand));
        }
        return rand;
    }
}
