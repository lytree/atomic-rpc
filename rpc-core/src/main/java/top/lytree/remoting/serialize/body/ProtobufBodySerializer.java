package top.lytree.remoting.serialize.body;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import top.lytree.remoting.serialize.BodySerializer;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

public class ProtobufBodySerializer implements BodySerializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.Proto;
    }

    @Override
    public byte[] serialize(Object object) {
        Schema<Object> schema = RuntimeSchema.getSchema(Object.class);
        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        Schema<T> schema = RuntimeSchema.getSchema(clazz);

        T message = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, message, schema);
        return message;
    }
}
