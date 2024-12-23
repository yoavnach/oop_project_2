package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException() {
        super("Error: Registration is required before attempting to unregister");
    }
}
