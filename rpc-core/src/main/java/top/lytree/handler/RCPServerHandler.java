package top.lytree.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.protocol.RPCProtocol;

public class RCPServerHandler  extends SimpleChannelInboundHandler<RPCProtocol> {
    private final static Logger logger = LoggerFactory.getLogger(RCPServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCProtocol request) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.error("server catch exception",cause);
        ctx.close();
    }
}
