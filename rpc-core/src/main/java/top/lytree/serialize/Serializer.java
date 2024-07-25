package top.lytree.serialize;

import top.lytree.serialize.algorithm.SerializerAlgorithm;

import java.io.IOException;

public interface Serializer {

    SerializerAlgorithm getSerializerAlgorithm();

    /**
     * 编码
     * @param obj
     * @return
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 解码
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
