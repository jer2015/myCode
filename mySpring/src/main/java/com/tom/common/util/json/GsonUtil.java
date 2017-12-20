package com.tom.common.util.json;

import com.google.gson.Gson;

/**
 * @author lxl
 */
public class GsonUtil {
    private static Gson gson;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 对象转json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String bean2json(Object object) {
        return gson.toJson(object);
    }

    /**
     * json转对象
     *
     * @param json json字符串
     * @param cls  对象Class
     * @param <T>  目标对象
     * @return 对象
     */
    public static <T> T json2Bean(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

}
