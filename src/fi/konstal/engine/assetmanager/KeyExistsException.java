package fi.konstal.engine.assetmanager;

public class KeyExistsException extends RuntimeException {
    public KeyExistsException() {
        super();
    }

    public KeyExistsException(String msg) {
        super(msg);
    }
}
