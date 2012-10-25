/**
 * 
 */
package jabara.general;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
}
