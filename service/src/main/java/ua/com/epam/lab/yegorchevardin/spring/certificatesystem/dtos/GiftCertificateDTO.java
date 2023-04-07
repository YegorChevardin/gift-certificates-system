package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object for GiftCertificate entity
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
public class GiftCertificateDTO {
    private Long id;
    @NotNull(message = "Gift Certificate name must be not null")
    @Length(min = 2, max = 45, message = "Gift certificate name should range between 2 and 45 characters")
    private String name;
    @Length(min = 5, max = 500, message = "Gift certificate description should range between 5 and 500 characters")
    private String description;
    @NotNull(message = "Gift certificate price must be not null")
    @Positive(message = "Gift certificate price must be positive")
    private Float price;
    @NotNull(message = "Gift certificate duration must be not null")
    @Positive(message = "Gift certificate duration must be positive")
    @Min(value = 1, message = "Gift certificate must be minimum one day")
    private Integer duration;
    private String createdDate;
    private String lastUpdateDate;
    private List<TagDTO> tags = new ArrayList<>();
}
