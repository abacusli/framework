package com.lechinepay.framework.web.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.util.Convertor;

public class DataBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBuilder.class);

    public static Map<String, Object> buildObjectMap(HttpServletRequest req) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (null == req) {
            return dataMap;
        }
        Enumeration<?> enumeration = req.getParameterNames();
        String name;
        while (enumeration.hasMoreElements()) {
            name = (String) enumeration.nextElement();
            dataMap.put(name, req.getParameter(name));

        }
        LOGGER.debug("request data:" + dataMap);
        return dataMap;
    }

    public static Map<String, String> buildDataMap(HttpServletRequest req) {
        Map<String, String> dataMap = new HashMap<String, String>();
        if (null == req) {
            return dataMap;
        }
        Enumeration<?> enumeration = req.getParameterNames();
        String name;
        while (enumeration.hasMoreElements()) {
            name = (String) enumeration.nextElement();
            dataMap.put(name, req.getParameter(name));

        }
        LOGGER.debug("request data:" + dataMap);
        return dataMap;
    }

    public static <T> T buildData(Map<String, String> dataMap, Class<T> dataClass) {
        if (null == dataMap || null == dataClass) {
            return null;
        }
        return Convertor.convert(dataMap, dataClass);
    }

    public static <T> T buildData(HttpServletRequest req, Class<T> dataClass) {
        if (null == req || null == dataClass) {
            return null;
        }
        Map<String, String> dataMap = DataBuilder.buildDataMap(req);
        return Convertor.convert(dataMap, dataClass);
    }

    public static Map<String, Object> buildDataMap(Map<String, String[]> parameterMap) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String key;
        String[] value;
        for (Entry<String, String[]> entry : parameterMap.entrySet()) {
            key = entry.getKey();
            if (null == key) {
                continue;
            }
            value = entry.getValue();
            if (null == value || value.length == 0) {
                dataMap.put(key, null);
            } else if (value.length == 1) {
                dataMap.put(key, value[0]);
            } else {
                dataMap.put(key, value);
            }
        }
        return dataMap;
    }

}
