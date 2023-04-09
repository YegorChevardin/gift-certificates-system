package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.extractors;

import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;

import java.util.HashMap;
import java.util.Map;

/**
 * This class intended for extracting fields of GiftCertificate
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Component
public class GiftCertificateFieldExtractor {
    /**
     * Extracts GiftCertificate object fields values into HashMap.
     * @param certificate entity that fields will be extracted
     * @return HashMap that contains extracted fields
     */
    public Map<String, String> extractData(GiftCertificate certificate) {
        Map<String, String> fields = new HashMap<>();

        if (certificate.getId() != 0) {
            fields.put(GiftCertificateColumns.ID.getValue(),
                    String.valueOf(certificate.getId()));
        }

        if (certificate.getName() != null && !certificate.getName().isEmpty()) {
            fields.put(GiftCertificateColumns.NAME.getValue(),
                    certificate.getName());
        }

        if (certificate.getDescription() != null && !certificate.getDescription().isEmpty()) {
            fields.put(GiftCertificateColumns.DESCRIPTION.getValue(),
                    certificate.getDescription());
        }

        if (certificate.getPrice() != null) {
            fields.put(GiftCertificateColumns.PRICE.getValue(),
                    certificate.getPrice().toString());
        }

        if (certificate.getDuration() != 0) {
            fields.put(GiftCertificateColumns.DURATION.getValue(),
                    String.valueOf(certificate.getDuration()));
        }
        fields.put(GiftCertificateColumns.LAST_UPDATE_DATE.getValue(),
                String.valueOf(certificate.getLastUpdateDate()));

        return fields;
    }
}