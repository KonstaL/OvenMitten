package fi.konstal.engine.assetmanager;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException() {
        super();
    }

    public AssetNotFoundException(String msg) {
        super(msg);
    }
}
