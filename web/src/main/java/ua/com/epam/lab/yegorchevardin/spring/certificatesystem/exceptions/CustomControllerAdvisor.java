package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling errors from controllers
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ControllerAdvice
public class CustomControllerAdvisor extends ResponseEntityExceptionHandler {
    /**
     * Constraint violation exception handler
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object>
    handleConstraintViolation(ConstraintViolationException ex) {
        return wrapExceptionToMap(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for handling page not found exception, which will throw 404 error
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return wrapExceptionToMap(ex, (HttpStatus) status);
    }

    /**
     * Method for handling DataNotFoundException, which will return 404 error
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(Exception exception) {
        return wrapExceptionToMap(exception, HttpStatus.NOT_FOUND);
    }

    /**
     * Method for handling DataFoundException and IncorrectParameterException, which will return 400 error
     */
    @ExceptionHandler({
            DataFoundException.class,
            IncorrectSortingParameter.class
    })
    public ResponseEntity<Object> handleDataFoundException(Exception exception) {
        return wrapExceptionToMap(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for handling SaveException, which will return 400 error
     */
    @ExceptionHandler({
            SaveException.class,
            ExecuteQueryRequestException.class
    })
    public ResponseEntity<Map<String, String>> handleSaveException(Exception exception) {
        return new ResponseEntity<>(
                Map.of("message:", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> wrapExceptionToMap(
            Exception exception,
            HttpStatus status
    ) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message:", exception.getMessage());
        responseMap.put("errorCode:", String.valueOf(status.value()));
        return new ResponseEntity<>(
                responseMap,
                status);
    }
}
