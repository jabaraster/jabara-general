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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

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
     * @throws IllegalStateException リソースが見付からなかった場合.
     * @see Class#getResourceAsStream(String)
     */
    @SuppressWarnings("resource")
    public static BufferedInputStream getResourceAsStream(final Class<?> pBase, final String pLocation) {
        ArgUtil.checkNull(pBase, "pBase"); //$NON-NLS-1$
        ArgUtil.checkNull(pLocation, "pLocation"); //$NON-NLS-1$

        final InputStream in = pBase.getResourceAsStream(pLocation);
        if (in == null) {
            throw new IllegalStateException("Resource not found. base -> " + pBase.getName() + ", location -> " + pLocation); //$NON-NLS-1$//$NON-NLS-2$
        }
        return toBuffered(in);
    }

    /**
     * @param pBase 位置の起点.
     * @param pLocation 位置.
     * @return ストリーム.
     * @throws NotFound リソースが見付からかなかった場合.
     * @see Class#getResourceAsStream(String)
     */
    @SuppressWarnings("resource")
    public static InputStream getResourceAsStreamSafety(final Class<?> pBase, final String pLocation) throws NotFound {
        ArgUtil.checkNull(pBase, "pBase"); //$NON-NLS-1$
        ArgUtil.checkNull(pLocation, "pLocation"); //$NON-NLS-1$
        final InputStream ret = pBase.getResourceAsStream(pLocation);
        if (ret == null) {
            throw NotFound.GLOBAL;
        }
        return toBuffered(ret);
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
     * @param pIn -
     * @param pEncoding -
     * @return pInによって読み取れるデータをpEncodingで指定された符号化方式で文字列に変換した結果.
     * @throws IOException -
     */
    public static String toString(final InputStream pIn, final Charset pEncoding) throws IOException {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$
        ArgUtil.checkNull(pEncoding, "pEncoding"); //$NON-NLS-1$
        return toString(pIn, pEncoding, DEFAULT_BUFFER_SIZE);
    }

    /**
     * @param pIn -
     * @param pEncoding -
     * @param pBufferSize -
     * @return pInによって読み取れるデータをpEncodingで指定された符号化方式で文字列に変換した結果.
     * @throws IOException -
     */
    public static String toString(final InputStream pIn, final Charset pEncoding, final int pBufferSize) throws IOException {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$
        ArgUtil.checkNull(pEncoding, "pEncoding"); //$NON-NLS-1$
        return toStringCore(toBuffered(pIn), pEncoding, pBufferSize);
    }

    /**
     * データを移送しながらダイジェスト値を計算します. <br>
     * このメソッドの中でストリームを閉じることはありません. <br>
     * 
     * @param pIn データ読み込み元
     * @param pOut データ書き込み先
     * @param pDigestAlgorithm ダイジェストアルゴリズム.
     * @return 移送したデータの長さとダイジェスト値.
     * @throws IOException データ移送中の例外.
     * @throws IllegalStateException ダイジェストアルゴリズムが見付からない場合.
     */
    @SuppressWarnings("resource")
    public static DataMeta write(final InputStream pIn, final OutputStream pOut, final String pDigestAlgorithm) throws IOException {
        ArgUtil.checkNull(pIn, "pIn"); //$NON-NLS-1$
        ArgUtil.checkNull(pOut, "pOut"); //$NON-NLS-1$
        ArgUtil.checkNullOrEmpty(pDigestAlgorithm, "pDigestAlgorithm"); //$NON-NLS-1$

        final MessageDigest digester = getMessageDigest(pDigestAlgorithm);
        final BufferedOutputStream bufOut = IoUtil.toBuffered(pOut);
        final BufferedInputStream bufIn = IoUtil.toBuffered(pIn);
        final byte[] buf = new byte[4096];

        long length = 0;
        for (int d = bufIn.read(buf); d != -1; d = bufIn.read(buf)) {
            length += d;
            bufOut.write(buf, 0, d);
            digester.update(buf, 0, d);
        }

        bufOut.flush();

        final String hash = DatatypeConverter.printHexBinary(digester.digest());
        return new DataMeta(hash, length);
    }

    private static MessageDigest getMessageDigest(final String pDigestAlgorithm) {
        try {
            return MessageDigest.getInstance(pDigestAlgorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw ExceptionUtil.rethrow(e);
        }
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
