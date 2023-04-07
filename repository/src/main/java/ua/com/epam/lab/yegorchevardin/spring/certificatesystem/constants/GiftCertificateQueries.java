package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Constants for GIftCertificate queries for getting elements from database
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@AllArgsConstructor
public enum GiftCertificateQueries {
    SELECT_CERTIFICATE_BY_ID("select * from gift_certificates as gc left join gift_certificates_tags as gct on gc.id = gct.gift_certificate_id left join tags as t on t.id = gct.tag_id where gc.id = ?;"),
    SELECT_CERTIFICATE_BY_NAME("select * from gift_certificates as gc left join gift_certificates_tags as gct on gc.id = gct.gift_certificate_id left join tags as t on t.id = gct.tag_id where gc.name = ?;"),
    SELECT_CERTIFICATE_ID("select gc.id from gift_certificates;"),
    SELECT_ALL_CERTIFICATES("select * from gift_certificates as gc left join gift_certificates_tags as gct on gc.id = gct.gift_certificate_id left join tags as t on t.id = gct.tag_id;"),
    DELETE_BY_ID("delete from gift_certificates where id = ?;"),
    INSERT_CERTIFICATE_TAGS_RELATION("insert into gift_certificates_tags values (?, ?);"),
    INSERT_NEW_CERTIFICATE("insert into gift_certificates(name, description, duration, create_date, last_update_date, price) values (?, ?, ?, ?, ?, ?);"),
    DELETE_ASSOCIATED_TAGS("delete from gift_certificates_tags where gift_certificate_id = ?"),
    UPDATE_CERTIFICATE("update gift_certificates set ");

    @Getter
    private final String value;
}
