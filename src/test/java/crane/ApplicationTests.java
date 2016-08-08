package crane;

import crane.tools.EmailHelper;
import crane.tools.I18nManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    EmailHelper helper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLang() {
        System.out.println();
        System.out.println(I18nManager.getInstance().getLang("key1"));
        I18nManager.getInstance().setLocaleByTag("en-US");
        System.out.println(I18nManager.getInstance().getFLang("key2", "param1", "param2"));
    }

    @Test
    public void testMail() throws MessagingException {
        helper.send("xxx@xxx.xxx","sdfas","dfasdf");
    }

}
