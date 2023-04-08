package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

/**
 * Exception for case if sql grammar was bad
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class ExecuteQueryRequestException extends RuntimeException {
    private static final String MESSAGE =
            "Cannot execute this request, try to rename your parameters";
    public ExecuteQueryRequestException() {
        super(MESSAGE);
    }
}
