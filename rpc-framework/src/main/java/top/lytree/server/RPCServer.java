package top.lytree.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lytree.client.RPCClient;
import top.lytree.consul.Consul;
import top.lytree.provider.RPCServiceConfig;
import top.lytree.provider.ServiceProvider;
import top.lytree.provider.impl.ConsulServiceProviderImpl;
import top.lytree.remoting.codec.RPCDecoder;
import top.lytree.remoting.codec.RPCEncoder;
import top.lytree.remoting.codec.RPCRequestEncoder;
import top.lytree.remoting.codec.RPCResponseEncoder;
import top.lytree.remoting.handler.RPCServerHandler;


public class RPCServer {
    private final static Logger logger = LoggerFactory.getLogger(RPCClient.class);

    private static ServiceProvider serviceProvider;

    static {
        Consul build = Consul.builder().build();
        serviceProvider = new ConsulServiceProviderImpl(build);
    }

    private final Integer port;

    public void registerService(RPCServiceConfig rpcServiceConfig) {
        serviceProvider.publishService(rpcServiceConfig);
    }

    public RPCServer(Integer port) {
        this.port = port;
    }

    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                // TCP默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据快，减少网络传输。TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法。
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 是否开启 TCP 底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new RPCDecoder());
                        channel.pipeline().addLast(new RPCEncoder());
                        channel.pipeline().addLast(new RPCRequestEncoder());
                        channel.pipeline().addLast(new RPCResponseEncoder());
                        channel.pipeline().addLast(new RPCServerHandler());
                    }
                });


        try {
            RPCServiceConfig rpcServiceConfig = RPCServiceConfig.builder()
            .group("test").port(port)
                    .version("1")
                    .service("bean").build();

            serviceProvider.publishService(rpcServiceConfig);
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = bootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.info("发生异常", e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }

    }

    public static void main(String[] args) {
        RPCServer rpcServer = new RPCServer(8888);
        rpcServer.run();
    }

}
