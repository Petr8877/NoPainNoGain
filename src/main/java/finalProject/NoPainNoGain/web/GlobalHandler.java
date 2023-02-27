package finalProject.NoPainNoGain.web;

import finalProject.NoPainNoGain.core.dto.exception.SingleErrorResponseDTO;
import finalProject.NoPainNoGain.core.exception.MultipleErrorResponse;
import finalProject.NoPainNoGain.core.exception.SingleErrorResponse;
import finalProject.NoPainNoGain.core.dto.exception.MultipleErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SingleErrorResponseDTO catchSingleErrorResponse(SingleErrorResponse e) {
        return new SingleErrorResponseDTO("Не верный запрос", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MultipleErrorResponseDTO catchMultipleErrorResponse(MultipleErrorResponse e) {
        return new MultipleErrorResponseDTO(e.getLogref(), e.getErrors());
    }
}
