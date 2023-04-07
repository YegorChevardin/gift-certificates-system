package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.dtos.TagDTO;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.TagListResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.responces.impl.TagResponse;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.TagService;

/**
 * Controller for handling responses for TagsDTO
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@Validated
public class TagController {
    private final TagService tagService;

    /**
     * Method for getting all tags
     * @return ResponseEntity<TagListResponse> response entity with all tags
     */
    @GetMapping
    public ResponseEntity<TagListResponse> showTags() {
        return showAllTags();
    }

    /**
     * Method for handling finding tag by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> showTag(
            @PathVariable @Min(0) Long id) {
        return ResponseEntity.ok(
                new TagResponse(
                        tagService.getById(id)
                )
        );
    }

    /**
     * Method for inserting new tag
     */
    @PostMapping
    public ResponseEntity<TagListResponse> insertNewTag(
            @RequestBody @Valid TagDTO tag
            ) {
        tagService.insertNewObject(tag);
        return showAllTags();
    }

    /**
     * Method for deleting tag
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TagListResponse> deleteTag(
            @PathVariable Long id
    ) {
        tagService.deleteById(id);
        return showAllTags();
    }

    private ResponseEntity<TagListResponse> showAllTags() {
        return ResponseEntity.ok(
                new TagListResponse(
                        tagService.getAll()
                ));
    }
}
