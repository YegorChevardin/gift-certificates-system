package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Class with tools for working with database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RequiredArgsConstructor
public abstract class AbstractDAO<T> {
    protected final JdbcTemplate jdbcTemplate;
    protected final ResultSetExtractor<List<T>> resultSetExtractor;

    /**
     * Executes SQL query and returns an object of {@link T} datatype
     * @param query The SQL query that will be executed
     * @param params The SQL query parameters
     * @return An Optional of object of {@link T} datatype
     */
    public Optional<T> executeQueryAsSingleEntity(String query, Object... params) {
        List<T> items = executeQuery(query, params);
        if (items == null || items.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(items.get(0));
    }

    /**
     * Executes SQL query and returns List of objects of {@link T} datatype
     * @param query The SQL query that will be executed
     * @param params The SQL query parameters
     * @return A List of objects of {@link T} datatype
     */
    public List<T> executeQuery(String query, Object... params) {
        return jdbcTemplate.query(query, resultSetExtractor, params);
    }

    /**
     * Executes update SQL query for some objects
     * @param query The update SQL query that will be executed
     * @param params The SQL query parameters
     * @return number of update statements that were successfully executed
     */
    public int executeUpdateQuery(String query, Object... params) {
        return jdbcTemplate.update(query, params);
    }
}
