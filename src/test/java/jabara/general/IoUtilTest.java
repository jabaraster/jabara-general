/**
 * 
 */
package jabara.general;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author jabaraster
 */
@RunWith(Enclosed.class)
public class IoUtilTest {

    /**
     * @author jabaraster
     */
    @SuppressWarnings("static-method")
    public static class toString_InputStream_Charset {
        /**
         * @throws IOException -
         */
        @SuppressWarnings("nls")
        @Test
        public void _test() throws IOException {
            final Charset ENCODING = Charset.forName("UTF-8");
            final String source = "あいうえお";
            final byte[] binary = source.getBytes(ENCODING);
            final String act = IoUtil.toString(new ByteArrayInputStream(binary), ENCODING);
            assertThat(act, is(source));
        }
    }
}
