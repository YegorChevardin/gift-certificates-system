package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Hard coded column values of tag table
 * for Tag entity
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TagColumns {
    ID("id"),
    NAME("value");

    @Getter
    private final String value;
}
