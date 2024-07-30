package top.lytree.provider.impl;

import lombok.extern.slf4j.Slf4j;
import top.lytree.consul.Consul;
import top.lytree.consul.model.agent.ImmutableRegistration;
import top.lytree.consul.model.agent.Registration;
import top.lytree.consul.model.agent.Registration.RegCheck;
import top.lytree.consul.model.health.ServiceHealth;
import top.lytree.provider.RPCServiceConfig;
import top.lytree.provider.ServiceProvider;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

import static top.lytree.consul.model.agent.Registration.RegCheck.*;

@Slf4j
public class ConsulServiceProviderImpl implements ServiceProvider {

    private final Consul consul;

    public ConsulServiceProviderImpl(Consul consul) {
        this.consul = consul;
    }

    @Override
    public void addService(RPCServiceConfig rpcServiceConfig) {
        try {
            ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
            builder.id(rpcServiceConfig.getGroup());
            builder.name(rpcServiceConfig.getGroup());
            builder.port(rpcServiceConfig.getPort());
            builder.check(ttl(3L));
            builder.addTags(rpcServiceConfig.getGroup());

            builder.putMeta("host", InetAddress.getLocalHost().getHostName());

            Registration registration = builder.build();
            consul.agentClient().register(registration);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public Object getService(String rpcServiceName) {
        return consul.healthClient().getHealthyServiceInstances(rpcServiceName).getResponse();
    }

    @Override
    public void publishService(RPCServiceConfig rpcServiceConfig) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            this.addService(rpcServiceConfig);
        } catch (UnknownHostException e) {
            log.error("occur exception when getHostAddress", e);
        }
    }
}
