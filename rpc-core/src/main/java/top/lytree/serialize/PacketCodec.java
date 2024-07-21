package top.lytree.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import top.lytree.protocl.Packet;
import top.lytree.serialize.algorithm.SerializerAlgorithm;
import top.lytree.serialize.impl.JSONSerializer;
import top.lytree.serialize.impl.ProtobufSerializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodec {

    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x12345678;


    /**
     * 序列化器们
     */
    private static final Map<Byte, Serializer> serializerMap;


    static {
        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON.getValue(), new JSONSerializer());
        serializerMap.put(SerializerAlgorithm.Proto.getValue(), new ProtobufSerializer());
    }

    /**
     * 编码
     */
    public static ByteBuf encode(Packet packet) {
        // 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        writeByteBufInfo(byteBuf, packet);

        return byteBuf;
    }

    /**
     * 编码
     */
    public static void encode(ByteBuf out, Packet msg) {
        writeByteBufInfo(out, msg);
    }

    /**
     * 编码ByteBuf信息
     */
    private static void writeByteBufInfo(ByteBuf out, Packet msg) {
        // 开始编码 魔数
        out.writeInt(MAGIC_NUMBER);
        // 版本号
        out.writeByte(msg.getVersion());
        // 序列化器
        out.writeByte(msg.getSerializer());
        // 对象信息
        Serializer serializer = serializerMap.get(msg.getSerializer());
        byte[] bytes = serializer.serialize(msg);
        // 对象长度和信息
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

    /**
     * 解码
     */
    public static Packet decode(ByteBuf byteBuf,Class<?> clazz) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化器
        Serializer serializer = serializerMap.get(byteBuf.readByte());
        // 对象信息
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        if (serializer != null && clazz != null) {
            return serializer.deserialize(clazz, bytes);
        }

        return null;
    }
}
