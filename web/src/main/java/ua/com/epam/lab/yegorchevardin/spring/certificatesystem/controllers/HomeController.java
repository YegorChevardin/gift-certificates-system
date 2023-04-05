package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling server work and for API introduction
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> showAPIHome() {
        return ResponseEntity.ok("Servier is working perfectly");
    }
}
