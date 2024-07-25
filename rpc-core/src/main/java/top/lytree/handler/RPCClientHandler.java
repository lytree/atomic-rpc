package top.lytree.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.lytree.protocol.RPCProtocol;

@ChannelHandler.Sharable
public class RPCClientHandler extends SimpleChannelInboundHandler<RPCProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCProtocol request) throws Exception {

    }
}
