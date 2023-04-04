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
    SELECT_BY_ID("select * from tag where id = ?;"),
    SELECT_BY_NAME("select * from tag where name ilike ?;"),
    SELECT_ALL_TAGS("select * from tag;"),
    INSERT_TAGS("insert into tag(name) values (?);"),
    DELETE_BY_ID("delete from tag where id = ?;");

    @Getter
    private final String value;
}
