/**
 * 
 */
package jabara.general;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author jabaraster
 */
public final class IoUtil {

    private IoUtil() {
        //
    }

    /**
     * pCを安全にクローズします. <br>
     * pCがnullの場合は何も処理を行いません. <br>
     * 
     * @param pC クローズ対象.
     */
    public static void close(final Closeable pC) {
        if (pC == null) {
            return;
        }
        try {
            pC.close();
        } catch (final IOException e) {
            // 処理なし
        }
    }

    /**
     * @param pBase 位置の起点.
     * @param pLocation 位置.
     * @return ストリーム.
     */
    @SuppressWarnings("resource")
    public static BufferedInputStream getResourceAsStream(final Class<?> pBase, final String pLocation) {
        final InputStream in = pBase.getResourceAsStream(pLocation);
        if (in == null) {
            throw new IllegalStateException("Resource not found. base -> " + pBase.getName() + ", location -> " + pLocation); //$NON-NLS-1$//$NON-NLS-2$
        }
        return (BufferedInputStream) (in instanceof BufferedInputStream ? in : new BufferedInputStream(in));
    }
}
