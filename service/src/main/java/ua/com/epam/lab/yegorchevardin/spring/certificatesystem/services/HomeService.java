package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services;

import java.util.Map;

/**
 * Service for handling logic from homepage
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public interface HomeService {
    /**
     * Method for setting up content and API information for home page
     * @return Map<String, String> collection with all the information
     */
    Map<String, String> showHomePageResponse(String currentUrl);
}
