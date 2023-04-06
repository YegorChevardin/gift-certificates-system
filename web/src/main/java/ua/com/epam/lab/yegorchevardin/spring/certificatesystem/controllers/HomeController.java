package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.HomeService;

import java.util.Map;

/**
 * Controller for handling server work and for API introduction
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    /**
     * Method for handling response for API homepage
     * @param request HttpServletRequest object for getting current url
     * @return Map<String, String> object with access points information
     */
    @GetMapping("/api/v1")
    public ResponseEntity<Map<String, String>> showAPIHome(HttpServletRequest request) {
        return ResponseEntity.ok(
                homeService.showHomePageResponse(request.getRequestURI())
        );
    }

    /**
     * Method for handling response with server working status
     */
    @GetMapping("/")
    public ResponseEntity<?> showServerStatus() {
        return ResponseEntity.ok().build();
    }
}
