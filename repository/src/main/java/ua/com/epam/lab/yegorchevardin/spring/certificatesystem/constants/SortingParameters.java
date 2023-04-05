package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Constants that representing allowed sorting parameters
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@AllArgsConstructor
public enum SortingParameters {
    DATE_SORT("date_sort"),
    NAME_SORT("name_sort"),
    TAG_SORT("tag_sort");

    @Getter
    private final String value;
}
