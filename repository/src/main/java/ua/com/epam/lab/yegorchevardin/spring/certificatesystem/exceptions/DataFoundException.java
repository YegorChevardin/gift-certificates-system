package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

/**
 * Exception for case if data is already exist in the table
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class DataFoundException extends RuntimeException {
    public DataFoundException(String message) {
        super(message);
    }
}
