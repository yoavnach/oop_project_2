package gym.Exception;

public class InstructorNotQualifiedException extends RuntimeException {
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type");
    }
}
