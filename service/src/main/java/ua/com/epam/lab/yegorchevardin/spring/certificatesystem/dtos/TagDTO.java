package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Data transfer object for Tag entity
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
public class TagDTO {
    private Long id;
    @NotNull
    @Length(min = 2, max = 45, message = "Tag name should range between 2 and 45 characters")
    private String name;
}
