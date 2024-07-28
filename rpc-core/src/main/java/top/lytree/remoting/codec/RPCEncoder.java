package top.lytree.remoting.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.lytree.remoting.protocol.RPCProtocol;

@ChannelHandler.Sharable
public class RPCEncoder extends MessageToByteEncoder<RPCProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RPCProtocol msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toByteBuf());
    }
}
