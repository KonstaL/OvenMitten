package fi.konstal.engine.assetmanager;

/**
 * AssetContainer enables the tracking of references to the asset
 * It contains the asset itself and a counter (..for now)
 *
 */
public class AssetContainer {
    private Object asset;
    private int referenceCount;

    /**
     * Instantiates a new Asset container.
     *
     * @param o the asset
     */
    public AssetContainer(Object o) {
        if(o == null) {
            throw new IllegalArgumentException("The object can't be null!");
        }
        referenceCount = 0;
        this.asset = o;
    }

    /**
     * Casts the asset to requested class and returns it
     *
     * @param <T>  the type of class to cast
     * @param type The variable to hold the class information
     * @return the asset
     */
    public <T> T getAsset(Class<T> type) {
        try {
            return (T) asset;
        } catch (ClassCastException e) {
            System.out.println("You can't cast this asset to that class!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the asset.
     *
     * @param asset the asset
     */
    public void setAsset(Object asset) {
        this.asset = asset;
    }

    /**
     * Inc reference count.
     */
    public void incRefCount () {
        referenceCount++;
    }

    /**
     * Dec reference count.
     */
    public void decRefCount () {
        referenceCount--;
    }

    /**
     * Returns the reference count.
     *
     * @return the ref count
     */
    public int getRefCount () {
        return referenceCount;
    }

    /**
     * Sets reference count.
     *
     * @param referenceCount the reference count
     */
    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }
}
