package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;

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
    Tag getByName(String name);
}
