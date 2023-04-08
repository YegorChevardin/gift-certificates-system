package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagQueries;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.AbstractDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.TagDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.QueryBuilder;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * DAO class for Tag entities to get them from database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class TagDAOImpl extends AbstractDAO<Tag> implements TagDAO {

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate, ResultSetExtractor<List<Tag>> resultSetExtractor) {
        super(jdbcTemplate, resultSetExtractor);
    }

    @Override
    public Tag getById(Long id) {
        return executeQueryAsSingleEntity(TagQueries.SELECT_BY_ID.getValue(), id)
                .orElseThrow(
                        () -> new DataNotFoundException("Cannot find tag with this id: " + id)
                );
    }

    @Override
    public List<Tag> getAll() {
        return executeQuery(TagQueries.SELECT_ALL_TAGS.getValue());
    }

    @Transactional
    @Override
    public void insert(Tag entity) {
        executeUpdateQuery(TagQueries.INSERT_TAGS.getValue(), entity.getValue());
    }

    @Override
    public void removeById(long id) {
        int affectedRows = executeUpdateQuery(TagQueries.DELETE_BY_ID.getValue(), id);
        if (affectedRows == 0) {
            throw new DataNotFoundException(
                    "Cannot delete tag with this id " + id + ", because it's not exists"
            );
        }
    }

    @Override
    public List<Tag> getWithFilter(Map<String, String> params) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String actualQuery = queryBuilder.buildQueryWithFilters(
                TagQueries.SELECT_ALL_TAGS.getValue(), params
        );
        return executeQuery(actualQuery);
    }

    @Override
    public Tag getByValue(String name) {
        return executeQueryAsSingleEntity(TagQueries.SELECT_BY_NAME.getValue(), name)
                .orElseThrow(
                        () -> new DataNotFoundException("Cannot find tag with this name: " + name)
                );
    }
}
