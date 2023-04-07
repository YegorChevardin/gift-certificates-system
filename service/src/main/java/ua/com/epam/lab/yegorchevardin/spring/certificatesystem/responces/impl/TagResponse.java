package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.DTOResponse;

import java.time.LocalDateTime;

/**
 * Class for getting response with Tag dto object
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class TagResponse extends DTOResponse<TagDTO> {
    public TagResponse(TagDTO responseEntity) {
        super(responseEntity);
    }
}
