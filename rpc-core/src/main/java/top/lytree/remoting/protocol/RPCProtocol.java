package top.lytree.remoting.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class RPCProtocol implements Serializable {
    private final short magic; // 魔数
    private final byte version; // 协议版本号
    private final long requestId; // 请求 ID
    private final byte type; // 数据类型
    private final int length; // 数据长度
    private final byte[] body; //数据结构体

    public RPCProtocol(ByteBuf frame) {
        this.magic = frame.readShort();
        this.version = frame.readByte();
        this.type = frame.readByte();
        this.requestId = frame.readLong();
        this.length = frame.readInt();
        byte[] body = new byte[length];
        frame.readBytes(body);
        this.body = body;
    }

    private RPCProtocol(short magic, byte version, byte type, long requestId, int length, byte[] body) {
        this.magic = magic;
        this.version = version;
        this.type = type;
        this.requestId = requestId;
        this.length = length;
        this.body = body;
    }

    public ByteBuf toByteBuf(){
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShort(this.magic);
        buffer.writeByte(this.version);
        buffer.writeByte(this.type);
        buffer.writeLong(this.requestId);
        buffer.writeInt(this.length);
        buffer.writeBytes(this.body);
        return buffer;
    }

    public static RPCProtocolBuilder neBuilder() {
        return new RPCProtocolBuilder();
    }


    public static final class RPCProtocolBuilder {
        private short magic;
        private byte version;
        private byte type;
        private long requestId;
        private int length;
        private byte[] body;

        public RPCProtocolBuilder() {
        }

        public RPCProtocolBuilder(RPCProtocol other) {
            this.magic = other.magic;
            this.version = other.version;
            this.type = other.type;
            this.requestId = other.requestId;
            this.length = other.length;
            this.body = other.body;
        }


        public RPCProtocolBuilder magic(short magic) {
            this.magic = magic;
            return this;
        }

        public RPCProtocolBuilder version(byte version) {
            this.version = version;
            return this;
        }

        public RPCProtocolBuilder type(byte type) {
            this.type = type;
            return this;
        }

        public RPCProtocolBuilder requestId(long requestId) {
            this.requestId = requestId;
            return this;
        }


        public RPCProtocolBuilder length(int length) {
            this.length = length;
            return this;
        }

        public RPCProtocolBuilder body(byte[] body) {
            this.body = body;
            return this;
        }

        public RPCProtocol build() {
            return new RPCProtocol(magic, version, type, requestId, length, body);
        }
    }
}


