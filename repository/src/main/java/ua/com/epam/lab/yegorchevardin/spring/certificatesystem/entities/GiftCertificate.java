package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representation of gift_certificate row in an object
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
public class GiftCertificate {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer duration;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;
}
