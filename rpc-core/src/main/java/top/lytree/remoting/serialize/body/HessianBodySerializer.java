package top.lytree.remoting.serialize.body;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;
import top.lytree.remoting.serialize.SerializationException;
import top.lytree.remoting.serialize.BodySerializer;
import top.lytree.remoting.serialize.algorithm.SerializerAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class HessianBodySerializer implements BodySerializer {

    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.Hessian;
    }

    @Override
    public <T> byte[] serialize(T object) {
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


    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(Class<T> clz, byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException();
        }
        T result;

        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)) {
            HessianSerializerInput hessianInput = new HessianSerializerInput(is);
            result = (T) hessianInput.readObject(clz);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return result;
    }
}
