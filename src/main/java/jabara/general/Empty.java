/*
 * Created:2006/08/22
 */

package jabara.general;

/**
 * 「空」を表現するオブジェクトを定数として保持します. <br>
 * 全ての定数は不変なオブジェクトです. <br>
 * 
 * @author 河野
 */
public final class Empty {

    /**
     * 空文字（長さ0 の文字列）です.
     */
    public static final String     STRING        = ""; //$NON-NLS-1$

    /**
     * 空の{@link Class}オブジェクトの配列です.
     */
    public static final Class<?>[] CLASS_ARRAY   = {};

    /**
     * 空の{@link String}オブジェクトの配列です.
     */
    public static final String[]   STRING_ARRAY  = {};

    /**
     * 空の{@link Object}オブジェクトの配列です.
     */
    public static final Object[]   OBJECT_ARRAY  = {};

    /**
     * 空のboolean配列です.
     */
    public static final boolean[]  BOOLEAN_ARRAY = {};

    /**
     * 空のchar配列です.
     */
    public static final char[]     CHAR_ARRAY    = {};

    /**
     * 空のbyteの配列です.
     */
    public static final byte[]     BYTE_ARRAY    = {};

    /**
     * 空のshort配列です.
     */
    public static final short[]    SHORT_ARRAY   = {};

    /**
     * 空のint配列です.
     */
    public static final int[]      INT_ARRAY     = {};

    /**
     * 空のlong配列です.
     */
    public static final long[]     LONG_ARRAY    = {};

    /**
     * 空のfloat配列です.
     */
    public static final float[]    FLAOT_ARRAY   = {};

    /**
     * 空のdouble配列です.
     */
    public static final double[]   DOUBLE_ARRAY  = {};

    private Empty() {
        // 処理なし
    }
}
