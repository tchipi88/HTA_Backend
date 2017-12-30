package com.tsoft.app.service.notification;

import com.tsoft.app.config.properties.ApplicationProperties;
import com.tsoft.app.domain.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author tchipnangngansopa
 */
@Service
public class TwilioService {

    @Resource
    ApplicationProperties applicationProperties;

    @Async
    public String sendSMS(User user, String sms) {
        Twilio.init(applicationProperties.getTwilio().getAccountSid(), applicationProperties.getTwilio().getAuthToken()
        );
        Message message = Message
                .creator(new PhoneNumber(user.getTelephone()), new PhoneNumber(applicationProperties.getTwilio().getTelephoneFrom()), sms)
                .create();
        return message.toString();
    }
}
