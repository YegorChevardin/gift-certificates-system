package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.GiftCertificateConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.GiftCertificateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateConvertor giftCertificateConvertor;

    @Override
    public void insertNewObject(GiftCertificateDTO object) {
        //todo
    }

    @Override
    public List<GiftCertificateDTO> getAll() {
        return giftCertificateDAO.getAll().stream().map(
                giftCertificateConvertor::convertToDTO).collect(Collectors.toList()
        );
    }

    @Override
    public GiftCertificateDTO getById(Long id) {
        return giftCertificateConvertor.convertToDTO(giftCertificateDAO.getById(id));
    }

    @Override
    public void deleteById(Long id) {
        giftCertificateDAO.removeById(id);
    }

    @Override
    public GiftCertificateDTO updateById(Long id) {
        //todo
        return null;
    }
}
