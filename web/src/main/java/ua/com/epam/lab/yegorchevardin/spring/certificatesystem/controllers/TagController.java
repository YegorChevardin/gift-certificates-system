package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.TagListResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.TagService;

import java.time.LocalDateTime;

/**
 * Controller for handling responses for TagsDTO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    /**
     * Method for getting all tags
     * @return ResponseEntity<TagListResponse> response entity with all tags
     */
    @GetMapping
    public ResponseEntity<TagListResponse> showAllTags() {
        return ResponseEntity.ok(
                new TagListResponse(
                        LocalDateTime.now(),
                        tagService.getAll()
                )
        );
    }
}
