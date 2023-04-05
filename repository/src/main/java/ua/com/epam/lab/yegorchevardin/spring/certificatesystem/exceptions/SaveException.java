package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

/**
 * Exception for case if entity cannot be saved in the database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class SaveException extends RuntimeException {
    public SaveException(String message) {
        super(message);
    }
}
