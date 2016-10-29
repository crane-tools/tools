package crane.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crane on 16/9/23.
 */
public class ReflectionUtils {

    /**
     * 从map通过反射获取实例
     *
     * @param clazz 实例类型
     * @param args  map中的private字段及值
     * @param <T>   类型参数
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getInstance(Class<T> clazz, Map<String, Object> args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = args.get(fields[i].getName());
            if (value != null) {
                fields[i].set(t, value);
            }
        }
        return t;
    }

    /**
     * 从对象获取map属性值映射
     *
     * @param object
     * @return
     */
    public static Map<String, Object> getMap(Object object) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            result.put(fields[i].getName(), fields[i].get(object));
        }
        return result;
    }

//    public static void main(String[] args) {
//        try {
//            User user = getInstance(User.class, new HashMap<String, Object>() {{
//                put("id", "1234");
//                put("nickName", "crane");
//                put("gender", 1l);
//                put("dfas", 1l);
//            }});
//            System.out.println(JSONObject.toJSONString(user));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//    }
}
