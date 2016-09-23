package crane.utils;

import org.apache.commons.lang.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by crane on 16/9/23.
 */
public class ObjectParser {
    /**
     * object类型转化为int
     *
     * @param object       要转化为int的对象
     * @param defaultValue int默认值
     * @return 转化结果
     */
    public static Integer getInteger(Object object, Integer defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        String value = object.toString().trim();
        return Integer.parseInt(value);
    }

    public static Integer getInteger(Object object) {
        return getInteger(object, null);
    }

    /**
     * object类型转化为string
     *
     * @param object       要转化string的对象
     * @param defaultValue string默认值
     * @return 转化结果
     */
    public static String getString(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        return object.toString();
    }

    public static String getString(Object object) {
        return getString(object, null);
    }

    /**
     * object类型转化为double
     *
     * @param object       要转化为double的对象
     * @param defaultValue double默认值
     * @return 转化结果
     */
    public static Double getDouble(Object object, Double defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        String value = object.toString().trim();
        return Double.parseDouble(value);
    }

    public static Double getDouble(Object object) {
        return getDouble(object, null);
    }

    /**
     * object类型转化为long
     *
     * @param object       要转化为long的对象
     * @param defaultValue long默认值
     * @return 转化结果
     */
    public static Long getLong(Object object, Long defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        String value = object.toString().trim();
        return Long.parseLong(value);
    }

    public static Long getLong(Object object) {
        return getLong(object, null);
    }

    /**
     * object类型转化为时间戳
     *
     * @param object       要转化为时间戳的对象 long Date
     * @param defaultValue 时间戳默认值
     * @return 转化结果
     */
    public static Timestamp getTimestamp(Object object, Timestamp defaultValue) {
        if (object == null) {
            return defaultValue;
        }

        if (object instanceof java.sql.Date) {
            return new Timestamp(((java.sql.Date) object).getTime());
        }
        if (object instanceof java.util.Date) {
            return new Timestamp(((java.util.Date) object).getTime());
        }

        if (object instanceof Long) {
            return new Timestamp(Long.parseLong(object.toString()));
        }
        return defaultValue;
    }

    public static Timestamp getTimestamp(Object object) {
        return getTimestamp(object, null);
    }
}
