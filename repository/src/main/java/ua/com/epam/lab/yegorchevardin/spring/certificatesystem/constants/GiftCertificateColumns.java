package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hard coded column values of gift_certificate table
 * for GiftCertificate entity
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum GiftCertificateColumns {
    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    PRICE("price"),
    DURATION("duration"),
    CREATE_DATE("create_date"),
    LAST_UPDATE_DATE("last_update_date");

    private final String value;
}
