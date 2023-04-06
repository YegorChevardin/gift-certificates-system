package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.GiftCertificateConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.TagConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GiftCertificateConvertorImpl implements GiftCertificateConvertor {
    private final TagConvertor tagConvertor;

    @Override
    public GiftCertificateDTO convertToDTO(GiftCertificate entity) {
        GiftCertificateDTO dto = new GiftCertificateDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastUpdateDate(entity.getLastUpdateDate());
        dto.setTags(entity.getTags().stream().map(
                tagConvertor::convertToDTO).collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public GiftCertificate convertToEntity(GiftCertificateDTO dto) {
        GiftCertificate entity = new GiftCertificate();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setTags(dto.getTags().stream().map(
                tagConvertor::convertToEntity).collect(Collectors.toList())
        );
        return entity;
    }
}
