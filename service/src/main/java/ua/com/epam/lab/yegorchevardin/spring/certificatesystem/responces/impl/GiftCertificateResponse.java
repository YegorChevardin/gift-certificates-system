package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.DTOResponse;

import java.time.LocalDateTime;

/**
 * Class for getting response with Gift Certificate dto object
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class GiftCertificateResponse extends DTOResponse<GiftCertificateDTO> {
    public GiftCertificateResponse(GiftCertificateDTO responseEntity) {
        super(responseEntity);
    }
}
