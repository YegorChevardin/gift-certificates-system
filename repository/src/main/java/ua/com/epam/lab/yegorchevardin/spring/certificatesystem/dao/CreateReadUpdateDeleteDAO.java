package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

/**
 * Interface that represents create, read, update and delete operations in the database
 * @param <T> type of entity for processing
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface CreateReadUpdateDeleteDAO<T> extends CreateReadDeleteDAO<T> {
    /**
     * Updates an entity of {@link T} datatype
     * @param item an updated entity
     */
    void update(T item);
}
