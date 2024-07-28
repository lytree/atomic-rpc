package top.lytree.remoting.serialize.message;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.serialize.MessageSerializer;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

import java.io.IOException;

public class ProtobufBodySerializer implements MessageSerializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.Proto;
    }

    @Override
    public byte[] serialize(RPCRequest object) throws IOException {

        Schema<RPCRequest> schema = RuntimeSchema.getSchema(RPCRequest.class);
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

    public RPCRequest deserialize(byte[] bytes) throws IOException {
        Schema<RPCRequest> schema = RuntimeSchema.getSchema(RPCRequest.class);

        RPCRequest message = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, message, schema);
        return message;
    }
}
