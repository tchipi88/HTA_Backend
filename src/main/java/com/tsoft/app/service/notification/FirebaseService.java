package com.tsoft.app.service.notification;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.tsoft.app.domain.User;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author tchipnangngansopa
 */
@Service
public class FirebaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseService.class);

    private static final String[] SCOPES = { "https://www.googleapis.com/auth/firebase.messaging" };

    @Autowired
    private ResourceLoader resourceLoader;

    private final RestTemplate restTemplate;

    public FirebaseService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    private String getAccessToken() throws IOException {
        Resource googleJson = resourceLoader.getResource("classpath:firebase/google-services.json");
        GoogleCredential googleCredential = GoogleCredential.fromStream(googleJson.getInputStream()).createScoped(Arrays.asList(SCOPES));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    HttpHeaders createHeaders() throws IOException {
        return new HttpHeaders() {

            private static final long serialVersionUID = 3109256773218160485L;

            {
                set("Authorization", "Bearer " + getAccessToken());
                set("Content-Type", "application/json");
            }
        };
    }

    // @Async
    public void sendPushNotification(User user, String message) throws Exception {
        if (!StringUtils.isBlank(user.getFirebaseToken())) {
            JSONObject body = new JSONObject();
            body.put("to", user.getFirebaseToken());
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "HTA Notifications");
            notification.put("body", message);

            body.put("notification", notification);

            sendPushNotification(body);
        }
    }

    private ResponseEntity<String> sendPushNotification(JSONObject pushNotificationHttpV1ApiDto) throws Exception {
        LOGGER.debug("Start Send Push Notification  to  {} ", pushNotificationHttpV1ApiDto.getString("to"));
        String baseUri = "https://fcm.googleapis.com/v1/projects/ganeo-hta/messages:send";
        HttpEntity httpEntity = new HttpEntity(pushNotificationHttpV1ApiDto.toString(), createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(baseUri, HttpMethod.POST, httpEntity, String.class);
        LOGGER.debug("Start Send Push Notification  to  {} with content  with response {}", pushNotificationHttpV1ApiDto.getString("to"),
                response.toString());
        return response;
    }
}
