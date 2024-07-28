package top.lytree.remoting.codec;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import top.lytree.remoting.protocol.RPCProtocol;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.protocol.RPCResponse;

import java.util.List;

import static top.lytree.remoting.constants.RPCConstants.RESPONSE_TYPE;
@ChannelHandler.Sharable
public class RPCResponseEncoder extends MessageToMessageEncoder<RPCResponse<Object>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RPCResponse<Object> msg, List<Object> out) throws Exception {
        RPCProtocol build = RPCProtocol.neBuilder().type(RESPONSE_TYPE).build();

        out.add(build);
    }
}
