package gym.Exception;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("Error: Client must be at least 18 years old to register\n");
    }
}
