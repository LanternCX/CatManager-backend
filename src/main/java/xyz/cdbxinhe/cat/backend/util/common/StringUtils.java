package xyz.cdbxinhe.cat.backend.util.common;

import com.qcloud.cos.utils.UrlEncoderUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * package xyz.cdbxinhe.cat.backend.util.common
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
public class StringUtils {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String QUE_FLAG = "\\?";
    public static final String AND_FLAG = "&";
    public static final String QUE_FLAG_2 = "?";
    public static final String EQUALS_FLAG = "=";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[^0-9]+");

    public static String[] stringToNumber(String input) {
        return NUMBER_PATTERN.split(input);
    }

    /**
     * 获取字符串的MD5摘要
     *
     * @param input 目标字符串
     * @return MD5摘要
     */
    public static String stringToMd5(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            char[] charArray = input.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString().toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取目标字符串的SHA1摘要
     *
     * @param input 目标字符串
     * @return SHA1摘要
     */
    public static String stringToSha1(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        MessageDigest sha1;
        try {
            sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(input.getBytes(StandardCharsets.UTF_8));

            byte[] byteArray = sha1.digest();
            char[] charArray = new char[byteArray.length * 2];
            int index = 0;
            for (byte byteThis : byteArray) {
                charArray[index++] = HEX_DIGITS[byteThis >>> 4 & 0xf];
                charArray[index++] = HEX_DIGITS[byteThis & 0xf];
            }
            return new String(charArray);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * URL安全化
     *
     * @param url 待处理URL
     * @return 处理后URL
     */
    public static String encodeUrl(String url) {
        StringBuilder before = new StringBuilder(url.split(QUE_FLAG)[0] + QUE_FLAG_2);
        for (String args : url.split(QUE_FLAG)[1].split(AND_FLAG)) {
            String key = args.split(EQUALS_FLAG)[0];
            String value = args.split(EQUALS_FLAG).length == 2 ? UrlEncoderUtils.encode(args.split(EQUALS_FLAG)[1]) : "";
            before.append(key).append(EQUALS_FLAG).append(value).append(AND_FLAG);
        }
        return before.substring(0, before.length() - 1);
    }
}