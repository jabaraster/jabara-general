/**
 * 
 */
package jabara.general;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 
 * @author jabaraster
 */
public final class IoUtil {

    /**
     * 
     */
    public static final Charset UTF_8               = Charset.forName("UTF-8"); //$NON-NLS-1$

    /**
     * {@link #toString(InputStream, Charset)}で使われる内部バッファのサイズ.
     */
    public static final int     DEFAULT_BUFFER_SIZE = 4096;

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
        ArgUtil.checkNull(pBase, "pBase"); //$NON-NLS-1$
        ArgUtil.checkNull(pLocation, "pLocation"); //$NON-NLS-1$

        final InputStream in = pBase.getResourceAsStream(pLocation);
        if (in == null) {
            throw new IllegalStateException("Resource not found. base -> " + pBase.getName() + ", location -> " + pLocation); //$NON-NLS-1$//$NON-NLS-2$
        }
        return (BufferedInputStream) (in instanceof BufferedInputStream ? in : new BufferedInputStream(in));
    }

    /**
     * @param pIn -
     * @return -
     */
    public static BufferedInputStream toBuffered(final InputStream pIn) {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$

        if (pIn instanceof BufferedInputStream) {
            return (BufferedInputStream) pIn;
        }
        return new BufferedInputStream(pIn);
    }

    /**
     * @param pOut -
     * @return -
     */
    public static BufferedOutputStream toBuffered(final OutputStream pOut) {
        ArgUtil.checkNull(pOut, "pOut"); //$NON-NLS-1$

        if (pOut instanceof BufferedOutputStream) {
            return (BufferedOutputStream) pOut;
        }
        return new BufferedOutputStream(pOut);
    }

    /**
     * @param pIn
     * @param pEncoding
     * @return pInによって読み取れるデータをpEncodingで指定された符号化方式で文字列に変換した結果.
     * @throws IOException -
     */
    public static String toString(final InputStream pIn, final Charset pEncoding) throws IOException {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$
        ArgUtil.checkNull(pEncoding, "pEncoding"); //$NON-NLS-1$
        return toString(pIn, pEncoding, DEFAULT_BUFFER_SIZE);
    }

    /**
     * @param pIn
     * @param pEncoding
     * @param pBufferSize
     * @return pInによって読み取れるデータをpEncodingで指定された符号化方式で文字列に変換した結果.
     * @throws IOException
     */
    public static String toString(final InputStream pIn, final Charset pEncoding, final int pBufferSize) throws IOException {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$
        ArgUtil.checkNull(pEncoding, "pEncoding"); //$NON-NLS-1$
        return toStringCore(toBuffered(pIn), pEncoding, pBufferSize);
    }

    private static String toStringCore(final InputStream pIn, final Charset pEncoding, final int pBufferSize) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[pBufferSize];
        for (int d = pIn.read(buffer); d != -1; d = pIn.read(buffer)) {
            out.write(buffer, 0, d);
        }
        return new String(out.toByteArray(), pEncoding);
    }
}
