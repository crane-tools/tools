package crane;

import crane.model.User;
import crane.orm.es.EsOrm;
import crane.tools.EmailHelper;
import crane.tools.EsHelper;
import crane.tools.I18nManager;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@IntegrationTest("server.port:8888")
@TestPropertySource(locations = {"classpath:application.properties", "classpath:application-dev.properties"})
@Profile("dev")
public class ApplicationTests {

    @Autowired
    EmailHelper helper;

    @Autowired
    EsHelper esHelper;

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
        helper.send("xxx@xxx.xxx", "sdfas", "dfasdf");
    }

    @Test
    public void addEs() throws IOException {
        User user = new User();
        user.setUserName("crane");
        user.setRealName("lxh");
        user.setGender(1l);
        user.setCreateDate(System.currentTimeMillis());
        Map<String, XContentBuilder> contentBuilderMap = EsOrm.buildEsData(user);
        esHelper.bulkInsert("crane", "user_info", contentBuilderMap);
    }
}
