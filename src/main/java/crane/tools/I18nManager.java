package crane.tools;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by crane on 16/7/21.
 */
public class I18nManager {
    private final String DEF_LANG_TAG = "zh-CN";
    private static String langTag;
    private ThreadLocal<Locale> currentThreadLocale = new ThreadLocal<Locale>();
    private static I18nManager instance = new I18nManager();
    private Map<String, ResourceBundle> rbMap = new HashMap<>();

    private I18nManager() {
    }

    public static I18nManager getInstance() {
        return instance;
    }

    static {
        //some static init
        langTag = System.getProperty("lang.tag", "zh-CN");
    }

    private Locale getLocaleByTag(String languageTag) {
        Locale locale = null;
        if (StringUtils.isBlank(languageTag)) {
            locale = Locale.forLanguageTag(this.langTag);
        } else {
            locale = Locale.forLanguageTag(languageTag);
        }
        if (locale == null) {
            locale = Locale.forLanguageTag(DEF_LANG_TAG);
        }
        return locale;
    }

    private ResourceBundle getResourceBundle() {
        Locale locale = this.getLocale();
        ResourceBundle rb;
        if (!rbMap.containsKey(locale.toLanguageTag())) {
            rb = ResourceBundle.getBundle("tool", locale, new UTF8Control());
            rbMap.put(locale.toLanguageTag(), rb);
        } else {
            rb = rbMap.get(locale.toLanguageTag());
        }
        return rb;
    }


    public Locale getLocale() {
        if (currentThreadLocale.get() == null) {
            currentThreadLocale.set(this.getLocaleByTag(this.DEF_LANG_TAG));
        }
        return currentThreadLocale.get();
    }

    public void setLocale(Locale locale) {
        currentThreadLocale.set(locale);
    }

    public void setLocaleByTag(String languageTag) {
        Locale locale = this.getLocaleByTag(languageTag);
        setLocale(locale);
    }

    public String getLang(String langKey) {
        try {
            return getResourceBundle().getString(langKey);
        } catch (MissingResourceException mre) {
        } catch (Exception e) {
        }
        return "@" + langKey + "@" + getLocale();
    }

    public String getFLang(String langKey, Object... args) {
        return MessageFormat.format(getLang(langKey), args);
    }

    class UTF8Control extends ResourceBundle.Control {
        public ResourceBundle newBundle
                (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {
            // The below is a copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    // Only this line is changed to make it to read properties files as UTF-8.
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}
