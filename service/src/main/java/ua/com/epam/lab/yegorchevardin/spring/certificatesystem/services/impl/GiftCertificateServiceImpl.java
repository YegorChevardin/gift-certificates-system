package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.GiftCertificateColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.SortingParameters;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.constants.TagColumns;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.GiftCertificateConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.IncorrectSortingParameter;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.GiftCertificateService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private static final String ALIAS_FOR_TAG_NAME = "tag_name";
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

    @Override
    public List<GiftCertificateDTO> getGiftCertificatesDTOByParameter(Map<String, String> parameters) {
        return giftCertificateDAO.getWithFilter(prepareParameters(parameters)).stream()
                .map(giftCertificateConvertor::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDTO getGiftCertificateDTOByName(String name) {
        return giftCertificateConvertor.convertToDTO(giftCertificateDAO.getByName(name));
    }

    private Map<String, String> prepareParameters(Map<String, String> parameters) {
        Map<String, String> preparedParameters = new HashMap<>();
        Map<String, String> copyParameters = new HashMap<>(parameters);
        Map<String, String> sortingParameters = new HashMap<>();

        parameters.forEach((key, value) -> {
            if(key.equals(GiftCertificateColumns.NAME.getValue())
                    || key.equals(GiftCertificateColumns.DESCRIPTION.getValue())) {
                preparedParameters.put(key, value);
                copyParameters.remove(key);
            } else if (key.equals(SortingParameters.DATE_SORT.getValue())
                    || key.equals(SortingParameters.NAME_SORT.getValue())
                    || key.equals(SortingParameters.TAG_SORT.getValue())
            ) {
                validateParameter(key, value);
                sortingParameters.put(key, value);
                copyParameters.remove(key);
            } else if (key.equals(ALIAS_FOR_TAG_NAME)) {
                preparedParameters.put(TagColumns.NAME.getValue(), value);
                copyParameters.remove(key);
            }
        });

        if (!copyParameters.isEmpty()) {
            throw IncorrectSortingParameter.createIncorrectParameterException(
                    copyParameters.keySet().toArray(key -> new String[0])
            );
        }

        preparedParameters.putAll(sortingParameters);
        return preparedParameters;
    }

    private void validateParameter(String paramName, String paramValue) {
        if (!paramValue.equals("ASC") && !paramValue.equals("DESC")) {
            throw IncorrectSortingParameter.createIncorrectParameterException(
                    paramName + " => " + paramValue
            );
        }
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
