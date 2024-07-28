package top.lytree.remoting.protocol;

import lombok.Getter;

@Getter
public class RPCRequest {
    private String requestId;
    private String serverName;
    private String methodName;
    private Byte messageType;
    private String version;
    private String group;
    private Object[] parameters;
    private Class<?>[] paramTypes;

    public static RPCMessageBuilder newBuilder() {
        return new RPCMessageBuilder();
    }
    public static final class RPCMessageBuilder {
        private String serverName;
        private String methodName;
        private String requestId;
        private byte[] body;

        public RPCMessageBuilder() {
        }

        public RPCMessageBuilder(RPCRequest other) {
            this.serverName = other.serverName;
            this.methodName = other.methodName;
            this.requestId = other.requestId;
        }



        public RPCMessageBuilder serverName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public RPCMessageBuilder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public RPCMessageBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public RPCMessageBuilder body(byte[] body) {
            this.body = body;
            return this;
        }

        public RPCRequest build() {
            RPCRequest rPCRequest = new RPCRequest();
            rPCRequest.serverName = this.serverName;
            rPCRequest.methodName = this.methodName;
            rPCRequest.requestId = this.requestId;
            return rPCRequest;
        }
    }
}
