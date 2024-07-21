package top.lytree.serialize.algorithm;

public enum SerializerAlgorithm {
    JSON(Byte.valueOf("1")),

    Proto(Byte.valueOf("2"))



    ;

    private final Byte value;

    SerializerAlgorithm(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }
}
