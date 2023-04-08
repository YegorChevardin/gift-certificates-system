package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateQueries;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.AbstractDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.TagDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.QueryBuilder;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.extractors.GiftCertificateFieldExtractor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.ExecuteQueryRequestException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.SaveException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DAO class for GiftCertificate entities to get them from database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class GiftCertificateDAOImpl
        extends AbstractDAO<GiftCertificate>
        implements GiftCertificateDAO {
    private final TagDAO tagDAO;
    private final GiftCertificateFieldExtractor giftCertificateFieldExtractor;

    @Autowired
    public GiftCertificateDAOImpl(
            TagDAO tagDAO,
            JdbcTemplate jdbcTemplate,
            ResultSetExtractor<List<GiftCertificate>> resultSetExtractor,
            GiftCertificateFieldExtractor giftCertificateFieldExtractor) {
        super(jdbcTemplate, resultSetExtractor);
        this.tagDAO = tagDAO;
        this.giftCertificateFieldExtractor = giftCertificateFieldExtractor;
    }

    @Override
    public GiftCertificate getById(Long id) {
        return getEntityById(id);
    }

    private GiftCertificate getEntityById(Long id) {
        return executeQueryAsSingleEntity(
                GiftCertificateQueries.SELECT_CERTIFICATE_BY_ID.getValue(),
                id
        ).orElseThrow(
                () -> new DataNotFoundException(
                        "Cannot find gift certificates by this id: " + id
                )
        );
    }

    @Override
    public GiftCertificate getByName(String name) {
        return executeQueryAsSingleEntity(
                GiftCertificateQueries.SELECT_CERTIFICATE_BY_NAME.getValue(),
                name
        ).orElseThrow(
                () -> new DataNotFoundException(
                        "Cannot find gift certificate by this name: " + name
                )
        );
    }

    @Override
    public List<GiftCertificate> getAll() {
        return executeQuery(
                GiftCertificateQueries.SELECT_ALL_CERTIFICATES.getValue()
        );
    }

    @Override
    public List<GiftCertificate> getWithFilter(Map<String, String> params) {
        String query = new QueryBuilder().buildQueryWithFilters(
                GiftCertificateQueries.SELECT_CERTIFICATE_ID.getValue(),
                params
        );

        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);
        return ids.stream().distinct().map((this::getEntityById)).toList();
    }

    @Override
    @Transactional
    public void insert(GiftCertificate entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            GiftCertificateQueries.INSERT_NEW_CERTIFICATE.getValue(),
                            Statement.RETURN_GENERATED_KEYS
                    );
                    int count = 0;
                    ps.setString(++count, entity.getName());
                    ps.setString(++count, entity.getDescription());
                    ps.setInt(++count, entity.getDuration());
                    ps.setTimestamp(++count, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(++count, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setFloat(++count, entity.getPrice());
                    return ps;
                },
                keyHolder
        );
        if (keyHolder.getKey() == null) {
            throw new SaveException(
                    "GiftCertificate cannot be saved in the database"
            );
        }
        entity.setId(keyHolder.getKey().longValue());
        if (entity.getTags() != null) {
            addNewTagsToCertificate(entity);
        }
    }

    @Override
    public void removeById(long id) {
        executeUpdateQuery(
                GiftCertificateQueries.DELETE_BY_ID.getValue(),
                id
        );
    }

    @Override
    @Transactional
    public void update(GiftCertificate item) {
        item.setLastUpdateDate(String.valueOf(LocalDateTime.now()));
        Map<String, String> params = giftCertificateFieldExtractor.extractData(item);
        int affectedRows;

        try {
            affectedRows = executeUpdateQuery(
                    new QueryBuilder().buildUpdateQuery(
                            GiftCertificateQueries.UPDATE_CERTIFICATE.getValue(),
                            params)
            );
        } catch (BadSqlGrammarException e) {
            throw new ExecuteQueryRequestException();
        }

        if (affectedRows == 0) {
            throw new DataNotFoundException(
                    "Could not find entity with this id:" + item.getId()
            );
        }
        if (item.getTags() != null) {
            updateCertificateTags(item);
        }
    }

    private void addNewTagsToCertificate(GiftCertificate entity) {
        List<Tag> newTags = createTagsWithId(entity.getTags());
        newTags.forEach((element) -> {
            executeUpdateQuery(
                    GiftCertificateQueries.INSERT_CERTIFICATE_TAGS_RELATION.getValue(),
                    entity.getId(),
                    element.getId()
            );
        });
    }

    private List<Tag> createTagsWithId(List<Tag> requestTags) {
        List<Tag> newTagsWithId = new ArrayList<>(requestTags.size());
        requestTags.forEach((element) -> {
            Tag tagWithId;
            try {
                tagWithId = tagDAO.getByValue(element.getValue());
            } catch (DataNotFoundException e) {
                tagDAO.insert(element);
                tagWithId = tagDAO.getByValue(element.getValue());
            }
            newTagsWithId.add(tagWithId);
        });
        return newTagsWithId;
    }

    private void updateCertificateTags(GiftCertificate item) {
        List<Tag> newTags = createTagsWithId(item.getTags());
        executeUpdateQuery(
                GiftCertificateQueries.DELETE_ASSOCIATED_TAGS.getValue(),
                item.getId()
        );
        newTags.forEach((newTag) ->
                executeUpdateQuery(
                        GiftCertificateQueries.INSERT_CERTIFICATE_TAGS_RELATION.getValue(),
                        item.getId(),
                        newTag.getId()
                )
        );
    }
}
