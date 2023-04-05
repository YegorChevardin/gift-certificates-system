package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

import java.util.List;
import java.util.Map;

public interface ParametersSearch<T> {
    /**
     * Method for getting a list of entities of {@link <T>} datatype by parameters
     * @param params request parameters
     * @return List of GiftCertificate entities
     */
    List<T> getWithFilter(Map<String, String> params);
}
