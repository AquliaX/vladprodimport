package org.vladimirskoe.project.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler for custom application exceptions
 *
 * @author Artem Matveev
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    /**
     * Handles {@link NullObjectException}
     * @param ex - exception
     * @return {@link ResponseEntity<ErrorDetails>}
     */
    @ExceptionHandler({NullObjectException.class})
    public ResponseEntity<ErrorDetails> handleNullObjectException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.NOT_FOUND);
    }

    /**
     * Error details object
     *
     * @author Artem Matveev
     */
    private static class ErrorDetails {

        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;

        ErrorDetails(LocalDateTime timestamp, int status, String error, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
