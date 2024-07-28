package top.lytree.remoting.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.json.JSONObject;
import top.lytree.remoting.constants.RPCConstants;
import top.lytree.remoting.protocol.RPCProtocol;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.protocol.RPCResponse;

import java.util.List;

@ChannelHandler.Sharable
public class RPCClientHandler extends SimpleChannelInboundHandler<RPCProtocol> {

    private final static Logger logger = LoggerFactory.getLogger(RPCClientHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCProtocol msg) throws Exception {
        byte messageType = msg.getType();
        byte[] body = msg.getBody();
        if (messageType == RPCConstants.HEARTBEAT_RESPONSE_TYPE) {
            logger.info("heart [{}]", body);
        } else if (messageType == RPCConstants.RESPONSE_TYPE) {
            RPCResponse response = JSONObject.parseObject(body, RPCResponse.class);
            logger.info(JSONObject.toJSONString(response));
        }
    }
}
