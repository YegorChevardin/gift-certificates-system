package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.GiftCertificateDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.GiftCertificateListResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.GiftCertificateResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.GiftCertificateService;

import java.util.Map;

/**
 * Controller for handling responses for GiftCertificateDTO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/gift-certificates")
@RequiredArgsConstructor
@Validated
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    /**
     * Method for getting all gift certificates
     * @return response entity with all gift certificates
     */
    @GetMapping
    public ResponseEntity<GiftCertificateListResponse> showAllGiftCertificates() {
        return ResponseEntity.ok(
                new GiftCertificateListResponse(
                        giftCertificateService.getAll()
                )
        );
    }

    /**
     * Method for getting gift_certificates by some filter
     */
    @GetMapping("/filter")
    public ResponseEntity<GiftCertificateListResponse> showFilteredGiftCertificates(
            @RequestParam Map<String, String> parameters
            ) {
        return ResponseEntity.ok(
                new GiftCertificateListResponse(
                        giftCertificateService.getGiftCertificatesDTOByParameter(parameters)
                )
        );
    }

    /**
     * Method for handling finding gift certificate by name
     */
    @GetMapping("/{name}")
    public ResponseEntity<GiftCertificateResponse> showGiftCertificate(
            @PathVariable @Length(min = 2, max = 45, message = "Name must be more than 2 and less than 45 characters") String name) {
        return ResponseEntity.ok(
                new GiftCertificateResponse(
                        giftCertificateService.getGiftCertificateDTOByName(name)
                )
        );
    }

    /**
     * Method for handling finding gift certificate by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateResponse> showGiftCertificate(
            @PathVariable @Min(0) Long id) {
        return ResponseEntity.ok(
                new GiftCertificateResponse(
                        giftCertificateService.getById(id)
                )
        );
    }

    /**
     * Method for handling creation of new gift certificate
     */
    @PostMapping
    public ResponseEntity<GiftCertificateListResponse> createNewGiftCertificate(
            @RequestBody @Valid GiftCertificateDTO giftCertificateDTO
            ) {
        giftCertificateService.insertNewObject(giftCertificateDTO);
        return ResponseEntity.ok(
                new GiftCertificateListResponse(giftCertificateService.getAll())
        );
    }

    /**
     * Method for handling delete action for gift certificate objects
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GiftCertificateListResponse> deleteGiftCertificate(
            @PathVariable @Min(0) Long id
    ) {
        giftCertificateService.deleteById(id);
        return ResponseEntity.ok(
                new GiftCertificateListResponse(
                        giftCertificateService.getAll()
                )
        );
    }

    /**
     * Method for handling update action for gift certificate objects
     */
    @PutMapping
    public ResponseEntity<GiftCertificateResponse> updateGiftCertificate(
            @RequestBody @Valid GiftCertificateDTO giftCertificateDTO
    ) {
        return ResponseEntity.ok(
                new GiftCertificateResponse(
                        giftCertificateService.update(giftCertificateDTO)
                )
        );
    }
}
