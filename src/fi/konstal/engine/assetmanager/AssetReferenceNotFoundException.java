package fi.konstal.engine.assetmanager;

/**
 * Used for indicating that a assetReference was not found by the search string
 */
public class AssetReferenceNotFoundException extends RuntimeException{
    /**
     * Instantiates a new plain Asset reference not found exception.
     */
    public AssetReferenceNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Asset reference not found exception with a custom message.
     *
     * @param msg the message
     */
    public AssetReferenceNotFoundException(String msg) {
        super(msg);
    }
}
