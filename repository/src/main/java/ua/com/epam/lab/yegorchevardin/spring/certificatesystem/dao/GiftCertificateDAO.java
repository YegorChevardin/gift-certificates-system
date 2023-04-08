package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao;

import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;

import java.util.List;

/**
 * Interface for abstraction of GiftCertificateDAO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface GiftCertificateDAO
        extends CreateReadUpdateDeleteDAO<GiftCertificate>,
        ParametersSearch<GiftCertificate> {
    /**
     * Retrieves a GiftCertificate entity by its name
     * @param name entity name
     * @return an GiftCertificate entity
     */
    GiftCertificate getByName(String name);
}
