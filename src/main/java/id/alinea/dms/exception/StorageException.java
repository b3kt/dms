package id.alinea.dms.exception;

public class StorageException extends RuntimeException {
    
    public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}