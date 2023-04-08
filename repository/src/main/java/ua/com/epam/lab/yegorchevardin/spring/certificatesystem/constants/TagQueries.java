package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Constants for queries in tags database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@AllArgsConstructor
public enum TagQueries {
    SELECT_BY_ID("select * from tags where id = ?;"),
    SELECT_BY_NAME("select * from tags where value = ?;"),
    SELECT_ALL_TAGS("select * from tags;"),
    INSERT_TAGS("insert into tags(value) values (?);"),
    DELETE_BY_ID("delete from tags where id = ?;");

    @Getter
    private final String value;
}
