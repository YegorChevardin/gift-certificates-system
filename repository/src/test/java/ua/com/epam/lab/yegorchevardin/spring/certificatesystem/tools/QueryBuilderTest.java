package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.SortingParameters;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.QueryBuilder;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.IncorrectSortingParameter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test class for QueryBuilder
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class QueryBuilderTest {
    private final QueryBuilder queryBuilder = new QueryBuilder();
    private static final String TAG_QUERY = "select * from tags ";
    private static final String CERTIFICATE_QUERY =
            "select * from gift_certificates as gc left join gift_certificates_tags as gct on gc.id = gct.gift_certificate_id left join tags as t on t.id = gct.tag_id ";
    private static final String UPDATE_CERTIFICATE_QUERY = "update gift_certificate set ";
    private static final String CORRECT_TAG_NAME = "tagName";
    private static final String CORRECT_CERTIFICATE_NAME = "giftCertificate";
    private static final String CORRECT_CERTIFICATE_DESCRIPTION = "description";
    private static final String UPDATABLE_ID = "1";
    private static final String ASCENDING = "ASC";
    private static final String DESCENDING = "DESC";
    private static final String INCORRECT_FILTER_PARAMETER = "incorrect parameter";

    @Test
    public void buildQueryWithFiltersTest_CorrectTagParams() {
        Map<String, String> parameters = new LinkedHashMap<>();

        parameters.put(TagColumns.NAME.getValue(), CORRECT_TAG_NAME);
        parameters.put(SortingParameters.TAG_SORT.getValue(), ASCENDING);

        String expected = TAG_QUERY + " where " + "lower(" + TagColumns.NAME.getValue() + ")" +
                " like '%" + CORRECT_TAG_NAME + "%' " +
                " order by " + TagColumns.NAME.getValue() + " " + ASCENDING;
        String actual = queryBuilder.buildQueryWithFilters(TAG_QUERY, parameters);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildQueryWithFiltersTest_WithIncorrectParam() {
        Map<String, String> parameters = new LinkedHashMap<>();

        parameters.put(TagColumns.NAME.getValue(), CORRECT_TAG_NAME);
        parameters.put(SortingParameters.TAG_SORT.getValue(), ASCENDING);
        parameters.put(INCORRECT_FILTER_PARAMETER, null);

        Assertions.assertThrows(IncorrectSortingParameter.class,
                () -> queryBuilder.buildQueryWithFilters(TAG_QUERY, parameters)
        );
    }

    @Test
    public void buildQueryWithFiltersTest_CorrectCertificateParams() {
        Map<String, String> parameters = new LinkedHashMap<>();

        parameters.put(GiftCertificateColumns.NAME.getValue(), CORRECT_CERTIFICATE_NAME);
        parameters.put(GiftCertificateColumns.DESCRIPTION.getValue(), CORRECT_CERTIFICATE_DESCRIPTION);
        parameters.put(SortingParameters.NAME_SORT.getValue(), ASCENDING);
        parameters.put(SortingParameters.DATE_SORT.getValue(), DESCENDING);

        String expected = CERTIFICATE_QUERY + " where " + "lower(" + GiftCertificateColumns.NAME.getValue() + ")" +
                " like '%" + CORRECT_CERTIFICATE_NAME + "%' " +
                " and " + "lower(" + GiftCertificateColumns.DESCRIPTION.getValue() + ")" + " like '%" +
                CORRECT_CERTIFICATE_DESCRIPTION + "%' " +
                " order by " + GiftCertificateColumns.NAME.getValue() + " " + ASCENDING +
                ", " +
                GiftCertificateColumns.CREATE_DATE.getValue() + " " + DESCENDING;
        String actual = queryBuilder.buildQueryWithFilters(CERTIFICATE_QUERY, parameters);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildUpdateQueryTest() {
        Map<String, String> parameters = new LinkedHashMap<>();

        parameters.put(GiftCertificateColumns.NAME.getValue(), CORRECT_CERTIFICATE_NAME);
        parameters.put(GiftCertificateColumns.DESCRIPTION.getValue(),
                CORRECT_CERTIFICATE_DESCRIPTION);
        parameters.put(GiftCertificateColumns.ID.getValue(), UPDATABLE_ID);

        String expected = UPDATE_CERTIFICATE_QUERY + GiftCertificateColumns.NAME.getValue()
                + "=\"" + CORRECT_CERTIFICATE_NAME + "\", " +
                GiftCertificateColumns.DESCRIPTION.getValue() + "=\"" + CORRECT_CERTIFICATE_DESCRIPTION + "\" " +
                "where id=" + UPDATABLE_ID;
        String actual = queryBuilder.buildUpdateQuery(UPDATE_CERTIFICATE_QUERY, parameters);
        Assertions.assertEquals(expected, actual);
    }
}
