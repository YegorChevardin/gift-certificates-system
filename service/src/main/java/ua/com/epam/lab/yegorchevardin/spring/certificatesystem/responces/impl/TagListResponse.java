package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.DTOResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for getting response with List of Tags
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class TagListResponse extends DTOResponse<List<TagDTO>> {
    public TagListResponse(LocalDateTime sendAt, List<TagDTO> responseEntity) {
        super(sendAt, responseEntity);
    }
}
