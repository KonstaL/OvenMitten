package fi.konstal.engine.assetmanager;

import java.util.*;

/**
 * AssetManager is a static class that handles assets.
 *
 * All assets should be loaded through assetmanager. This enables a smaller memoryfootprint
 * (by referencing to single assets vs. loading it multiple times) and serialization, as the only thing you're serializing is the reference
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-22
 */
public class AssetManager {
    private static Map<Integer, AssetContainer> assets = new HashMap<>();
    private static HashMap<String, Integer> assetReferences = new HashMap<>();


    /**
     * Adds an asset and binds it to a integer key
     *
     * If an non-unique ID is tried to be added, it warns the user that the
     * old asset will be used instead of the new one
     *
     * @param <T>   the Class of the asset
     * @param key   the key
     * @param asset the asset itself
     */
    public static <T> void addAsset(int key, T asset) {
        if (assets.containsKey(key)) {
            System.out.println("Warning, The key you gave already exists in AssetManager. The key will point to the old asset");
        } else {
            assets.put(key, new AssetContainer(asset));
        }
    }

    /**
     * Gets an asset by it's key
     *
     * @param <T>  the type of class to return
     * @param key  the key
     * @param type the Class object of the asset. Used to cast the asset to it's original class
     * @return the asset casted into the given class
     */
    public static synchronized <T> T getAsset(int key, Class<T> type) {
        AssetContainer assetContainer = assets.get(key);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        assetContainer.incRefCount();
        return assetContainer.getAsset(type);
    }

    /**
     * Gets an Collection Asset
     *
     * @param <T>  the type of the Collection
     * @param key  the key
     * @param type Used to cast the Collection back to it's type
     * @return the Collection of assets
     */
    public static synchronized <T> Collection<T> getAssetCollection(int key, Class<T> type) {
        AssetContainer assetContainer = assets.get(key);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }

        Collection<T> toReturn = assetContainer.getAsset(Collection.class);
        return toReturn;
    }

    /**
     * Returns a boolean whether a certain key is already in use
     *
     * @param key the key
     * @return the boolean
     */
    public static boolean containsAsset(int key) {
        return assets.containsKey(key);
    }

    /**
     * Returns a boolean whether certain reference String is already in use
     *
     * @param ref the ref
     * @return the boolean
     */
    public static boolean containsRef(String ref) {
        return assetReferences.containsKey(ref);
    }

    /**
     * Remove an asset.
     *
     * @param key the key of the asset to remove
     */
    public static void removeAsset(int key) {
        if (assets.get(key) == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        assets.remove(key);
    }

    /**
     * Returns the number of times some asset has been referenced
     *
     * @param key the key of the asset
     * @return the int
     */
    public static int numberOfTimesReferenced(int key) {
        AssetContainer assetContainer = assets.get(key);
        if (assets == null) {
            throw new AssetNotFoundException("No asset was found with that key!");
        }
        return assetContainer.getRefCount();
    }

    /**
     * Adds an object to AssetManager while giving it a String to reference the assetKey.
     *
     * Great for use if you have multiple objects using the same asset
     *
     * @param <T>    the type of class to return
     * @param name   The String you can reference the assetKey by
     * @param key    the key
     * @param object the asset
     */
    public static <T> void addAssetReference(String name, int key, T object) {
        assetReferences.put(name, key);
        addAsset(key, object);
    }

    /**
     * Add String reference to an allready existing assetKey
     *
     * @param name the String reference
     * @param key  the key
     */
    public static void addAssetReference(String name, int key) {
        assetReferences.put(name, key);
    }

    /**
     * Returns the integer assetKey
     *
     * @param name The String reference to search by
     * @return the asset key
     */
    public static int getAssetKey(String name) {
        Integer toReturn;
        toReturn = assetReferences.get(name);
        if(toReturn == null) {
            throw new AssetNotFoundException("The given string doesn't match with any ID");
        }
        return toReturn;
    }

    /**
     * Removes a String reference to a asset key
     *
     * @param name the name of the String reference to remove
     */
    public static void  removeAssetReference(String name) {
        if(!(assetReferences.containsKey(name))) {
            throw new AssetNotFoundException("The given string doesn't match with any ID");
        }
        assetReferences.remove(name);
    }
}
