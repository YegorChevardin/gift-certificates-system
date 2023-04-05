package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

/**
 * Class for handling errors from controllers
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ControllerAdvice
public class CustomControllerAdvisor {
    @ExceptionHandler({
            SaveException.class,
            DataNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handleDatabaseErrors(Exception exception) {
        return new ResponseEntity<>(Map.of("message: ",exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
