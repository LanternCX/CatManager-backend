package xyz.cdbxinhe.cat.backend.util.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 * package xyz.cdbxinhe.cat.backend.util.common
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
public class JsonUtils {
    public static final String KEY_SPE = "()[],";
    public static final String KEY_EMPTY = " ";
    public static final String KEY_EMPTY_2 = "";

    public static final String SPE_FLAG1 = ",";
    public static final String SPE_FLAG2 = ")";
    public static final String SPE_FLAG3 = "]";
    public static final String NULL_FLAG = "null";
    public static final String EQUALS_FLAG = "=";

    /**
     * 从JSON数组中删除某一项
     *
     * @param array 数组
     * @param key   Key
     * @param value Value
     * @return 删除后的
     */
    public static JSONArray removeItem(JSONArray array, String key, Object value) {
        JSONArray newArray = new JSONArray();
        for (JSONObject obj : array.toArray(new JSONObject[0])) {
            if (obj.containsKey(key)) {
                if (value.equals(obj.get(key))) {
                    continue;
                }
            }
            newArray.add(obj);
        }
        return newArray;
    }


    /**
     * 获取key
     *
     * @param str 序列化字符串
     * @return Key
     */
    private static String before(String str) {
        int i = str.indexOf(EQUALS_FLAG);
        int j = i;
        if (i == -1) {
            return null;
        }
        while (true) {
            String tempStr;
            if (j - 1 >= 0) {
                tempStr = str.substring(j - 1, j);
            } else {
                break;
            }
            if (KEY_SPE.contains(tempStr) || KEY_EMPTY.equals(tempStr)) {
                break;
            }
            j--;
        }
        return str.substring(j, i);
    }

    /**
     * 获取Value
     *
     * @param str 序列化字符串
     * @return Value
     */
    private static String after(String str) {
        int i = str.indexOf(EQUALS_FLAG);
        int j = i + 1;
        int length = str.length();
        if (i == -1 || j >= length) {
            return null;
        }
        while (true) {
            String tempStr1;
            if (j + 1 < length) {
                tempStr1 = str.substring(j, j + 1);
            } else {
                break;
            }
            if (SPE_FLAG1.equals(tempStr1) || SPE_FLAG2.equals(tempStr1)) {
                String tempStr2;
                if (j + 2 < length) {
                    tempStr2 = str.substring(j + 1, j + 2);
                    if (KEY_EMPTY.equals(tempStr2) || SPE_FLAG3.equals(tempStr2) || SPE_FLAG1.equals(tempStr2)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            j++;
        }
        return str.substring(i + 1, j);
    }

    /**
     * 序列化字符串转JSON
     *
     * @param str 序列化字符串
     * @return 字符串
     */
    public static JSONObject strToJson(String str) {
        JSONObject result = new JSONObject();
        while (true) {
            String before = before(str);
            String after = after(str);
            if (before == null) {
                break;
            }
            if (NULL_FLAG.equals(after)) {
                result.put(before, null);
            } else {
                result.put(before, after);
            }
            str = str.replace(before + EQUALS_FLAG + after, KEY_EMPTY_2);
        }
        return result;
    }
}
