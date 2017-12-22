package fi.konstal.engine.assetmanager;

/**
 * Meant to show that no assets were found by the search.
 */
public class AssetNotFoundException extends RuntimeException {
    /**
     * Instantiates a plain new Asset not found exception.
     */
    public AssetNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Asset not found exception with a custom message.
     *
     * @param msg the message
     */
    public AssetNotFoundException(String msg) {
        super(msg);
    }
}
