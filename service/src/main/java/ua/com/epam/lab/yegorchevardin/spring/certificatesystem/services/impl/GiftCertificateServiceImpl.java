package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.GiftCertificateConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.GiftCertificateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateConvertor giftCertificateConvertor;

    @Override
    public void insertNewObject(GiftCertificateDTO object) {
        if (checkIfExist(object.getName())) {
            throw new DataFoundException(
                    "Cannot create gift certificate with this name, because it's already exits: " + object.getName()
            );
        }
        GiftCertificate entity = giftCertificateConvertor.convertToEntity(object);
        entity.setCreatedDate(String.valueOf(LocalDateTime.now()));
        entity.setLastUpdateDate(String.valueOf(LocalDateTime.now()));
        giftCertificateDAO.insert(entity);
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
        giftCertificateDAO.getById(id);
        giftCertificateDAO.removeById(id);
    }

    @Override
    public GiftCertificateDTO update(GiftCertificateDTO object) {
        if (object.getId() == null) {
            throw new DataNotFoundException(
                    "Cannot update object with en empty id."
            );
        }
        if (checkIfExist(object.getName())
                && !Objects.equals(giftCertificateDAO.getByName(object.getName()).getId(), object.getId())) {
            throw new DataFoundException(
                    "Cannot update object to this name, because another gift certificate " +
                            "with this name is already exist: " + object.getName()
            );
        }

        GiftCertificate entity = giftCertificateConvertor.convertToEntity(object);
        entity.setId(object.getId());
        giftCertificateDAO.update(entity);
        return giftCertificateConvertor.convertToDTO(giftCertificateDAO.getById(entity.getId()));
    }

    private boolean checkIfExist(String name) {
        try {
            giftCertificateDAO.getByName(name);
        } catch (DataNotFoundException e) {
            return false;
        }
        return true;
    }
}
