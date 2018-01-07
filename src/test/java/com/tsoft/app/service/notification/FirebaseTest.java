package com.tsoft.app.service.notification;

import com.tsoft.app.App;
import com.tsoft.app.domain.User;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author tchipnangngansopa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class FirebaseTest {

    @Autowired
    FirebaseService firebaseService;

    private User user;

    @Before
    public void init() {
        user = new User();
        user.setFirebaseToken("dwhKCzFh8Do:APA91bE_BDCT3hOIdgqMGJnRj7vWe9Ca-llzD-5McDltamwFOnXz3HVLMGHaulF2V9px2JkpIjErOYpMvVDfirr4DOsMLWsXMcEqcraowYMrboDDjryIjYnlXV3KALUPime7af0VXdbK");
    }

    @Test
    public void sendPushNotificationWithToken() throws Exception {
        //firebaseService.sendPushNotification(user, "Message FCM GODD");
    }

}
