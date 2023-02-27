package finalProject.NoPainNoGain.core.dto.exception;

import finalProject.NoPainNoGain.core.exception.MyError;

import java.util.List;

public class MultipleErrorResponseDTO {

    private String logreg;

    private List<MyError> errors;

    public MultipleErrorResponseDTO() {
    }

    public MultipleErrorResponseDTO(String logreg, List<MyError> errors) {
        this.logreg = logreg;
        this.errors = errors;
    }

    public String getLogreg() {
        return logreg;
    }

    public List<MyError> getErrors() {
        return errors;
    }

}
