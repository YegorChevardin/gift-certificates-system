package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services;

import java.util.List;

/**
 * Service for implementing create, read, delete functionality
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface CRDService<T> {
    /**
     * Method for insertion object into database
     * @param object Object to insert
     * @return T created object
     */
    void insertNewObject(T object);

    /**
     * Method for getting all elements from the database
     * @return List<T> list of objects
     */
    List<T> getAll();

    /**
     * Method for getting element by the id
     * @param id id of the wanted element
     * @return T object
     */
    T getById(Long id);

    /**
     * Method for deleting elements by the id
     * @param id id of the wanted element
     */
    void deleteById(Long id);
}
