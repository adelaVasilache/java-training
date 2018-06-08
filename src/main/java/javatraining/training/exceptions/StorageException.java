package javatraining.training.exceptions;


/**
 * Created by Adela Vasilache on 08.05.2018
 */
public class StorageException extends RuntimeException{
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
