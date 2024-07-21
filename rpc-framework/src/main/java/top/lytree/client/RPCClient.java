package top.lytree.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.codec.RPCCodec;

public class RPCClient {
    private final static Logger logger = LoggerFactory.getLogger(RPCClient.class);

    private final String host;

    private final Integer port;

    private static Bootstrap bootstrap;
    public RPCClient(String host,Integer port) {
        this.host = host;
        this.port = port;
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
                        channel.pipeline().addLast(new RPCCodec());
                    }
                });
    }
}
