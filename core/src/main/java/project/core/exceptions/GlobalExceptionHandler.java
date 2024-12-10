package project.core.exceptions;

import jakarta.persistence.EntityNotFoundException;
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
import project.core.exceptions.admin.RoleException;
import project.core.exceptions.profile.PersonalDataException;
import project.core.exceptions.profile.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String BAD_REQUEST = HttpStatus.BAD_REQUEST.name();
    private static final String NOT_FOUND = HttpStatus.NOT_FOUND.name();

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST,  "Invalid role. Allowed values are: " + stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUserException(UserException e) {

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonalDataException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handlePersonalDataException(PersonalDataException e) {

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {

        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, stringFix(e.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> roleException(RoleException e) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.name(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
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
