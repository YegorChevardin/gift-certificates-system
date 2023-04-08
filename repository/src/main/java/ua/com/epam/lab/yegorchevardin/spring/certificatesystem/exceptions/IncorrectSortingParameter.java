package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions;

/**
 * Exception for case if sorting parameter name was not correct
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class IncorrectSortingParameter extends RuntimeException {
    private static final String MESSAGE = "Sorting parameter name(-s) was incorrect: ";

    private IncorrectSortingParameter(String message) {
        super(message);
    }

    /**
     * Method for creation IncorrectParameterException
     * @param parameters parameters that was not correct
     * @return exception
     */
    public static IncorrectSortingParameter createIncorrectParameterException(String... parameters) {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE);
        for (String parameter : parameters) {
            stringBuilder.append(parameter);
        }

        return new IncorrectSortingParameter(stringBuilder.toString());
    }
}
