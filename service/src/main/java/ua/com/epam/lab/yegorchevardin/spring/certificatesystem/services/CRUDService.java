package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services;

/**
 * Service for implementing create, read, delete and update functionality
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface CRUDService<T> extends CRDService<T> {
    /**
     * Method for updating element by id
     * @param object object to update
     * @return Updated object
     */
    T update(T object);
}
