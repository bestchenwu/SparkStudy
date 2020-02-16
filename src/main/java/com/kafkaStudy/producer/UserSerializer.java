package com.kafkaStudy.producer;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;
import com.kafkaStudy.model.User;
import java.util.Map;

/**
 * {@link User}的序列化类
 *
 * @author chenwu on 2020.2.2
 */
public class UserSerializer implements Serializer {

    private ObjectMapper objectMapper;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] bytes = null;
        try{
            bytes = objectMapper.writeValueAsString(data).getBytes();
            return bytes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {

    }
}
