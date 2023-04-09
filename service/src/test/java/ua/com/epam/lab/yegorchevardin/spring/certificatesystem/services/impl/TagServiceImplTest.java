package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.TagConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.impl.TagConvertorImpl;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.TagDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.Tag;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test class for tag service
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    private static final List<Tag> TAGS = List.of(
            new Tag(1L, "valentine"),
            new Tag(2L, "birthday"),
            new Tag(3L, "easter")
    );
    @Mock
    private TagDAO tagDAO;

    @Mock
    private TagConvertor tagConvertor;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void getAll_ReturnListOfTagDTO() {
        List<TagDTO> expectedResult = TAGS.stream().map(
                (element) -> {
                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(element.getValue());
                    tagDTO.setId(element.getId());
                    return tagDTO;
                }
        ).toList();

        when(tagDAO.getAll()).thenReturn(TAGS);
        for (int i = 0; i < TAGS.size(); i++) {
            when(tagConvertor.convertToDTO(TAGS.get(i))).thenReturn(expectedResult.get(i));
        }

        Assertions.assertTrue(tagService.getAll().containsAll(expectedResult));
    }

    @Test
    public void getById_returnTagDTO() {
        TagDTO expected = new TagDTO();
        expected.setId(1L);
        expected.setName("valentine");

        when(tagDAO.getById(1L)).thenReturn(TAGS.get(1));
        when(tagConvertor.convertToDTO(tagDAO.getById(1L))).thenReturn(expected);

        Assertions.assertEquals(expected, tagService.getById(1L));
    }

    @Test
    public void getById_throwDataNotFoundException() {
        TagDTO expected = new TagDTO();
        expected.setId(1L);
        expected.setName("valentine");

        when(tagDAO.getById(1L)).thenThrow(new DataNotFoundException("Not Found"));

        Assertions.assertThrows(DataNotFoundException.class,
                () -> tagService.getById(1L));
    }


}
