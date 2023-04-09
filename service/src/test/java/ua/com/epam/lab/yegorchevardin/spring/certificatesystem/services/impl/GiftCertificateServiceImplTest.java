package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.convertors.GiftCertificateConvertor;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dao.GiftCertificateDAO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.entities.GiftCertificate;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.exceptions.DataNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test class for gift certificate service
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {
    private static final List<GiftCertificate> GIFTS = List.of(
            new GiftCertificate(
                    1L,
                    "someName",
                    "someDesc",
                    13F,
                    10,
                    String.valueOf(LocalDateTime.now()),
                    String.valueOf(LocalDateTime.now()),
                    new ArrayList<>()
            )
    );
    @Mock
    private GiftCertificateDAO giftCertificateDAO;
    @Mock
    private GiftCertificateConvertor giftCertificateConvertor;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @Test
    public void getAll_returnsListOFGiftCertificateDTO() {
        List<GiftCertificateDTO> giftCertificateDTOList = GIFTS.stream().map(
                (element) -> {
                    GiftCertificateDTO dto = new GiftCertificateDTO();
                    dto.setId(element.getId());
                    dto.setName(element.getName());
                    dto.setDescription(element.getDescription());
                    dto.setDuration(element.getDuration());
                    dto.setPrice(element.getPrice());
                    dto.setCreatedDate(element.getCreatedDate());
                    dto.setLastUpdateDate(element.getLastUpdateDate());
                    dto.setTags(new ArrayList<>());
                    return dto;
                }
        ).toList();

        when(giftCertificateDAO.getAll()).thenReturn(GIFTS);
        for (int i = 0; i < GIFTS.size(); i++) {
            when(giftCertificateConvertor.convertToDTO(GIFTS.get(i))).
                    thenReturn(giftCertificateDTOList.get(i));
        }

        Assertions.assertTrue(giftCertificateService.getAll()
                .containsAll(giftCertificateDTOList));
    }

    @Test
    public void getById_returnGiftCertificateDTO() {
        GiftCertificateDTO dto = new GiftCertificateDTO();
        dto.setId(GIFTS.get(0).getId());
        dto.setName(GIFTS.get(0).getName());
        dto.setDescription(GIFTS.get(0).getDescription());
        dto.setDuration(GIFTS.get(0).getDuration());
        dto.setPrice(GIFTS.get(0).getPrice());
        dto.setCreatedDate(GIFTS.get(0).getCreatedDate());
        dto.setLastUpdateDate(GIFTS.get(0).getLastUpdateDate());
        dto.setTags(new ArrayList<>());

        when(giftCertificateDAO.getById(1L)).thenReturn(GIFTS.get(0));
        when(giftCertificateConvertor.convertToDTO(giftCertificateDAO.getById(1L)))
                .thenReturn(dto);

        Assertions.assertEquals(giftCertificateService.getById(1L), dto);
    }

    @Test
    public void getById_throwDataNotFoundException() {
        GiftCertificateDTO dto = new GiftCertificateDTO();
        dto.setId(GIFTS.get(0).getId());
        dto.setName(GIFTS.get(0).getName());
        dto.setDescription(GIFTS.get(0).getDescription());
        dto.setDuration(GIFTS.get(0).getDuration());
        dto.setPrice(GIFTS.get(0).getPrice());
        dto.setCreatedDate(GIFTS.get(0).getCreatedDate());
        dto.setLastUpdateDate(GIFTS.get(0).getLastUpdateDate());
        dto.setTags(new ArrayList<>());

        when(giftCertificateDAO.getById(1L)).thenThrow(
                new DataNotFoundException("Not found")
        );

        Assertions.assertThrows(
                DataNotFoundException.class,
                () -> giftCertificateService.getById(1L)
        );
    }
}
