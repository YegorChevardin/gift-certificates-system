package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;

import java.util.Optional;

/**
 * Interface for abstraction of TagDAO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface TagDAO
        extends CreateReadDeleteDAO<Tag>,
        ParametersSearch<Tag> {
    /**
     * Retrieves an Optional of Tag entity by its name
     * @param name entity name
     * @return an {@link Tag} entity
     */
    Optional<Tag> getByName(String name);
}
