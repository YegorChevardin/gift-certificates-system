package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.impl;

import org.springframework.stereotype.Service;
import ua.com.epam.lab.yegorchevardin.spring.certificatesystem.services.HomeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {
    private static final String GIFT_CERTIFICATE_ACCESS_POINT = "api/v1/gift-certificates";
    private static final String TAGS_ACCESS_POINT = "api/v1/tags";

    @Override
    public Map<String, String> showHomePageResponse(String url) {
        Map<String, String> responseEntity = new HashMap<>();
        responseEntity.put("Tags access point", url + TAGS_ACCESS_POINT);
        responseEntity.put("GiftCertificates access point", url + GIFT_CERTIFICATE_ACCESS_POINT);
        return responseEntity;
    }
}
