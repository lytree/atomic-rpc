package top.lytree.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RPCServiceConfig {
    /**
     * service version
     */
    private  String version = "";
    /**
     * when the interface has multiple implementation classes, distinguish by group
     */
    private  String group = "";

    private Integer port = 8888;
    /**
     * target service
     */
    private Object service;

    public String getRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }

    public String getServiceName() {
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }
}
