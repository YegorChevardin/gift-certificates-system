package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.DTOResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for getting response with List of Gift Certificate
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class GiftCertificateListResponse extends DTOResponse<List<GiftCertificateDTO>> {
    public GiftCertificateListResponse(List<GiftCertificateDTO> responseEntity) {
        super(responseEntity);
    }
}
