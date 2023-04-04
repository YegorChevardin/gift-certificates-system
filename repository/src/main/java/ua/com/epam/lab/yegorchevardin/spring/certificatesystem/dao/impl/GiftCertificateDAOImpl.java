package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.SaveException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    public GiftCertificateDAOImpl(
            TagDAO tagDAO,
            JdbcTemplate jdbcTemplate,
            ResultSetExtractor<List<GiftCertificate>> resultSetExtractor) {
        super(jdbcTemplate, resultSetExtractor);
        this.tagDAO = tagDAO;
    }

    @Override
    public GiftCertificate getById(Long id) {
        return executeQueryAsSingleEntity(
                GiftCertificateQueries.SELECT_CERTIFICATE_BY_ID.getValue(),
                id).orElseThrow(
                () -> new DataNotFoundException(
                        "Cannot find gift certificates by this id:" + id
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
                    ps.setTimestamp(++count, Timestamp.valueOf(entity.getCreatedDate()));
                    ps.setTimestamp(++count, Timestamp.valueOf(entity.getLastUpdateDate()));
                    ps.setFloat(++count, entity.getPrice());
                    return ps;
                },
                keyHolder
        );
        entity.setId(getCreatedId(keyHolder));
        if (entity.getTags() == null) {
            addNewTagsToCertificate(entity);
        }
    }

    @Override
    public void removeById(long id) {
        //todo
    }

    @Override
    public void update(GiftCertificate item) {

    }

    @Override
    public List<GiftCertificate> getWithFilter(Map<String, String> params) {
        return null;
    }

    private long getCreatedId(KeyHolder keyHolder){
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) {
            throw new SaveException(
                    "GiftCertificate with id " + keyHolder.getKey() + " cannot be saved in the database"
            );
        }
        return (long) keys.get("id");
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
            Tag tagWithId = tagDAO.getByName(element.getName());
            newTagsWithId.add(tagWithId);
        });
        return newTagsWithId;
    }
}
