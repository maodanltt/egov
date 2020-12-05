package com.tywh.egov.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class WebUtil{

    public static void setFieldsValue(HttpServletRequest request, Object obj) throws Exception{

        Class c = obj.getClass();
        Field[] fields =  c.getDeclaredFields();
        for(Field field : fields) {
            String param = request.getParameter(field.getName());
            String setMethod = "set" + field.getName().toUpperCase().charAt(0) + field.getName().substring(1);
            c.getDeclaredMethod(setMethod, String.class).invoke(c,param);
        }
    }
}
