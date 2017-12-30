package com.tsoft.app.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to JHipster.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String defaultPassword;

    private final Twilio twilio = new Twilio();

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public Twilio getTwilio() {
        return twilio;
    }

    public static class Twilio {

        private String accountSid;

        private String authToken;

        private String serviceSid;

        private String telephoneFrom;

        public String getTelephoneFrom() {
            return telephoneFrom;
        }

        public void setTelephoneFrom(String telephoneFrom) {
            this.telephoneFrom = telephoneFrom;
        }

        public String getAccountSid() {
            return accountSid;
        }

        public void setAccountSid(String accountSid) {
            this.accountSid = accountSid;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public String getServiceSid() {
            return serviceSid;
        }

        public void setServiceSid(String serviceSid) {
            this.serviceSid = serviceSid;
        }

    }

}
