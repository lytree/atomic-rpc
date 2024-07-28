package top.lytree.remoting.serialize;

import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

import java.io.IOException;

public interface MessageSerializer {
    SerializerAlgorithm getSerializerAlgorithm();


    byte[] serialize(RPCRequest obj) throws IOException;

    /**
     * 解码
     */
    RPCRequest deserialize(byte[] bytes) throws IOException;
}
