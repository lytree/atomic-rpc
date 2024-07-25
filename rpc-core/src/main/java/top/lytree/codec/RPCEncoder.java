package top.lytree.codec;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import top.lytree.protocol.RPCProtocol;

import java.util.List;

@ChannelHandler.Sharable
public class RPCEncoder extends MessageToMessageEncoder<RPCProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RPCProtocol msg, List<Object> out) throws Exception {

    }
}
