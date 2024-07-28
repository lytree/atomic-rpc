package top.lytree.remoting.protocol;

import lombok.Getter;

@Getter
public class RPCResponse<T> {

    private String requestId;
    /**
     * response code
     */
    private Integer code;
    /**
     * response message
     */
    private String message;
    /**
     * response body
     */
    private T data;

    public static <T> RPCResponseBuilder<T> newBuilder() {
        return new RPCResponseBuilder<T>();
    }
    public static final class RPCResponseBuilder<T> {
        private String requestId;
        private Integer code;
        private String message;
        private T data;

        public RPCResponseBuilder() {
        }

        public RPCResponseBuilder(RPCResponse<T> other) {
            this.requestId = other.requestId;
            this.code = other.code;
            this.message = other.message;
            this.data = other.data;
        }



        public RPCResponseBuilder<T> requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public RPCResponseBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public RPCResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public RPCResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public RPCResponse<T> build() {
            RPCResponse<T> rPCResponse = new RPCResponse<T>();
            rPCResponse.message = this.message;
            rPCResponse.data = this.data;
            rPCResponse.code = this.code;
            rPCResponse.requestId = this.requestId;
            return rPCResponse;
        }
    }
}
