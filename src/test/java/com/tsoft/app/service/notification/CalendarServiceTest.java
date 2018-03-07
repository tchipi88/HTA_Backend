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
public class CalendarServiceTest {

    @Autowired
    CalendarService calendarService;

    private User user;

    @Before
    public void init() {
        user = new User();
        user.setEmail("ngansop.arthur@gmail.com");
    }

    @Test
    public void createEvent() throws Exception {
        // calendarService.createEvent(user);
    }

}
