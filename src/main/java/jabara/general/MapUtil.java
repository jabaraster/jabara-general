/**
 * 
 */
package jabara.general;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jabaraster
 */
public final class MapUtil {

    private MapUtil() {
        //
    }

    /**
     * @param pInitial -
     * @param v -
     * @return -
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> m(final Map<? extends K, ? extends V> pInitial, final Object... v) {
        ArgUtil.checkNull(pInitial, "pInitial"); //$NON-NLS-1$

        if (v == null || v.length == 0) {
            throw new IllegalStateException("引数には２つ以上の要素が必要です."); //$NON-NLS-1$
        }
        if (v.length % 2 == 1) {
            throw new IllegalStateException("引数の個数は偶数でなくてはなりません."); //$NON-NLS-1$
        }
        final Map<K, V> ret = new HashMap<K, V>(pInitial);
        for (int i = 0; i < v.length; i = i + 2) {
            ret.put((K) v[i], (V) v[i + 1]);
        }
        return ret;
    }

    /**
     * @param v -
     * @return -
     */
    public static <K, V> Map<K, V> m(final Object... v) {
        if (v == null || v.length == 0) {
            throw new IllegalStateException("引数には２つ以上の要素が必要です."); //$NON-NLS-1$
        }
        if (v.length % 2 == 1) {
            throw new IllegalStateException("引数の個数は偶数でなくてはなりません."); //$NON-NLS-1$
        }
        return m(Collections.<K, V> emptyMap(), v);
    }
}