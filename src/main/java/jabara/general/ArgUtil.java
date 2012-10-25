/**
 * 
 */
package jabara.general;

/**
 * @author jabaraster
 */
public final class ArgUtil {

    private ArgUtil() {
        // 処理なし
    }

    /**
     * @param pValue -
     * @param pName -
     */
    public static void checkNull(final Object pValue, final String pName) {
        if (pValue == null) {
            throw new IllegalArgumentException("引数'" + pName + "'にnullを渡すことはできません。"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * @param pValue -
     * @param pName -
     */
    public static void checkNullOrEmpty(final String pValue, final String pName) {
        if (pValue == null || pValue.length() == 0) {
            throw new IllegalArgumentException("引数'" + pName + "'にnullまたは長さ0の文字列を渡すことはできません。検出された値 -> [" + pValue + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }
}
