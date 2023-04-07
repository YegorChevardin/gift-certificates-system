package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.impl;

import org.springframework.stereotype.Component;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.TagConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;

@Component
public class TagConvertorImpl implements TagConvertor {
    @Override
    public TagDTO convertToDTO(Tag entity) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(entity.getId());
        tagDTO.setName(entity.getName());
        return tagDTO;
    }

    @Override
    public Tag convertToEntity(TagDTO dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        return tag;
    }
}
