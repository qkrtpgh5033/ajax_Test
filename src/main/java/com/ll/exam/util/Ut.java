package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ut {
    private static final ObjectMapper om = new ObjectMapper();

    public static Map mapOf(Object...objects){
        Map<String, Object> map = new LinkedHashMap<>();
        for(int i = 0; i < objects.length; i+=2){
            map.put((String) objects[i], objects[i + 1]);
        }

        return map;

    }
    public static class json {
        public static String toJson(Object obj, String defaultValue) {
            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }


        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {
            try {
                return (T) om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }


    }
}