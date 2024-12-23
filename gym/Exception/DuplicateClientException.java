package gym.Exception;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException() {
        super("Error: The client is already registered");
    }
}
