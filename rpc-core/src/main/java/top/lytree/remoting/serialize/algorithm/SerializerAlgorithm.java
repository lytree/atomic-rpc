package top.lytree.remoting.serialize.algorithm;

import lombok.Getter;

@Getter
public enum SerializerAlgorithm {
    JSON(Byte.valueOf("1")),

    Proto(Byte.valueOf("2")),

    Hessian(Byte.valueOf("3"))

    ;

    private final Byte value;

    SerializerAlgorithm(Byte value) {
        this.value = value;
    }

}
