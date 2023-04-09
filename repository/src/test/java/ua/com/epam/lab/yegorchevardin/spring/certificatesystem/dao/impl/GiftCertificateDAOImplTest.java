package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.config.H2DatabaseConfig;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.SortingParameters;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.IncorrectSortingParameter;
import java.util.*;

/**
 * Test class for GiftCertificateDAO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ContextConfiguration(classes = H2DatabaseConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class GiftCertificateDAOImplTest {
    @Autowired
    private GiftCertificateDAO giftCertificateDao;
    private static final long EXISTED_ID = 1;
    private static final long NOT_EXISTED_ID = 999;
    private static final String CORRECT_CERTIFICATE_NAME = "certificate";
    private static final String CORRECT_DESCRIPTION = "description";
    private static final String INCORRECT_PARAMETER_VALUE = "incorrect parameter value";
    private static final String ASCENDING = "ASC";
    private static final String DESCENDING = "DESC";

    private static final GiftCertificate GIFT_CERTIFICATE_1 = new GiftCertificate(1L, "giftCertificate1",
            "description1", 99.9F, 1, "2020-10-20T07:20:15.156",
            "2020-10-20T07:20:15.156",
            new ArrayList<>());

    private static final GiftCertificate GIFT_CERTIFICATE_2 = new GiftCertificate(2L, "giftCertificate3",
            "description3", 100.9F, 3, "2019-10-20T07:20:15.156",
            "2019-10-20T07:20:15.156",
            new ArrayList<>());

    private static final GiftCertificate GIFT_CERTIFICATE_3 = new GiftCertificate(3L, "giftCertificate2",
            "description2", 999.9F, 2, "2018-10-20T07:20:15.156",
            "2018-10-20T07:20:15.156",
            new ArrayList<>());

    @Test
    public void getByIdTest_CorrectId() {
        GiftCertificate actual = giftCertificateDao.getById(EXISTED_ID);
        Assertions.assertEquals(GIFT_CERTIFICATE_1, actual);
    }

    @Test
    public void getByIdTest_NotExistedId() {
        Assertions.assertThrows(DataNotFoundException.class,
                () -> giftCertificateDao.getById(NOT_EXISTED_ID));
    }

    @Test
    public void getAllTest_ExistedRows() {
        List<GiftCertificate> expected = Arrays.asList(GIFT_CERTIFICATE_1, GIFT_CERTIFICATE_2, GIFT_CERTIFICATE_3);

        Assertions.assertTrue(giftCertificateDao.getAll().containsAll(expected));
    }

    @Test
    @Sql({"classpath:DeleteAllRowsFromCertificatesTable.sql"})
    @Sql(scripts = {"classpath:FillingCertificatesTable.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTest_NoRowsInTable() {
        Assertions.assertTrue(giftCertificateDao.getAll().isEmpty());
    }

    @Test
    public void getWithFiltersTest_CorrectParams() {
        Map<String, String> filterParams = new LinkedHashMap<>();
        filterParams.put(GiftCertificateColumns.NAME.getValue(), CORRECT_CERTIFICATE_NAME);
        filterParams.put(GiftCertificateColumns.DESCRIPTION.getValue(), CORRECT_DESCRIPTION);
        filterParams.put(SortingParameters.NAME_SORT.getValue(), ASCENDING);
        filterParams.put(SortingParameters.DATE_SORT.getValue(), DESCENDING);

        List<GiftCertificate> expected = Arrays.asList(GIFT_CERTIFICATE_1, GIFT_CERTIFICATE_2);
        Assertions.assertTrue(giftCertificateDao.getWithFilter(filterParams).containsAll(expected));
    }

    @Test
    public void getWithFiltersTest_IncorrectParam() {
        Map<String, String> filterParams = new LinkedHashMap<>();
        filterParams.put(GiftCertificateColumns.NAME.getValue(), CORRECT_CERTIFICATE_NAME);
        filterParams.put(GiftCertificateColumns.DESCRIPTION.getValue(), CORRECT_DESCRIPTION);
        filterParams.put(SortingParameters.DATE_SORT.getValue(), DESCENDING);
        filterParams.put("incorrect_param", INCORRECT_PARAMETER_VALUE);
        Assertions.assertThrows(IncorrectSortingParameter.class,
                () -> giftCertificateDao.getWithFilter(filterParams));
    }
}
