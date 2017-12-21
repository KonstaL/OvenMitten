package fi.konstal.engine.assetmanager;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private static Map<String, AssetContainer> assets = new HashMap<>();


    public static <T> void addAsset(String name, T asset) {
        if (assets.containsKey(name)) {
            throw new KeyExistsException("The key you gave already exists in AssetManager!");
        } else {
            assets.put(name, new AssetContainer(asset));
        }
    }

    public static synchronized <T> T getAsset(String name, Class<T> type) {
        AssetContainer assetContainer = assets.get(name);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }

        return assetContainer.getAsset(type);
    }

    public static void removeAsset(String name) {
        if (assets.get(name) == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        assets.remove(name);
    }

    public static int numberOfTimesReferenced(String name) {
        AssetContainer assetContainer = assets.get(name);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        return assetContainer.getRefCount();
    }
}
