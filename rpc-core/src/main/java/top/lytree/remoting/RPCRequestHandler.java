package top.lytree.remoting;

import lombok.extern.slf4j.Slf4j;
import top.lytree.RPCException;
import top.lytree.provider.ServiceProvider;
import top.lytree.provider.impl.ConsulServiceProviderImpl;
import top.lytree.remoting.protocol.RPCRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class RPCRequestHandler {


    private final ServiceProvider serviceProvider;

    public RPCRequestHandler() {
        serviceProvider = new ConsulServiceProviderImpl();
    }

    /**
     * Processing rpcRequest: call the corresponding method, and then return the method
     */
    public Object handle(RPCRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.getServerName());
        return invokeTargetMethod(rpcRequest, service);
    }

    /**
     * get method execution results
     *
     * @param rpcRequest client request
     * @param service    service object
     * @return the result of the target method execution
     */
    private Object invokeTargetMethod(RPCRequest rpcRequest, Object service) {
        Object result;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getMethodName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            throw new RPCException(e.getMessage(), e);
        }
        return result;
    }
}
