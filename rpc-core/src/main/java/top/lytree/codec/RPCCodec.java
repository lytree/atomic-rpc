package top.lytree.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import top.lytree.protobuf.Request;
import top.lytree.protobuf.Response;

import java.util.List;

@ChannelHandler.Sharable
public class RPCCodec extends MessageToMessageCodec<ByteBuf, Response> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, List<Object> list) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

    }

}
