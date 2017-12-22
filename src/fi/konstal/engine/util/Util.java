package fi.konstal.engine.util;

import fi.konstal.engine.assetmanager.AssetManager;

/**
 * Util is used for general purpose tasks.
 */
public class Util {
    /**
     * Returns an unique intergerID which is not currently in use in the AssetManager
     *
     * @return the unique id
     */
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
