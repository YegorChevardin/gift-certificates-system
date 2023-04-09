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
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.SortingParameters;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.TagDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.IncorrectSortingParameter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for the TagDAO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ContextConfiguration(classes = H2DatabaseConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TagDAOImplTest {
    private static final Tag TAG_1 = new Tag(1L, "tagName1");
    private static final Tag TAG_2 = new Tag(2L, "tagName3");
    private static final Tag TAG_3 = new Tag(3L, "tagName2");
    private static final String PART_OF_TAG_NAME = "tagna";
    private static final long NOT_EXISTED_ID = 100000;
    private static final String INCORRECT_PARAMETER_VALUE = "incorrect parameter value";

    @Autowired
    private TagDAO tagDao;

    @Test
    public void getByIdTest_CorrectId() {
        Tag actual = tagDao.getById(TAG_1.getId());
        Assertions.assertEquals(TAG_1, actual);
    }

    @Test
    public void getByIdTest_NotExistedId() {
        Assertions.assertThrows(DataNotFoundException.class, () -> tagDao.getById(NOT_EXISTED_ID));
    }

    @Test
    public void getAllTest_ExistedRows() {
        List<Tag> expectedTags = Arrays.asList(TAG_1, TAG_2, TAG_3);

        Assertions.assertTrue(tagDao.getAll().containsAll(expectedTags));
    }

    @Test
    @Sql({"classpath:DeleteAllRowsFromTagsTable.sql"})
    @Sql(scripts = {"classpath:FillTagsTable.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTest_NoRowsInTable() {
        Assertions.assertTrue(tagDao.getAll().isEmpty());
    }

    @Test
    public void getWithFiltersTest_IncorrectParam() {
        Map<String, String> filterParams = new LinkedHashMap<>();
        filterParams.put(TagColumns.NAME.getValue(), PART_OF_TAG_NAME);
        filterParams.put("incorrect_param", INCORRECT_PARAMETER_VALUE);
        Assertions.assertThrows(IncorrectSortingParameter.class, () -> tagDao.getWithFilter(filterParams));
    }

}
