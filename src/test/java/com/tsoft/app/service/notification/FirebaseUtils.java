//package com.tsoft.app.service.notification;
//
//import com.fasterxml.jackson.annotation.JsonRootName;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.util.Arrays;
//import net.minidev.json.JSONValue;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Utils class that allow to push Notification to Android ,IOS devises using
// * Firebase Cloud Messaging
// *
// * @author tchipnangngansopa
// */
//public final class FirebaseUtils {
//
//    /**
//     * No need to create instance.
//     */
//    private FirebaseUtils() {
//    }
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseUtils.class);
//
//    private static final String[] SCOPES = {"https://www.googleapis.com/auth/firebase.messaging"};
//
//    private static String getAccessToken() throws IOException, JSONException {
//
//        JSONObject credentialObject = new JSONObject();
//        credentialObject.put("type", "service_account");
//        credentialObject.put("project_id", "smoovengo-android");
//        credentialObject.put("private_key_id", "3af01369b04b296f664755cb8792420144f2bee7");
//        credentialObject.put("private_key",
//                "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxRkxTk+0wbjGL\n9HPjYS3uQB8C5BhjBNxMr8G+YgNa1ETQRkgqQWBiuwGxquvhZNbDJjh9ode0Q7qK\n/W2G6VuOSYX3hFVaeuvm7yn3nnRVR3cyQvBpMTLlVXIjWjGM0hCQmYLyAqiuGYpF\nL1HVi+5celzjzp1S9sXvDKsLCnzmVyImL/cjVXbN6wxmogWnoSPvEkO+5WCIAZ9H\n400sKuzbgfNx2arGUJQtIt7nB+IQM7O1VkU19fsvwGOmeTZ2d41pQbGTrKCBBaY6\n800afFPl8t9Rsbw31ceyfFEVS83alJDFv6ZtTcdhGHJLjTTi6qZd4P9bnaXS3K4A\n7MDKY+ArAgMBAAECggEAUQ6Bq92BoJliEkpOCvPOWpf7FglRlUUNrgrXHcQXIJra\ndBkOujN2bO5xEdnDTSYchNKfFeLAjG5Dh3XldMCS2naHyOs7U8nMv6UvnUdYjf3F\nZLfy0UZrrSzqqXt+GJOce2GV3C6XDjQjYwsF8gyvip1wh8FbcinfyzQRqVS8U11U\n5NvnvXvpgaFqNgqTODvj/cNC4RuSBZz1/A+KlKLjzn4ck7yIaZlnte6Jgf7r+TKv\nGlIyVQz1ROnM41rf5N9aUY5Ucj7iUXTbtOjj+Qk2wEnCCqjS+sLNnmPlzRG7Y0wi\n4WDQFApBr+4AJGG6w/1vKiCejwM0nA24tWf9/n2qoQKBgQDhKfatNSIPm7qebdLq\nVD0pjNsyoz2zzL3aQb/KUdNCkD0aQqBQ29gpA2EaJJGFmvkCrPHZyBrpTczH+jH9\nCFGViFwy12LY4l2zXDJYNAQB3TJYEgGcfB4omwbUsIHdqNRZOCh1qoWScRT7jLQO\nL7huN/Z0eNBfpmGZKzgz24JuYwKBgQDJjWE/dkKECoqt6ZiBj8e5cU729pdu8DS4\nY4A252rzmS6TfemthEfH04nm7Vg4v0HmNofQ+lN47fiU8sKXhnN6OFZUyM0Ho2D7\nAUvtR9Dp0Euj1IRaRgfR7fGqYYvlHSHPlreXGVHnyuNki6068wcitXmQVuuoA8MO\n7jNeP/2tmQKBgQCJOzE8GeEbkDdqbdcZuKPaWh4Su8lUd1nS1zXUUpCmxhBnm+nn\nvkX9oTCiZV1IB87yoYN5z2sPfLptu4CSDmri70v6NKnrM/QV6ABLH9C9bhpL8RWI\nUWF0MCA2qylLdqWNhxups3dbbI50TipDZzRKANO14xhk45wiZWQIvyW8rwKBgB9o\ntd/y6Pi0v9nMTmTaaDCA9OJNmVnDeyPE9cUYmTUvCPAZLiJJO/pq/9GJ6Tamr3lV\nxCKP8BQnWZa0ohiE7lf8kY+S30iycDmNgSyQBYF6YV89jgaXZT6UyxVXMDrbKFq2\nTRPx8S6jEn6nua43E0SYbUJOGVlMhMg93KlyZXD5AoGAWZ7kM6FHYRoVim2+ubNY\noEqhNMuSjkvL1qHuA986raiWikC4QaJRVzsXmWTpt/bEL7j+xkfCV0EZihIlszi0\nuxFFKN845JdiCxX70GAEh5HSNOPsWHG136trLrTDXWo91YsY2nAUSftjDrxNXGWa\nDk8qDxTKVGlNwOiaMstT+Vw=\n-----END PRIVATE KEY-----\n");
//        credentialObject.put("client_email", "firebase-adminsdk-k491n@smoovengo-android.iam.gserviceaccount.com");
//        credentialObject.put("client_id", "117537438543579008020");
//        credentialObject.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
//        credentialObject.put("token_uri", "https://accounts.google.com/o/oauth2/token");
//        credentialObject.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
//        credentialObject.put("client_x509_cert_url",
//                "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-k491n%40smoovengo-android.iam.gserviceaccount.com");
//
//        InputStream credentialStream = new ByteArrayInputStream(credentialObject.toJSONString().getBytes());
//        GoogleCredential googleCredential = GoogleCredential.fromStream(credentialStream).createScoped(Arrays.asList(SCOPES));
//        googleCredential.refreshToken();
//        return googleCredential.getAccessToken();
//    }
//
//    public static void sendPushNotificationHttpV1Api(PushNotificationHttpV1ApiDto pushNotificationHttpV1ApiDto) {
//        LOGGER.debug("Start PostRequest Firebase  to  {} with content {}", pushNotificationHttpV1ApiDto.getToken(),
//                pushNotificationHttpV1ApiDto.getNotification().getBody());
//
//        // Construct the objects needed for the request
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/v1/projects/smoovengo-android/messages:send");
//
//        String token = getAccessToken();
//        // The message we are going to post
//        httpPost.addHeader("Authorization", "Bearer " + token);
//        httpPost.addHeader("Content-Type", "application/json");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//        StringEntity body = new StringEntity(mapper.writeValueAsString(pushNotificationHttpV1ApiDto), ContentType.APPLICATION_JSON);
//        body.setContentType("application/json");
//        httpPost.setEntity(body);
//
//        // Make the request
//        HttpResponse response = httpClient.execute(httpPost);
//
//        int statusCode = response.getStatusLine().getStatusCode();
//
//        LOGGER.debug("End PostRequest Firebase  with status {}", statusCode);
//        // release connection
//        httpPost.releaseConnection();
//
//    }
//
//    public static void sendPushNotification() {
//        try {
//            // Construct the objects needed for the request
//            HttpClient httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/fcm/send");
//
//            // The message we are going to post
//            httpPost.addHeader("Authorization",
//                    "key=AAAAXD8kfyI:APA91bGdbBT4H_s4xL-tiwHezmCQZbdtspaL5sftcYxivD958V0dkuG-iH0Adr31zlwLBxprPhuyb-hv9vnd4tNxsG2FPRp5_hWMJof9TNWAlh603wAubMg0YF7f8HdSeT43_rYk92O1");
//            httpPost.addHeader("Content-Type", "application/json");
//
//            JSONObject message = new JSONObject();
//            message.put("to",
//                    "dlAK4SqsQ2s:APA91bG_No35sS3s_AnIpTv2Xrpfyb4b-fIsTKhTRWuk-AyFs6BzCCYve-SxJJM84uunCLqw_ojlJ9EMoyLJT6VEcSG3HFxR61kXGIe-FhlZPw01pcSrjkauGOsay0iK6k2BT51QLLvx");
//            message.put("priority", "high");
//
//            JSONObject notification = new JSONObject();
//            notification.put("title", "Java");
//            notification.put("body", "Notificação do Java");
//
//            message.put("notification", notification);
//
//            httpPost.setEntity(new StringEntity(message.toString(), "UTF-8"));
//
//            // Make the request
//            HttpResponse response = httpClient.execute(httpPost);
//
//            int statusCode = response.getStatusLine().getStatusCode();
//            String response_string = EntityUtils.toString(response.getEntity());
//            JSONObject finalResult = (JSONObject) JSONValue.parse(response_string);
//            System.out.println(finalResult);
//            LOGGER.debug("End PostRequest Firebase  with status {}", statusCode);
//            // release connection
//            httpPost.releaseConnection();
//        } catch (IOException ex) {
//            LOGGER.error("Pb lors de l'appel vers le service Firebase  pour la notif {}  au devise {} ", ex);
//        }
//    }
//
//    public static void main(String[] args) throws JsonProcessingException, IOException {
//        // System.out.println(createSalesforceAccount().toString());
//        // sendPushNotification();
//        PushNotificationHttpV1ApiDto pushNotificationDto = new PushNotificationHttpV1ApiDto();
//        // pushNotificationDto.setToken("csYU86x2gLY:APA91bEYHrZUFmqP6hI8abykSOqQc0u0fOFdezt7-0M9FbqP8g6WPil8n3A6tOc3qCNgDPs7DymyQ1hh-51oGOtL2vUBnpjY8ZxdgY7OfSC1wzNyeHSdLafNDr5HR6yeLUk4Pr1U-Qv8");
//        pushNotificationDto.setToken(
//                "cBgraWvexXM:APA91bGyEiAhSvZKTJhkqF3ROvXEfWnFXeqFkJMDeQZPkmSOyHF3KxByE5eYigBMlXsmUt0VZuX8gxgHfJaQL4lrT-9Ft8IK1pw0HWm_JBQlo4JCzKCN1TYCR6sT_cmE0UtPBArFIGd2");
//        pushNotificationDto.setToken(
//                "cBgraWvexXM:APA91bGyEiAhSvZKTJhkqF3ROvXEfWnFXeqFkJMDeQZPkmSOyHF3KxByE5eYigBMlXsmUt0VZuX8gxgHfJaQL4lrT-9Ft8IK1pw0HWm_JBQlo4JCzKCN1TYCR6sT_cmE0UtPBArFIGd2");
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setTitle("Test");
//        notificationDto.setBody("FCM Message");
//        pushNotificationDto.setNotification(notificationDto);
//        System.out.println(sendPushNotificationHttpV1Api(pushNotificationDto).toJSONString());
//    }
//
//    @JsonRootName(value = "message")
//    private static class PushNotificationHttpV1ApiDto implements Serializable {
//
//        public PushNotificationHttpV1ApiDto() {
//        }
//
//        private static final long serialVersionUID = -3116267211068662090L;
//
//        public String token;
//
//        public NotificationDto notification;
//
//        public AndroidConfig android;
//
//        public ApnsConfig apns;
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//
//        public NotificationDto getNotification() {
//            return notification;
//        }
//
//        public void setNotification(NotificationDto notification) {
//            this.notification = notification;
//        }
//
//        public AndroidConfig getAndroid() {
//            return android;
//        }
//
//        public void setAndroid(AndroidConfig android) {
//            this.android = android;
//        }
//
//        public ApnsConfig getApns() {
//            return apns;
//        }
//
//        public void setApns(ApnsConfig apns) {
//            this.apns = apns;
//        }
//
//    }
//
//    private static class NotificationDto implements Serializable {
//
//        public NotificationDto() {
//        }
//
//        private String title;
//
//        private String body;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getBody() {
//            return body;
//        }
//
//        public void setBody(String body) {
//            this.body = body;
//        }
//
//    }
//
//    private static class AndroidConfig implements Serializable {
//
//        public AndroidConfig() {
//        }
//    }
//
//    private class ApnsConfig implements Serializable {
//
//        public ApnsConfig() {
//        }
//    }
//
//}
