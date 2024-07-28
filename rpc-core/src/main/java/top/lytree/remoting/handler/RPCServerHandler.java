package top.lytree.remoting.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.json.JSONObject;
import top.lytree.remoting.protocol.RPCProtocol;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.protocol.RPCResponse;

import java.util.UUID;

@ChannelHandler.Sharable
public class RPCServerHandler extends SimpleChannelInboundHandler<RPCProtocol> {
    private final static Logger logger = LoggerFactory.getLogger(RPCServerHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.error("server catch exception",cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCProtocol msg) throws Exception {

    }
}
