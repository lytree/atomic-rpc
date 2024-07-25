package top.lytree.serialize.impl;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.RuntimeVersion;
import top.lytree.serialize.Serializer;
import top.lytree.serialize.algorithm.SerializerAlgorithm;

public class ProtobufSerializer   implements Serializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.Proto;
    }

    @Override
    public byte[] serialize(Object object) {
        if (object instanceof GeneratedMessage message){
            return message.toByteArray();
        }
        return null;
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return null;
    }
}
