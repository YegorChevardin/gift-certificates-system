package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data transfer object for GiftCertificate entity
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
public class GiftCertificateDTO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer duration;
    private String createdDate;
    private String lastUpdateDate;
    private List<TagDTO> tags;
}
