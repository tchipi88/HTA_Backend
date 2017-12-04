//package com.tsoft.app.service.firebase;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Arrays;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// *
// * @author tchipnangngansopa
// */
//@Service
//public class FirebaseService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseService.class);
//
//    private static final String[] SCOPES = {"https://www.googleapis.com/auth/firebase.messaging"};
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    private final RestTemplate restTemplate;
//
//    public FirebaseService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    private String getAccessToken() throws IOException {
//        Resource googleJson = resourceLoader.getResource("classpath:firebase/google-services.json");
//        GoogleCredential googleCredential = GoogleCredential.fromStream(new FileInputStream(googleJson.getFilename())).createScoped(Arrays.asList(SCOPES));
//        googleCredential.refreshToken();
//        return googleCredential.getAccessToken();
//    }
//
//    HttpHeaders createHeaders() throws IOException {
//        return new HttpHeaders() {
//
//            private static final long serialVersionUID = 3109256773218160485L;
//
//            {
//                set("Authorization", "Bearer " + getAccessToken());
//                set("Content-Type", "application/json");
//            }
//        };
//    }
//
//    public ResponseEntity<JSONObject> sendPushNotification(JSONObject body) throws IOException {
//        LOGGER.debug("Start Send Push Notification wiht content {}", body.toString());
//        String baseUri = "https://fcm.googleapis.com/v1/projects/ganeo-hta/messages:send";
//        ResponseEntity<JSONObject> response = restTemplate.exchange(baseUri, HttpMethod.POST, new HttpEntity(body, createHeaders()),
//                JSONObject.class);
//        LOGGER.debug("End Send Push Notification wiht respone {}", response.toString());
//        return response;
//    }
//
//}
