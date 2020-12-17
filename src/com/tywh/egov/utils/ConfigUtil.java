package com.tywh.egov.utils;

import java.util.ResourceBundle;

public class ConfigUtil {

    private ConfigUtil() {

    }
    public static String getConfigValue(String key, String fileName) {
        return ResourceBundle.getBundle(fileName).getString(key);
    }

    public static boolean isNotEmpty(String str) {

        return str != "" && str.trim().length() > 0;
    }
}
