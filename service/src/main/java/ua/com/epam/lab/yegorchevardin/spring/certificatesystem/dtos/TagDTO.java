package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos;

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
    @Length(min = 2, max = 45)
    private String name;
}
