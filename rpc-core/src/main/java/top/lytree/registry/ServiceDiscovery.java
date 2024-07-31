package top.lytree.registry;

import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.utils.SPI;

import java.net.InetSocketAddress;

@SPI
public interface ServiceDiscovery {
    /**
     * lookup service by rpcServiceName
     *
     * @param rpcRequest rpc service pojo
     * @return service address
     */
    InetSocketAddress lookupService(RPCRequest rpcRequest);
}
