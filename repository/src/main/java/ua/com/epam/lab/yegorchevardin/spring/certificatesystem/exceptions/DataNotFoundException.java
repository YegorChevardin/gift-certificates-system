package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

/**
 * Exception for case if data was not found in the database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
