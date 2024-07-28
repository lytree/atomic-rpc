package top.lytree.remoting.serialize.body;

import top.lytree.json.JSONObject;
import top.lytree.remoting.serialize.BodySerializer;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

public class JSONBodySerializer implements BodySerializer {
    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes, clazz);
    }
}
