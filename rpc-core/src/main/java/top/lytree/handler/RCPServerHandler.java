package top.lytree.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.protobuf.Request;

public class RCPServerHandler  extends SimpleChannelInboundHandler<Request> {
    private final static Logger logger = LoggerFactory.getLogger(RCPServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.error("server catch exception",cause);
        ctx.close();
    }
}
