package com.kafkaStudy.consumer;

import com.kafkaStudy.model.User;
import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * {@link User}的反序列化类
 *
 * @author chenwu on 2020.2.16
 */
public class UserDeserializer implements Deserializer {

    private ObjectMapper objectMapper;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            User user = objectMapper.readValue(data, User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {

    }
}
