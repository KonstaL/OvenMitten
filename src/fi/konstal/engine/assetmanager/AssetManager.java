package fi.konstal.engine.assetmanager;

import java.util.*;

public class AssetManager {
    private static Map<Integer, AssetContainer> assets = new HashMap<>();
    private static HashMap<String, Integer> assetReferences = new HashMap<>();


    public static <T> void addAsset(int key, T asset) {
        if (assets.containsKey(key)) {
            System.out.println("Warning, The key you gave already exists in AssetManager. The key will point to the old asset");
        } else {
            assets.put(key, new AssetContainer(asset));
        }
    }

    public static synchronized <T> T getAsset(int key, Class<T> type) {
        AssetContainer assetContainer = assets.get(key);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }

        return assetContainer.getAsset(type);
    }

    public static synchronized <T> Collection<T> getAssetCollection(int key, Class<T> type) {
        AssetContainer assetContainer = assets.get(key);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }

        Collection<T> toReturn = assetContainer.getAsset(Collection.class);
        return toReturn;
    }

    public static boolean containsAsset(int key) {
        return assets.containsKey(key);
    }

    public static boolean containsRef(String ref) {
        return assetReferences.containsKey(ref);
    }

    public static void removeAsset(int key) {
        if (assets.get(key) == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        assets.remove(key);
    }

    public static int numberOfTimesReferenced(String name) {
        AssetContainer assetContainer = assets.get(name);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        return assetContainer.getRefCount();
    }

    public static <T> void addAssetReference(String name, int key, T object) {
        assetReferences.put(name, key);
        addAsset(key, object);
    }

    public static <T> void addAssetReference(String name, int key) {
        assetReferences.put(name, key);
    }

    public static int getAssetReference(String name) {
        Integer toReturn;
        toReturn = assetReferences.get(name);
        if(toReturn == null) {
            throw new AssetNotFoundException("The given string doesn't match with any ID");
        }
        return toReturn;
    }

    public static void  removeAssetReference(String name) {
        if(!(assetReferences.containsKey(name))) {
            throw new AssetNotFoundException("The given string doesn't match with any ID");
        }
        assetReferences.remove(name);
    }
}
