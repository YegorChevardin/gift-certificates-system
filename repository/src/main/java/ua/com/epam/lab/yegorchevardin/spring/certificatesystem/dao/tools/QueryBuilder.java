package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools;

import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.SortingParameters;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.IncorrectSortingParameter;

import java.util.Map;

/**
 * Class for creation selections condition in SQL queries
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class QueryBuilder {
    /**
     * Builds a query with filter parameters
     * @param basicQuery query that contains DML command and table name
     * @param filterParams parameters by which the selection is filtered
     * @return a string SQL query
     */
    public String buildQueryWithFilters(String basicQuery, Map<String, String> filterParams) {
        StringBuilder query = new StringBuilder(basicQuery);
        filterParams.forEach((key, value) -> {
            String param = key.toLowerCase();
            if(param.equals(GiftCertificateColumns.NAME.getValue())
                    || param.equals(GiftCertificateColumns.DESCRIPTION.getValue())
                    || param.equals(TagColumns.NAME.getValue())) {
                addPartParameter(query, param, value);
            } else if (param.equals(SortingParameters.DATE_SORT.getValue())) {
                addSortParameter(query, GiftCertificateColumns.CREATE_DATE.getValue(), value);
            } else if (param.equals(SortingParameters.NAME_SORT.getValue())) {
                addSortParameter(query, GiftCertificateColumns.NAME.getValue(), value);
            } else if (param.equals(SortingParameters.TAG_SORT.getValue())) {
                addSortParameter(query, TagColumns.NAME.getValue(), value);
            } else {
                throw IncorrectSortingParameter.createIncorrectParameterException(param);
            }
        });
        return query.toString();
    }

    /**
     * Builds a query with updated parameters.
     * @param basicQuery query that contains DML command and table name
     * @param updatableParams updated parameters
     * @return a string SQL query
     */
    public String buildUpdateQuery(String basicQuery, Map<String, String> updatableParams) {
        StringBuilder updateQuery = new StringBuilder(basicQuery);
        String id = updatableParams.get(GiftCertificateColumns.ID.getValue());
        updatableParams.remove(GiftCertificateColumns.ID.getValue());
        updatableParams.forEach((key, value) -> {
            updateQuery.append(key)
                    .append("=")
                    .append('"').append(value).append('"')
                    .append(", ");
        });
        updateQuery.deleteCharAt(updateQuery.length() - 2);
        updateQuery.append("where id=").append(id);
        return updateQuery.toString();
    }

    private void addPartParameter(StringBuilder query, String param, String value) {
        if (query.toString().contains("where")) {
            query.append(" and ");
        } else {
            query.append(" where ");
        }
        query.append("lower(").append(param).append(")").append(" like '%").append(value).append("%' ");
    }

    private void addSortParameter(StringBuilder query, String param, String value) {
        if (query.toString().contains("order by")) {
            query.append(", ");
        } else {
            query.append(" order by ");
        }
        query.append(param).append(" ").append(value);
    }
}
