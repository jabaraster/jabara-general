/**
 * 
 */
package jabara.general;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jabaraster
 */
public final class ReflectionUtil {
    private ReflectionUtil() {
        // 処理なし
    }

    /**
     * @param pScanTarget -
     * @param pAnnotationType -
     * @return -
     */
    public static List<Method> getAnnotatedMethod(final Class<?> pScanTarget, final Class<? extends Annotation> pAnnotationType) {
        ArgUtil.checkNull(pScanTarget, "pScanTarget"); //$NON-NLS-1$
        ArgUtil.checkNull(pAnnotationType, "pAnnotationType"); //$NON-NLS-1$

        final List<Method> ret = new ArrayList<Method>();
        for (Class<?> type = pScanTarget; !Object.class.equals(type); type = type.getSuperclass()) {
            for (final Method method : type.getDeclaredMethods()) {
                if (method.isAnnotationPresent(pAnnotationType)) {
                    method.setAccessible(true);
                    ret.add(method);
                }
            }
        }
        return ret;
    }

    /**
     * @param pType 調査対象の型.
     * @return pTypeに唯一設定されている具体的な型パラメータ型.
     * @throws NotFound 見付からなかった場合.
     */
    public static Class<?> getConcreteParameterType(final Class<?> pType) throws NotFound {
        ArgUtil.checkNull(pType, "pType"); //$NON-NLS-1$

        for (Class<?> sc = pType; !Object.class.equals(sc); sc = sc.getSuperclass()) {
            final Type sup = sc.getGenericSuperclass();
            if (sup instanceof ParameterizedType) {
                final Type[] types = ((ParameterizedType) sup).getActualTypeArguments();
                if (types.length == 1 && types[0] instanceof Class) {
                    return (Class<?>) types[0];
                }
            }
        }

        throw NotFound.GLOBAL;
    }

    /**
     * @param pObject -
     * @param pMethod -
     * @param pArguments -
     * @return -
     */
    public static Object invoke(final Object pObject, final Method pMethod, final Object[] pArguments) {
        try {
            return pMethod.invoke(pObject, pArguments);

        } catch (final InvocationTargetException e) {
            throw ExceptionUtil.rethrow(e.getTargetException());
        } catch (final IllegalAccessException e) {
            throw ExceptionUtil.rethrow(e);
        }
    }

    /**
     * {@link Object}クラスにて定義されているメソッドかどうかをチェックします.
     * 
     * @param pMethod チェック対象メソッド.
     * @return Objectクラスで定義されているメソッドであればtrue.
     */
    public static boolean isObjectMethod(final Method pMethod) {
        ArgUtil.checkNull(pMethod, "pMethod"); //$NON-NLS-1$

        if (pMethod.getDeclaringClass().equals(Object.class)) {
            return true;
        }

        final Class<?>[] argumentTypes = pMethod.getParameterTypes();
        if (isHashCodeCore(pMethod, argumentTypes)) {
            return true;
        }
        if (isToStringCore(pMethod, argumentTypes)) {
            return true;
        }
        if (isEqualsCore(pMethod, argumentTypes)) {
            return true;
        }

        // getClass()はfinalなので最初のチェックでひっかかる.
        // wait(),notify(),notifyAll()も同様.

        return false;
    }

    private static boolean isEqualsCore(final Method pMethod, final Class<?>[] pArgumentTypes) {
        if (!"equals".equals(pMethod.getName())) { //$NON-NLS-1$
            return false;
        }
        if (pArgumentTypes == null) {
            return false;
        }
        if (pArgumentTypes.length != 1) {
            return false;
        }
        if (Object.class.equals(pArgumentTypes[0])) {
            return true;
        }
        return false;
    }

    private static boolean isHashCodeCore(final Method pMethod, final Class<?>[] pArgumentTypes) {
        if (!"hashCode".equals(pMethod.getName())) { //$NON-NLS-1$
            return false;
        }
        if (pArgumentTypes == null || pArgumentTypes.length == 0) {
            return true;
        }
        return false;
    }

    private static boolean isToStringCore(final Method pMethod, final Class<?>[] pArgumentTypes) {
        if (!"toString".equals(pMethod.getName())) { //$NON-NLS-1$
            return false;
        }
        if (pArgumentTypes == null || pArgumentTypes.length == 0) {
            return true;
        }
        return false;
    }
}
