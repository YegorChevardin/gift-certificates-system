package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.TagConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.TagDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.TagService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;
    private final TagConvertor tagConvertor;

    @Override
    public void insertNewObject(TagDTO object) {
        checkIfTagExist(object.getName());
        tagDAO.insert(tagConvertor.convertToEntity(object));
    }

    @Override
    public List<TagDTO> getAll() {
        return tagDAO.getAll().stream().map(
                tagConvertor::convertToDTO
        ).collect(Collectors.toList());
    }

    @Override
    public TagDTO getById(Long id) {
        return tagConvertor.convertToDTO(tagDAO.getById(id));
    }

    @Override
    public void deleteById(Long id) {
        tagDAO.removeById(id);
    }

    private void checkIfTagExist(String name) {
        try {
            if (tagDAO.getByName(name) != null) {
                throw new DataFoundException(
                        "Tag with this name is already exist: " + name
                );
            }
        } catch (DataNotFoundException ignored) {}
    }
}
