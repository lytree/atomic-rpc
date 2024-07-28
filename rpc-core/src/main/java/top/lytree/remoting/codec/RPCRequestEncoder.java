package top.lytree.remoting.codec;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import top.lytree.remoting.protocol.RPCProtocol;
import top.lytree.remoting.protocol.RPCRequest;

import java.util.List;

import static top.lytree.remoting.constants.RPCConstants.REQUEST_TYPE;
@ChannelHandler.Sharable
public class RPCRequestEncoder extends MessageToMessageEncoder<RPCRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RPCRequest msg, List<Object> out) throws Exception {
        RPCProtocol build = RPCProtocol.neBuilder().type(REQUEST_TYPE).build();

        out.add(build);
    }
}
