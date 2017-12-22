package fi.konstal.engine.assetmanager;

public class AssetReferenceNotFoundException extends RuntimeException{
    public AssetReferenceNotFoundException() {
        super();
    }

    public AssetReferenceNotFoundException(String msg) {
        super(msg);
    }
}
