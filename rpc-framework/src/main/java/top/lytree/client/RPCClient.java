package top.lytree.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.remoting.codec.RPCDecoder;
import top.lytree.remoting.codec.RPCEncoder;
import top.lytree.remoting.codec.RPCRequestEncoder;
import top.lytree.remoting.codec.RPCResponseEncoder;
import top.lytree.remoting.handler.RPCClientHandler;
import top.lytree.remoting.protocol.RPCRequest;

import java.util.UUID;


public class RPCClient {
    private final static Logger logger = LoggerFactory.getLogger(RPCClient.class);

    private static final Bootstrap bootstrap;
    public RPCClient() {

    }

    static {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(executors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .handler(new LoggingHandler(LogLevel.INFO))
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new RPCEncoder());
                        channel.pipeline().addLast(new RPCDecoder());
                        channel.pipeline().addLast(new RPCRequestEncoder());
                        channel.pipeline().addLast(new RPCResponseEncoder());
                        channel.pipeline().addLast(new RPCClientHandler());
                    }
                });
    }

    public ChannelFuture connect(String host,Integer port) throws InterruptedException {
        return bootstrap.connect(host, port).sync();
    }
    public static void main(String[] args) throws InterruptedException {
        RPCClient rpcClient = new RPCClient();
        ChannelFuture channelFuture = rpcClient.connect("127.0.0.1", 8888);
        RPCRequest build = RPCRequest.newBuilder().methodName("1").serverName("2").requestId(UUID.randomUUID().toString()).build();
        channelFuture.channel().writeAndFlush(build);
    }
}
