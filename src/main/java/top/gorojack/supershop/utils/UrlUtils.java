package top.gorojack.supershop.utils;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {

    public static Map<String, String> parseUrl(String url) {
        String[] split = url.split("&");
        Map<String, String> map = new HashMap<>();
        for (String s : split) {
            String[] param = s.split("=");
            try {
                map.put(param[0], param[1]);
            } catch (Exception ignored) {
            }
        }
        return map;
    }
}
