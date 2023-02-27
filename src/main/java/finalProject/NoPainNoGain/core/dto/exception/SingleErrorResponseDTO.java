package finalProject.NoPainNoGain.core.dto.exception;

public class SingleErrorResponseDTO {

    private String logref;

    private String message;

    public SingleErrorResponseDTO() {
    }

    public SingleErrorResponseDTO(String logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}
