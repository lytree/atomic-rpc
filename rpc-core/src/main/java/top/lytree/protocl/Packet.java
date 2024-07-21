package top.lytree.protocl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Packet {
    /**
     * 协议版本
     */
    @JsonIgnore
    private Byte version = 1;

    /**
     * 序列化器 默认JSON解析
     */
    @JsonIgnore
    private Byte serializer = 1;

}
