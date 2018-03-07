package com.tsoft.app.service.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.tsoft.app.domain.User;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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

            sendPushNotificationHttpV1Api(body);
        }
    }

    private ResponseEntity<String> sendPushNotification(JSONObject pushNotificationHttpV1ApiDto) throws Exception {
        LOGGER.debug("Start Send Push Notification  to  {} ", pushNotificationHttpV1ApiDto.getString("to"));
        String baseUri = "https://fcm.googleapis.com/v1/projects/ganeo-hta/messages:send";
        HttpEntity<String> httpEntity = new HttpEntity(pushNotificationHttpV1ApiDto.toString(), createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(baseUri, HttpMethod.POST, httpEntity, String.class);
        LOGGER.debug("Start Send Push Notification  to  {} with content  with response {}", pushNotificationHttpV1ApiDto.getString("to"),
                response.toString());
        return response;
    }

    public void sendPushNotificationHttpV1Api(JSONObject pushNotificationHttpV1ApiDto) throws Exception {
        LOGGER.debug("Start Send Push Notification  to  {} ", pushNotificationHttpV1ApiDto.getString("to"));

        // Construct the objects needed for the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/v1/projects/smoovengo-android/messages:send");

        String token = getAccessToken();
        // The message we are going to post
        httpPost.addHeader("Authorization", "Bearer " + token);
        httpPost.addHeader("Content-Type", "application/json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        StringEntity body = new StringEntity(mapper.writeValueAsString(pushNotificationHttpV1ApiDto), ContentType.APPLICATION_JSON);
        body.setContentType("application/json");
        httpPost.setEntity(body);

        // Make the request
        HttpResponse response = httpClient.execute(httpPost);
        LOGGER.debug("End PostRequest Firebase  with status {}  and content {}", response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        // release connection
        httpPost.releaseConnection();

    }

}
