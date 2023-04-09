package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.tools.extractors;

import org.junit.jupiter.api.Test;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.tools.extractors.GiftCertificateFieldExtractor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns.*;

/**
 * Test class for GiftCertificateFieldExtractor
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class GiftCertificateFieldExtractorTest {
    private final GiftCertificateFieldExtractor extractor = new GiftCertificateFieldExtractor();

    private static final String EXPECTED_ID = "1";
    private static final String EXPECTED_NAME = "name";
    private static final String EXPECTED_DESCRIPTION = "description";
    private static final String EXPECTED_PRICE = "9.99";
    private static final String EXPECTED_DURATION = "365";
    private static final String EXPECTED_LAST_UPDATE_DATE = "2022-04-19T20:37:22.156";

    @Test
    public void testExtract() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(Long.parseLong(EXPECTED_ID));
        giftCertificate.setName(EXPECTED_NAME);
        giftCertificate.setDescription(EXPECTED_DESCRIPTION);
        giftCertificate.setPrice(Float.valueOf(EXPECTED_PRICE));
        giftCertificate.setDuration(Integer.parseInt(EXPECTED_DURATION));
        giftCertificate.setLastUpdateDate(EXPECTED_LAST_UPDATE_DATE);

        Map<String, String> actual = extractor.extractData(giftCertificate);
        Map<String, String> expected = new HashMap<>();

        expected.put(ID.getValue(), EXPECTED_ID);
        expected.put(NAME.getValue(), EXPECTED_NAME);
        expected.put(DESCRIPTION.getValue(), EXPECTED_DESCRIPTION);
        expected.put(PRICE.getValue(), EXPECTED_PRICE);
        expected.put(DURATION.getValue(), EXPECTED_DURATION);
        expected.put(LAST_UPDATE_DATE.getValue(), EXPECTED_LAST_UPDATE_DATE);
        assertEquals(expected, actual);
    }
}
