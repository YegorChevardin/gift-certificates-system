package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of the ResultSetExtractor interface for Tag class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class TagExtractor implements ResultSetExtractor<List<Tag>> {
    @Override
    public List<Tag> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Tag> tags = new ArrayList<>();
        while (rs.next()) {
            Tag tag = new Tag();
            tag.setId(rs.getLong(TagColumns.ID.getValue()));
            tag.setName(rs.getString(TagColumns.NAME.getValue()));
            tags.add(tag);
        }
        return tags;
    }
}
