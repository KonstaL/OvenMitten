package fi.konstal.engine.assetmanager;

public class AssetContainer {
    private Object asset;
    private int referenceCount;

    public AssetContainer(Object o) {
        if(o == null) {
            throw new IllegalArgumentException("The object can't be null!");
        }
        referenceCount = 0;
        this.asset = o;
    }

    public <T> T getAsset(Class<T> type) {
        try {
            return (T) asset;
        } catch (ClassCastException e) {
            System.out.println("You can't cast this asset to that class!");
            e.printStackTrace();
        }
        return null;
    }

    public void setAsset(Object asset) {
        this.asset = asset;
    }

    public void incRefCount () {
        referenceCount++;
    }

    public void decRefCount () {
        referenceCount--;
    }

    public int getRefCount () {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }
}
