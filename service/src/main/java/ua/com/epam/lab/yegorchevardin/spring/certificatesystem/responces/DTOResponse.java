package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces;

import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Abstract class that stands for responding with entities
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Getter
public abstract class DTOResponse<T> {
    private final String sendAt = LocalDateTime.now().toString();
    private final T data;

    public DTOResponse(T data) {
        this.data = data;
    }
}
