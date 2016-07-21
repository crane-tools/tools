package crane;

import crane.tools.I18nManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

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

}
