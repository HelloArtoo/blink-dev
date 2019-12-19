package com.gobue.blink.common.utils.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper(false);
    public static final ObjectMapper SNAKE_OBJECT_MAPPER = createObjectMapper(true);
    public static final ObjectMapper CACHE_MAPPER = createObjectMapper4Cache();

    private static ObjectMapper createObjectMapper4Cache() {
        ObjectMapper objectMapper = createObjectMapper(false);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }

    /**
     * 初始化ObjectMapper
     */
    private static ObjectMapper createObjectMapper(boolean snake) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME);

//        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeDeserializer);
//        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        javaTimeModule.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);

        if (snake) {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        return objectMapper;
    }


    /**
     * 将 json 字段串转换为 对象.
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("failed to convert json to object :" + json, e);
        }
    }


    public static <T> T map2Object(Map data, Class<T> cls) {
        return OBJECT_MAPPER.convertValue(data, cls);
    }

    public static String object2Json(Object o) {
        if (Objects.isNull(o)) {
            return "";
        }
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createGenerator(sw);
            OBJECT_MAPPER.writeValue(gen, o);
        } catch (IOException e) {
            throw new RuntimeException("failed to convert object to json ", e);
        } finally {
            if (null != gen) {
                try {
                    gen.close();
                } catch (IOException e) {
                    throw new RuntimeException("failed to convert object to json", e);
                }
            }
        }
        return sw.toString();
    }

    public static Map<String, Object> object2Map(Object o) {
        return OBJECT_MAPPER.convertValue(o, Map.class);
    }


    /**
     * 将 json 字段串转换为 List.
     */
    public static <T> List<T> json2List(String json, Class<T> clazz) throws IOException {
        JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);

        List<T> list = OBJECT_MAPPER.readValue(json, type);
        return list;
    }


    /**
     * 将 json 字段串转换为 数据.
     */
    public static <T> T[] json2Array(String json, Class<T[]> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, clazz);

    }

    public static <T> T node2Object(JsonNode jsonNode, Class<T> clazz) {
        try {
            T t = OBJECT_MAPPER.treeToValue(jsonNode, clazz);
            return t;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("failed to convert jsonNode to object :" + jsonNode.toString(), e);
        }
    }

    public static Map<String, Object> json2Map(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException("error convert json to map:" + json, e);
        }
    }

    public static JsonNode object2Node(Object o) {
        try {
            if (o == null) {
                return OBJECT_MAPPER.createObjectNode();
            } else {
                return OBJECT_MAPPER.convertValue(o, JsonNode.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to convert json to object ", e);
        }
    }


    static class CustomLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

        public CustomLocalDateTimeDeserializer(DateTimeFormatter formatter) {
            super(formatter);
        }

        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            try {
                if (NumberUtils.isDigits(parser.getText())) {
                    return new Timestamp(NumberUtils.createLong(parser.getText())).toLocalDateTime();
                } else {
                    String text = eraseText(parser, "+");

                    if (text.length() <= 20) {
                        if (text.contains("T")) {
                            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                        } else {
                            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        }
                    }
                }
            } catch (Exception e) {
                //ignore
            }
            return super.deserialize(parser, context);
        }


    }

    private static String eraseText(JsonParser parser, String sign) throws IOException {
        String text = parser.getText().trim();
        if (text.contains(".")) {
            text = StringUtils.substringBeforeLast(text, ".");
        }
        if (text.contains(sign)) {
            text = StringUtils.substringBeforeLast(text, sign);
        }
        return text;
    }

    static class CustomLocalDateDeserializer extends LocalDateDeserializer {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public CustomLocalDateDeserializer(DateTimeFormatter formatter) {
            super(formatter);
        }

        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            if (parser.hasToken(JsonToken.VALUE_STRING)) {
                String text = eraseText(parser, "T");
                if (text.length() == 10) {
                    return LocalDate.parse(text, formatter);
                }
            } else {
                try {
                    if (parser.getText().length() < 8) {
                        return null;
                    }
                    if (parser.getNumberType().equals(JsonParser.NumberType.LONG)) {
                        return new Timestamp(NumberUtils.createLong(parser.getText())).toLocalDateTime().toLocalDate();
                    }
                } catch (Exception e) {
                    //ignore
                }
            }
            return super.deserialize(parser, context);
        }
    }



}
