package com.lechinepay.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.lechinepay.framework.exception.Codes;
import com.lechinepay.framework.exception.UnCheckedException;

public class Convertor {

    public static <T> T convert(Map<String, String> responseMap, Class<T> responseClass) {
        if (null == responseMap || null == responseClass) {
            return null;
        }
        T responseObj = null;
        try {
            responseObj = responseClass.newInstance();
            Object value;
            for (Class<?> superClass = responseObj.getClass(); null != superClass
                    && superClass != Object.class; superClass = superClass.getSuperclass()) {
                for (Field field : superClass.getDeclaredFields()) {
                    if (Modifier.isStatic(field.getModifiers()) || !Modifier.isPrivate(field.getModifiers())
                            || field.getType() != String.class) {
                        continue;
                    }
                    value = responseMap.get(field.getName());
                    if (null == value) {
                        continue;
                    }
                    if (field.isAccessible()) {
                        field.set(responseObj, value);
                    } else {
                        field.setAccessible(true);
                        field.set(responseObj, value);
                        field.setAccessible(false);
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        }
        return responseObj;
    }

    public static <T> Map<String, String> convert(T request) {
        Map<String, String> data = new HashMap<String, String>();
        if (null == request) {
            return data;
        }
        try {
            Object value;
            for (Class<?> superClass = request.getClass(); null != superClass
                    && superClass != Object.class; superClass = superClass.getSuperclass()) {
                for (Field field : superClass.getDeclaredFields()) {
                    if (Modifier.isStatic(field.getModifiers()) || !Modifier.isPrivate(field.getModifiers())
                            || field.getType() != String.class) {
                        continue;
                    }
                    if (field.isAccessible()) {
                        value = field.get(request);
                    } else {
                        field.setAccessible(true);
                        value = field.get(request);
                        field.setAccessible(false);
                    }
                    if (null == value) {
                        continue;
                    }
                    data.put(field.getName(), value.toString());
                }
            }
        } catch (IllegalArgumentException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        }
        return data;
    }

    public static <T> T convertObject(Map<String, Object> responseMap, Class<T> responseClass) {
        if (null == responseMap || null == responseClass) {
            return null;
        }
        T responseObj = null;
        try {
            responseObj = responseClass.newInstance();
            Object value;
            for (Class<?> superClass = responseObj.getClass(); null != superClass
                    && superClass != Object.class; superClass = superClass.getSuperclass()) {
                for (Field field : superClass.getDeclaredFields()) {
                    if (Modifier.isStatic(field.getModifiers()) || !Modifier.isPrivate(field.getModifiers())) {
                        continue;
                    }
                    value = responseMap.get(field.getName());
                    if (null == value) {
                        continue;
                    }
                    if (field.isAccessible()) {
                        field.set(responseObj, value);
                    } else {
                        field.setAccessible(true);
                        field.set(responseObj, value);
                        field.setAccessible(false);
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        }
        return responseObj;
    }

    public static <T> Map<String, Object> convertObject(T request) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (null == request) {
            return data;
        }
        try {
            Object value;
            for (Class<?> superClass = request.getClass(); null != superClass
                    && superClass != Object.class; superClass = superClass.getSuperclass()) {
                for (Field field : superClass.getDeclaredFields()) {
                    if (Modifier.isStatic(field.getModifiers()) || !Modifier.isPrivate(field.getModifiers())) {
                        continue;
                    }
                    if (field.isAccessible()) {
                        value = field.get(request);
                    } else {
                        field.setAccessible(true);
                        value = field.get(request);
                        field.setAccessible(false);
                    }
                    if (null == value) {
                        continue;
                    }
                    data.put(field.getName(), value);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new UnCheckedException(Codes.SYS_ERROR, e.getMessage(), e);
        }
        return data;
    }
}
