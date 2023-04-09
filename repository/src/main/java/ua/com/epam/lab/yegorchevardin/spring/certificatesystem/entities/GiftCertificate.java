package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of gift_certificate row in an object
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificate {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer duration;
    private String createdDate;
    private String lastUpdateDate;
    private List<Tag> tags = new ArrayList<>();
}
