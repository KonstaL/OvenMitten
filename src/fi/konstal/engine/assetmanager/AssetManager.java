package fi.konstal.engine.assetmanager;

import java.util.*;

public class AssetManager {
    private static Map<String, AssetContainer> assets = new HashMap<>();


    public static <T> void addAsset(String name, T asset) {
        if (assets.containsKey(name)) {
            System.out.println("Warning, The key you gave already exists in AssetManager. The key will point to the old asset");
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

    public static synchronized <T> Collection<T> getAssetCollection(String name, Class<T> type) {
        AssetContainer assetContainer = assets.get(name);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }

        Collection<T> toReturn = assetContainer.getAsset(Collection.class);
        return toReturn;
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
