package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representation of tag row in an object
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;
    private String value;
}
