package finalProject.NoPainNoGain.core.dto.exception;

import finalProject.NoPainNoGain.core.exception.MyError;

import java.util.List;

public record MultipleErrorResponseDto(String logref, List<MyError> errors) {

}
