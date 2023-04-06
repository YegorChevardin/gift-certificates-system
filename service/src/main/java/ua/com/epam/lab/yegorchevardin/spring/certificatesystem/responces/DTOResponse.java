package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Abstract class that stands for responding with entities
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Getter
public class DTOResponse<T> {
    private final Timestamp sendAt =
            Timestamp.valueOf(LocalDateTime.now());
    private T responseEntity;
}
