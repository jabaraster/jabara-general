/**
 * 
 */
package jabara.general;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * @author jabaraster
 */
public class DataMetaTest {

    /**
     * @throws Exception -
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    public void _test() throws Exception {
        final ByteArrayOutputStream mem = new ByteArrayOutputStream();
        final ObjectOutputStream objOut = new ObjectOutputStream(mem);
        objOut.writeObject(new DataMeta("aaaaaa", 10));

        final ByteArrayInputStream in = new ByteArrayInputStream(mem.toByteArray());
        final ObjectInputStream objIn = new ObjectInputStream(in);

        System.out.println(objIn.readObject());
    }

}
