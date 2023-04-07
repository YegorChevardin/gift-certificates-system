package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors;

/**
 * Interface for defining convert classes
 * @param <E> entity class
 * @param <D> dto class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface Convertor <E, D> {
    /**
     * Method for converting entity object to dto
     * @param entity entity to convert
     * @return D DTO object
     */
    D convertToDTO(E entity);

    /**
     * Method for converting dto object to entity
     * @param dto dto object to convert
     * @return E entity object for database
     */
    E convertToEntity(D dto);
}
