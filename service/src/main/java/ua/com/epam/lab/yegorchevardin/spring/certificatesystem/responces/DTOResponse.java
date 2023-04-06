package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Abstract class that stands for responding with entities
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public abstract class DTOResponse<T> {
    private LocalDateTime sendAt;
    private T responseEntity;
}
