package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;

import java.util.List;
import java.util.Map;

/**
 * Interface of service to handle login for GiftCertificateDTO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface GiftCertificateService extends CRUDService<GiftCertificateDTO> {
    /**
     * Method for getting gift certificate by name
     * @param name name for search
     * @return GiftCertificateDTO
     */
    GiftCertificateDTO getGiftCertificateDTOByName(String name);

    /**
     * Method for getting gift certificate by some parameter
     * @param parameters parameters for search
     * @return List of GiftCertificateDTO
     */
    List<GiftCertificateDTO> getGiftCertificatesDTOByParameter(Map<String, String> parameters);
}
