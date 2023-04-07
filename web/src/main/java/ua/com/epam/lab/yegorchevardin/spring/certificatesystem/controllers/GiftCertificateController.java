package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.GiftCertificateListResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.GiftCertificateResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.GiftCertificateService;

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
     * @return ResponseEntity<TagListResponse> response entity with all gift certificates
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
}
