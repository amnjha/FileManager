package aman.filemanager.exceptions;

public class SystemExcpetion extends Exception {
    public SystemExcpetion() {
        super();
    }

    public SystemExcpetion(String message) {
        super(message);
    }

    public SystemExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemExcpetion(Throwable cause) {
        super(cause);
    }

    protected SystemExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
