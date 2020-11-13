package com.tywh.egov.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    日期格式化工具类
 */
public class DateUtil {

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String result = sdf.format(date);
        return result;
    }
}
