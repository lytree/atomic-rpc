package top.lytree.remoting.serialize.message;

import top.lytree.json.JSONObject;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.serialize.MessageSerializer;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

import java.io.IOException;

public class JSONBodySerializer implements MessageSerializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(RPCRequest object)throws IOException {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public RPCRequest deserialize(byte[] bytes)throws IOException {
        return JSONObject.parseObject(bytes, RPCRequest.class);
    }

}
