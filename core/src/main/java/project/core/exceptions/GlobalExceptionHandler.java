package project.core.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import project.core.exceptions.indentification.RegistrationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleRegistrationException(ConstraintViolationException e) {

        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST",  "Invalid role. Allowed values are: " + stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleRegistrationException(RegistrationException e) {

        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @Setter
    @Getter
    private class ErrorResponse {

        private String errorCode;
        private String errorMessage;

        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }

    private static String stringFix(String message) {
        return message.replaceAll(".*" + ": ", "");
    }
}
