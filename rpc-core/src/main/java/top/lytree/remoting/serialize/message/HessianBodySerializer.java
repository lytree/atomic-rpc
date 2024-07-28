package top.lytree.remoting.serialize.message;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;
import top.lytree.remoting.protocol.RPCRequest;
import top.lytree.remoting.serialize.MessageSerializer;
import top.lytree.remoting.serialize.SerializationException;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class HessianBodySerializer implements MessageSerializer {

    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.Hessian;
    }

    @Override
    public byte[] serialize(RPCRequest object) throws IOException {
        if (object == null) {
            throw new NullPointerException();
        }
        byte[] results;

        HessianSerializerOutput hessianOutput;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            hessianOutput = new HessianSerializerOutput(os);
            hessianOutput.writeObject(object);
            hessianOutput.flush();
            results = os.toByteArray();
        } catch (Exception e) {
            throw new SerializationException(e);
        }
        return results;
    }

    @Override
    public RPCRequest deserialize(byte[] bytes) throws IOException {
        if (bytes == null) {
            throw new NullPointerException();
        }
        RPCRequest result;

        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)) {
            HessianSerializerInput hessianInput = new HessianSerializerInput(is);
            result = (RPCRequest) hessianInput.readObject(RPCRequest.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return result;
    }


}
