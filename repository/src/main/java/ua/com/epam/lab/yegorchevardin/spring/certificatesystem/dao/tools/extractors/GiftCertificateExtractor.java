package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of the ResultSetExtractor interface for GiftCertificateExtractor class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class GiftCertificateExtractor implements ResultSetExtractor<List<GiftCertificate>> {
    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GiftCertificate> certificates = new ArrayList<>();
        boolean hasRows = rs.next();

        while (hasRows && !rs.isAfterLast()) {
            GiftCertificate certificate = new GiftCertificate();
            certificate.setId(rs.getLong(GiftCertificateColumns.ID.getValue()));
            certificate.setName(rs.getString(GiftCertificateColumns.NAME.getValue()));
            certificate.setDescription(rs.getString(GiftCertificateColumns.DESCRIPTION.getValue()));
            certificate.setDuration(rs.getInt(GiftCertificateColumns.DURATION.getValue()));
            certificate.setCreatedDate(
                    String.valueOf(
                            rs.getTimestamp(GiftCertificateColumns.CREATE_DATE.getValue())
                                    .toLocalDateTime()
                    )
            );
            certificate.setLastUpdateDate(
                    String.valueOf(rs.getTimestamp(GiftCertificateColumns.LAST_UPDATE_DATE.getValue())
                            .toLocalDateTime())
            );
            certificate.setPrice(rs.getFloat(GiftCertificateColumns.PRICE.getValue()));

            List<Tag> tags = extractTagList(certificate.getId(), rs);
            certificate.setTags(tags);
            certificates.add(certificate);
        }
        return certificates;
    }

    private List<Tag> extractTagList(long certificateId, ResultSet rs) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        while (!rs.isAfterLast()
                && rs.getLong("gift_certificate_id") == certificateId
                && rs.getLong("tag_id") != 0) {
            Tag tag = new Tag();
            tag.setId(rs.getLong("t." + TagColumns.ID.getValue()));
            tag.setValue(rs.getString("t." + TagColumns.NAME.getValue()));
            tags.add(tag);
            rs.next();
        }
        if (tags.size() == 0) {
            rs.next();
        }
        return tags;
    }
}
