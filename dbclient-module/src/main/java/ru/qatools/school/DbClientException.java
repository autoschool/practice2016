package ru.qatools.school;

/**
 * Created by omaz on 27.04.16.
 */
public class DbClientException extends RuntimeException {
    public DbClientException() {
    }

    public DbClientException(String message) {
        super(message);
    }

    public DbClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbClientException(Throwable cause) {
        super(cause);
    }

    public DbClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
